package com.stockmarket.marketservice.controller;

import com.stockmarket.marketservice.entity.UserEntity;
import com.stockmarket.marketservice.model.JwtResponse;
import com.stockmarket.marketservice.model.LoginRequest;
import com.stockmarket.marketservice.model.MessageResponse;
import com.stockmarket.marketservice.model.SignupRequest;
import com.stockmarket.marketservice.repository.UserRepository;
import com.stockmarket.marketservice.security.jwt.JwtUtils;
import com.stockmarket.marketservice.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Properties;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().toString();

        if(userDetails.getConfirmed()) {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getMobileNumber(),
                    roles));
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Verification not completed!"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserEntity newUserEntity = new UserEntity(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getRole(),
                signUpRequest.getEmail(),
                signUpRequest.getMobileNumber()
        );


//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
        userRepository.save(newUserEntity);
//        try{
//            sendEmail(user);
//        } catch (Exception e){
//
//        }

        return ResponseEntity.ok(new MessageResponse("User "+ newUserEntity.getId() +" registered successfully!"));
    }

    public void sendEmail(UserEntity userEntity) throws AddressException, MessagingException {


        final String username = "";
        final String password = "";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(""));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(userEntity.getEmail())
            );
            message.setSubject("User confirmation email");

            message.setContent(
                    "<h1><a href =\"http://localhost:8085/api/auth/confirm/" + userEntity.getId() + "/\"> Click to confirm </a></h1>",
                    "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @GetMapping(value="/confirm/{userid}")
    public String confirmUser(@PathVariable Long userid) {
        Optional<UserEntity> user =   userRepository.findById(userid);

        user.get().setConfirmed(true);
        userRepository.save(user.get());
        return "User confirmed " +user.get().getUsername();
    }
}
