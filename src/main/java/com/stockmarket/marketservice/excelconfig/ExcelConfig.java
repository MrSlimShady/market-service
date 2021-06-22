package com.stockmarket.marketservice.excelconfig;

import com.stockmarket.marketservice.entity.StockPrice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelConfig {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String[] HEADERs = { "Company Code", "Stock Exchange", "Price Per Share(in Rs)", "Date" };
    static String SHEET = "Sheet1";


    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<StockPrice> excelToStockPrice(InputStream is){
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet worksheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = worksheet.iterator();

            List<StockPrice> stockPriceList = new ArrayList<>();

//            for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
//                StockPrice stockPrice = new StockPrice();
//
//                Row row = worksheet.getRow(i);
//
//                stockPrice.setCompanyCode( row.getCell(0).getStringCellValue());
//                stockPrice.setStockExchange(row.getCell(1).getStringCellValue());
//                stockPrice.setCurrentPrice(row.getCell(2).getNumericCellValue());
//                stockPrice.setDate(row.getCell(3).getDateCellValue());
//                stockPrice.setTime(row.getCell(4).getStringCellValue());
//
//                stockPriceList.add(stockPrice);
//            }
//
//            workbook.close();
//
//            return stockPriceList;
//
//        }catch (IOException e) {
//
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                StockPrice data = new StockPrice();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            data.setCompanyCode( currentCell.getStringCellValue());
                            break;

                        case 1:
                            data.setStockExchange(currentCell.getStringCellValue());
                            break;

                        case 2:
                            data.setCurrentPrice((double)currentCell.getNumericCellValue());
                            break;

                        case 3:
                            data.setDate( currentCell.getDateCellValue());
                            break;

                        case 4:
                            data.setTime( currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }


                stockPriceList.add(data);
            }

            workbook.close();

            return stockPriceList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }

    }
}
