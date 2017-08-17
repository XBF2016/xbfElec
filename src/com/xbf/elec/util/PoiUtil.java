package com.xbf.elec.util;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiUtil {
	public static void exportExcel(String[][] data, OutputStream outputStream)
			throws IOException {

		// 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();

		// 获取列数
		short colNum = (short) data[0].length;

		// 第一行
		HSSFRow titleRow = sheet.createRow(0);
		// 第一行样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		cellStyle.setFont(font);
		// 第一行数据
		for (short i = 0; i < colNum; i++) {
			HSSFCell cell = titleRow.createCell(i);
			// 应用样式
			cell.setCellStyle(cellStyle);
			// 设置编码
			cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
			cell.setCellValue(data[0][i]);
		}

		// 用户数据
		for (short i = 1; i < data.length; i++) {// 遍历行
			HSSFRow row = sheet.createRow(i);
			for (short j = 0; j < colNum; j++) {
				HSSFCell cell = row.createCell(j);
				// 设置编码
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(data[i][j]);
			}
		}

		// 写入数据到输出流
		workbook.write(outputStream);
		outputStream.close();

	}
}
