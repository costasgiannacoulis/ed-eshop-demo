package org.acme.eshop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;

public class SpreadsheetStructureBuilder {
	public final static String DATEFORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static DateFormat DATEFORMAT = new SimpleDateFormat(DATEFORMAT_PATTERN);
	public final static String DEFAULT_FONT = "Tahoma";
	public final static MediaType XLSX_MEDIA_TYPE = MediaType.parseMediaType(
		"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	private final static int DEFAULT_COLUMN_WIDTH = 20;
	private static Calendar TODAY = Calendar.getInstance();

	public Workbook workbook = new XSSFWorkbook();
	public Sheet sheet;
	public CellStyle headerCellStyle;
	public CellStyle defaultCellStyle, dateCellStyle, numberCellStyle, currencyCellStyle;
	private Map<Integer, CellStyle> cellStyleMap = new HashMap<>();

	static {
		TODAY.set(Calendar.HOUR_OF_DAY, 0);
		TODAY.set(Calendar.MINUTE, 0);
		TODAY.set(Calendar.SECOND, 0);
	}

	{
		final Font headerFont = workbook.createFont();
		headerFont.setFontName(DEFAULT_FONT);
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 8);
		headerFont.setColor(IndexedColors.WHITE.getIndex());

		final Font bodyFont = workbook.createFont();
		bodyFont.setFontName(DEFAULT_FONT);
		bodyFont.setFontHeightInPoints((short) 8);
		bodyFont.setColor(IndexedColors.BLACK.getIndex());

		headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		headerCellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);

		defaultCellStyle = workbook.createCellStyle();
		defaultCellStyle.setFont(bodyFont);
		defaultCellStyle.setBorderBottom(BorderStyle.THIN);
		defaultCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		defaultCellStyle.setBorderLeft(BorderStyle.THIN);
		defaultCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		defaultCellStyle.setBorderRight(BorderStyle.THIN);
		defaultCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		defaultCellStyle.setBorderTop(BorderStyle.THIN);
		defaultCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		defaultCellStyle.setAlignment(HorizontalAlignment.LEFT);
		defaultCellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);

		numberCellStyle = workbook.createCellStyle();
		numberCellStyle.cloneStyleFrom(defaultCellStyle);
		numberCellStyle.setAlignment(HorizontalAlignment.RIGHT);
		numberCellStyle.setDataFormat((short) 3);

		dateCellStyle = workbook.createCellStyle();
		dateCellStyle.cloneStyleFrom(defaultCellStyle);
		dateCellStyle.setAlignment(HorizontalAlignment.CENTER);
		dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(DATEFORMAT_PATTERN));

		currencyCellStyle = workbook.createCellStyle();
		currencyCellStyle.cloneStyleFrom(numberCellStyle);
		currencyCellStyle.setDataFormat((short) 7);
	}

	public SpreadsheetStructureBuilder(final String sheetTitle) {
		sheet = workbook.createSheet(sheetTitle);
		sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
	}

	public void registerCellStyle(final int columnIndex, final CellStyle cellStyle) {
		cellStyleMap.put(columnIndex, cellStyle);
	}

	public void registerCellStyles(final CellStyle... cellStyles) {
		IntStream.range(0, cellStyles.length).forEach(i -> cellStyleMap.put(i, cellStyles[i]));
	}

	public void createHeaderRow(final int rowNumber, final String... titles) {
		createRow(headerCellStyle, rowNumber, titles);
	}

	public void createRow(final CellStyle cellStyle, final int rowNumber, final String... cellValues) {
		final Row row = sheet.createRow(rowNumber);

		IntStream.range(0, cellValues.length).forEach(i -> {
			createCell(row, i, cellValues[i], cellStyle);
			sheet.autoSizeColumn(i);
		});
	}

	public void createRow(final int rowNumber, final String... cellValues) {
		final Row row = sheet.createRow(rowNumber);

		IntStream.range(0, cellValues.length).forEach(i -> {
			createCell(row, i, cellValues[i], cellStyleMap.get(i));
			sheet.autoSizeColumn(i);
		});
	}

	private void createCell(final Row row, final int columnIndex, final String cellValue, final CellStyle cellStyle) {
		final Cell cell = row.createCell(columnIndex);
		cell.setCellStyle(cellStyle);

		if (cellStyle.getDataFormat() == 3 || cellStyle.getDataFormat() == 7) {
			cell.setCellValue(Double.valueOf(cellValue));
		} else if (cellStyle.getDataFormatString().equalsIgnoreCase(DATEFORMAT_PATTERN)) {
			try {
				cell.setCellValue(DATEFORMAT.parse(cellValue));
			} catch (final ParseException e) {
				cell.setCellValue(TODAY.getTime());
			}
		} else {
			cell.setCellValue(cellValue);
		}
	}
}
