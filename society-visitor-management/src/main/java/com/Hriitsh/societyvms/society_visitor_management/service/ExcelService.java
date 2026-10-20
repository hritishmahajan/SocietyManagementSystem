package com.Hriitsh.societyvms.society_visitor_management.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Hriitsh.societyvms.society_visitor_management.dto.ResidentRequestDTO;

@Service
public class ExcelService {

    public List<ResidentRequestDTO> parseExcelFile(MultipartFile file) throws Exception {
        List<ResidentRequestDTO> residents = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String name = row.getCell(0).getStringCellValue();
                String flatNumber = row.getCell(1).getStringCellValue();
                String email = row.getCell(2).getStringCellValue();
                String phoneNumber = row.getCell(3).getStringCellValue();

                ResidentRequestDTO dto = new ResidentRequestDTO();
                dto.setName(name);
                dto.setFlatNumber(flatNumber);
                dto.setEmail(email);
                dto.setPhoneNumber(phoneNumber);

                residents.add(dto);
            }
        }

        return residents;
    }
}