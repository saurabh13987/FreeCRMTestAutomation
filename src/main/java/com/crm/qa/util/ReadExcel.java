package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

	private Logger logger = Logger.getLogger(this.getClass());

	public Object[][] getTestData(String excelName, String sheetName) {

		FileInputStream file = null;
		Workbook book = null;
		Sheet sheet = null;
		try

		{
			file = new FileInputStream(excelName);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);
		}

		catch (FileNotFoundException exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}

		catch (InvalidFormatException exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}

		catch (IOException exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int row = 0; row < sheet.getLastRowNum(); row++) {
			for (int column = 0; column < sheet.getRow(0).getLastCellNum(); column++) {
				if (sheet.getRow(row + 1).getCell(column) != null
						&& !sheet.getRow(row + 1).getCell(column).toString().isEmpty()) {
					data[row][column] = sheet.getRow(row + 1).getCell(column).toString();
				} else {
					data[row][column] = "";
				}
			}
		}

		return data;
	}

}
