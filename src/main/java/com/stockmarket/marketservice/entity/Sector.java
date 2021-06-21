package com.stockmarket.marketservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class Sector {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String brief;
}
