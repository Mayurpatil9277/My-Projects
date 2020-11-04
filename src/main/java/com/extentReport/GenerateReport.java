package com.extentReport;

import com.common.excelreport.ExcelReportGenerator;

public class GenerateReport {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExcelReportGenerator.generateExcelReport("myxls.xlsx", "/Autotest/test-output");
	}

}
