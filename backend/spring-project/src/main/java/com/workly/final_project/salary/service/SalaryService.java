 package com.workly.final_project.salary.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.workly.final_project.salary.dto.SalaryDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SalaryService {

    public void createAndSaveExcel(SalaryDTO salaryDTO) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("급여대장");
            
            // 스타일 설정
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            
            // 데이터 입력
            createRow(sheet, 0, new String[]{"급 여 대 장", "", "2025 4대보험 요율 적용"}, headerStyle);
            createRow(sheet, 1, new String[]{"성명", salaryDTO.getEmployeeName(), "사번", salaryDTO.getEmployeeId()});
            createRow(sheet, 2, new String[]{"부서", salaryDTO.getDepartment(), "직급", salaryDTO.getPosition()});
            
            // 급여 계산
            int baseSalary = Integer.parseInt(salaryDTO.getBaseSalary());
            int overtimeHours = Integer.parseInt(salaryDTO.getOvertimeHours());
            int bonus = Integer.parseInt(salaryDTO.getBonus());
            
            // 4대보험 계산
            int pension = (int)(baseSalary * 0.045);          // 국민연금 4.5%
            int healthInsurance = (int)(baseSalary * 0.0375); // 건강보험 3.75%
            int employmentInsurance = (int)(baseSalary * 0.009); // 고용보험 0.9%
            int longTermCare = (int)(healthInsurance * 0.115);   // 장기요양보험 11.5%
            
            // 합계 계산
            int totalPay = baseSalary + overtimeHours + bonus;
            int totalDeduction = pension + healthInsurance + employmentInsurance + longTermCare;
            int netPay = totalPay - totalDeduction;
            
            // 급여 내역 입력
            createRow(sheet, 4, new String[]{"입금 항목", "지급 금액", "공제 항목", "공제 금액"}, headerStyle);
            createRow(sheet, 5, new String[]{"기본급", formatNumber(baseSalary), "국민연금", formatNumber(pension)});
            createRow(sheet, 6, new String[]{"초과근무수당", formatNumber(overtimeHours), "건강보험료", formatNumber(healthInsurance)});
            createRow(sheet, 7, new String[]{"보너스", formatNumber(bonus), "장기요양보험료", formatNumber(longTermCare)});
            createRow(sheet, 8, new String[]{"", "", "고용보험료", formatNumber(employmentInsurance)});
            createRow(sheet, 10, new String[]{"지급액 계", formatNumber(totalPay), "공제액 계", formatNumber(totalDeduction)});
            createRow(sheet, 11, new String[]{"실수령액", "", formatNumber(netPay), ""});
            
            // 열 너비 자동 조정
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // 저장 경로 생성
            String directoryPath = "C:/salary/";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // 파일 저장
            String fileName = "급여대장_" + salaryDTO.getEmployeeId() + "_" + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
            String filePath = directoryPath + fileName;
            
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }
    
    private void createRow(Sheet sheet, int rowNum, String[] values, CellStyle style) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < values.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(values[i]);
            if (style != null) {
                cell.setCellStyle(style);
            }
        }
    }
    
    private void createRow(Sheet sheet, int rowNum, String[] values) {
        createRow(sheet, rowNum, values, null);
    }
    
    private String formatNumber(int number) {
        return String.format("%,d", number);
    }
}