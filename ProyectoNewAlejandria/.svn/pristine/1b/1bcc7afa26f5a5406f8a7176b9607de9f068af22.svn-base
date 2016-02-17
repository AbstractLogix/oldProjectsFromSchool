/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author jchaclan
 */
public class ExcelCreator {

    public static final int TYPE_CALENDAR = 100;
    public static final int TYPE_DATE = 200;
    public static final int TYPE_STRING = 300;
    public static final int TYPE_BOOLEAN = 400;
    public static final int TYPE_DOUBLE = 500;
    public static final int TYPE_INTEGER = 600;
    public static final int TYPE_CURRENCY_QUE = 701;
    public static final int TYPE_CURRENCY_USD = 702;
    private static String[] COLUMNS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "AB", "AC", "AD", "AE", "AF"};
    private ArrayList<String> headers;
    private ArrayList<ArrayList> data;
    private ArrayList<Integer> types;
    private ArrayList<String> top;
    private ArrayList<String> footer;
    private String fileName;
    private String title = "Report";
    private CellStyle topCellStyle;
    private CellStyle titleCellStyle;
    private CellStyle footerCellStyle;
    private CellStyle headerCellStyle;
    private CellStyle StringCellStyle;
    private CellStyle DateCellStyle;
    private CellStyle IntegerCellStyle;
    private CellStyle DoubleCellStyle;
    private CellStyle CurrencyQUECellStyle;
    private CellStyle CurrencyUSDCellStyle;
    private CellStyle totalIntegerCellStyle;
    private CellStyle totalDoubleCellStyle;
    private CellStyle totalCurrencyQUECellStyle;
    private CellStyle totalCurrencyUSDCellStyle;
    int headerSize = 0;

    public ExcelCreator(ArrayList<String> headers, ArrayList<ArrayList> data, ArrayList<Integer> types) {
        this.headers = headers;
        this.data = data;
        this.types = types;
        this.top = new ArrayList<String>();
        this.footer = new ArrayList<String>();
    }

    // PARAMETROS: headers, data, types, fileName, title, footer
    public ExcelCreator(ArrayList<String> headers, ArrayList<ArrayList> data, ArrayList<Integer> types, String fileName, String title, ArrayList<String> top, ArrayList<String> footer) {
        this(headers, data, types);
        this.fileName = fileName;
        this.title = title;
        this.top = top;
        this.footer = footer;
    }

    private void initStyles(Workbook workbook) {
        DataFormat df = workbook.createDataFormat();

        // Cell Style del TOP header
        topCellStyle = workbook.createCellStyle();
        Font topFont = workbook.createFont();
        topFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        topCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        topCellStyle.setFont(topFont);

        // Cell Style del TITLE
        titleCellStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleFont.setFontHeightInPoints((short) 16);
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleCellStyle.setFont(titleFont);

        // Cell Style del HEADER de las columnas
        headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerCellStyle.setFont(headerFont);

        // Cell Style del Footer
        footerCellStyle = workbook.createCellStyle();
        Font footerFont = workbook.createFont();
        footerFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        footerFont.setFontHeightInPoints((short) 8);
        footerCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        footerCellStyle.setFont(footerFont);

        // Cell Style String
        StringCellStyle = workbook.createCellStyle();
        StringCellStyle.setAlignment(CellStyle.ALIGN_LEFT);

        // Cell Style Date
        DateCellStyle = workbook.createCellStyle();
        DateCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        DateCellStyle.setDataFormat(df.getFormat("m/d/yy"));

        // Cell Style Integer
        IntegerCellStyle = workbook.createCellStyle();
        IntegerCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);

        // Cell Style Double
        DoubleCellStyle = workbook.createCellStyle();
        DoubleCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        DoubleCellStyle.setDataFormat(df.getFormat("#,##0.00"));

        // Cell Style Currency QUE
        CurrencyQUECellStyle = workbook.createCellStyle();
        CurrencyQUECellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        CurrencyQUECellStyle.setDataFormat(df.getFormat("Q #,##0.00"));

        // Cell Style Currency QUE
        CurrencyUSDCellStyle = workbook.createCellStyle();
        CurrencyUSDCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        CurrencyUSDCellStyle.setDataFormat(df.getFormat("$ #,##0.00"));



        // Cell Style Integer
        totalIntegerCellStyle = workbook.createCellStyle();
        Font totalIntegerFont = workbook.createFont();
        totalIntegerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        totalIntegerCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        totalIntegerCellStyle.setFont(totalIntegerFont);

        // Cell Style Double
        totalDoubleCellStyle = workbook.createCellStyle();
        Font totalDoubleFont = workbook.createFont();
        totalDoubleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        totalDoubleCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        totalDoubleCellStyle.setDataFormat(df.getFormat("#,##0.00"));
        totalDoubleCellStyle.setFont(totalDoubleFont);

        // Cell Style Total Currency QUE
        totalCurrencyQUECellStyle = workbook.createCellStyle();
        Font totalCurrencyQUEFont = workbook.createFont();
        totalCurrencyQUEFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        totalCurrencyQUECellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        totalCurrencyQUECellStyle.setDataFormat(df.getFormat("Q#,##0.00"));
        totalCurrencyQUECellStyle.setFont(totalCurrencyQUEFont);


        // Cell Style Total Currency USD
        totalCurrencyUSDCellStyle = workbook.createCellStyle();
        Font totalCurrencyUSDFont = workbook.createFont();
        totalCurrencyUSDFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        totalCurrencyUSDCellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        totalCurrencyUSDCellStyle.setDataFormat(df.getFormat("$#,##0.00"));
        totalCurrencyUSDCellStyle.setFont(totalCurrencyUSDFont);


    }

    public String createExcel() {
        Workbook workbook = new HSSFWorkbook(); // crear el WorkBook
        Sheet sheet = workbook.createSheet(this.getTitle()); //crear la Hoja de Trabajo
        headerSize = top.size() + 2;
        initStyles(workbook);
        Row row = null;
        Cell cell = null;

        // Imprimir los TOP Headers
        for (int i = 0; i < top.size(); i++) {
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellStyle(topCellStyle);
            cell.setCellValue(top.get(i));
            // Merge de las Celdas donde se encuentran las lines del TOP Header
            sheet.addMergedRegion(new CellRangeAddress(
                    i, //first row (0-based)
                    i, //last row  (0-based)
                    0, //first column (0-based)
                    headers.size() - 1 //last column  (0-based)
                    ));
        }

        // Imprimir el Titulo del Reporte
        row = sheet.createRow(top.size() + 1);
        cell = row.createCell(0);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue(this.getTitle());// Imprimir el Titulo

        // Merge de las Celdas donde se encuentra el Titulo
        sheet.addMergedRegion(new CellRangeAddress(
                top.size() + 1, //first row (0-based)
                top.size() + 1, //last row  (0-based)
                0, //first column (0-based)
                headers.size() - 1 //last column  (0-based)
                ));

        row = sheet.createRow(headerSize + 1);
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(headers.get(i));
        }


        for (int rownum = 0; rownum < data.size(); rownum++) {
            row = sheet.createRow(rownum + headerSize + 2);
            for (int cellnum = 0; cellnum < data.get(rownum).size(); cellnum++) {
                cell = row.createCell(cellnum);
                switch (types.get(cellnum)) {
                    case 100: { // TYPE_CALENDAR
                        cell.setCellValue((Calendar) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 200: { // TYPE_DATE
                        cell.setCellStyle(DateCellStyle);
                        cell.setCellValue((java.util.Date) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 300: { // TYPE_STRING
                        cell.setCellStyle(StringCellStyle);
                        cell.setCellValue((String) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 400: { // TYPE_BOOLEAN
                        cell.setCellValue((Boolean) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 500: { // TYPE_DOUBLE
                        cell.setCellStyle(DoubleCellStyle);
                        cell.setCellValue((Double) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 600: { // TYPE_INTEGER
                        cell.setCellStyle(IntegerCellStyle);
                        cell.setCellValue((Integer) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 701: { // TYPE_CURRENCY_QUE
                        cell.setCellStyle(CurrencyQUECellStyle);
                        cell.setCellValue((Double) data.get(rownum).get(cellnum));
                        break;
                    }
                    case 702: { // TYPE_CURRENCY_USD
                        cell.setCellStyle(CurrencyUSDCellStyle);
                        cell.setCellValue((Double) data.get(rownum).get(cellnum));
                        break;
                    }
                    default: {
                        cell.setCellValue((String) data.get(rownum).get(cellnum));
                        break;
                    }
                }// fin de switch
            }// fin del for que recorre cada celda de la las filas(rows
        }// fin del for que recorre cada una de las filas.

        // IMPRIMIR EL FOOTER
        for (int i = 0; i < footer.size(); i++) {
            row = sheet.createRow(headerSize + data.size() + 4 + i);
            cell = row.createCell(0);
            cell.setCellStyle(footerCellStyle);
            cell.setCellValue(footer.get(i));
            // Merge de las Celdas donde se encuentran las lines del FOOTER
            sheet.addMergedRegion(new CellRangeAddress(
                    headerSize + data.size() + 4 + i, //first row (0-based)
                    headerSize + data.size() + 4 + i, //last row  (0-based)
                    0, //first column (0-based)
                    headers.size() - 1 //last column  (0-based)
                    ));
        }// fin del for para Imprimir las lineas del FOOTER

        // AUTO SIZE COLUM
        for (int i = 0; i < headers.size(); i++) {
            sheet.autoSizeColumn((short) i);
        }

        // TOTALIZAR LOS INT y los DOUBLE
        row = sheet.createRow(headerSize + data.size() + 2);
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i) == ExcelCreator.TYPE_DOUBLE) {
                cell = row.createCell(i);
                cell.setCellStyle(totalDoubleCellStyle);
                cell.setCellFormula("SUM(" + COLUMNS[i] + (int) (headerSize + 3) + ":" + COLUMNS[i] + (int) (headerSize + 2 + data.size()) + ")");
            }
            if (types.get(i) == ExcelCreator.TYPE_INTEGER) {
                cell = row.createCell(i);
                cell.setCellStyle(totalIntegerCellStyle);
                cell.setCellFormula("SUM(" + COLUMNS[i] + (int) (headerSize + 3) + ":" + COLUMNS[i] + (int) (headerSize + 2 + data.size()) + ")");
            }
            if (types.get(i) == ExcelCreator.TYPE_CURRENCY_QUE) {
                cell = row.createCell(i);
                cell.setCellStyle(totalCurrencyQUECellStyle);
                cell.setCellFormula("SUM(" + COLUMNS[i] + (int) (headerSize + 3) + ":" + COLUMNS[i] + (int) (headerSize + 2 + data.size()) + ")");
            }
            if (types.get(i) == ExcelCreator.TYPE_CURRENCY_USD) {
                cell = row.createCell(i);
                cell.setCellStyle(totalCurrencyUSDCellStyle);
                cell.setCellFormula("SUM(" + COLUMNS[i] + (int) (headerSize + 3) + ":" + COLUMNS[i] + (int) (headerSize + 2 + data.size()) + ")");
            }

        }

        createFileName();
        writeFile(workbook);
        return getFileName();
    }// fin de CreateExcel

    private void createFileName() {
        if ((fileName == null) || (fileName.length() == 0)) {
            Calendar c = Calendar.getInstance();
            this.setFileName(c.getTimeInMillis() + ".xls");
        }
        if (!(fileName.indexOf(".") > 0) || (!fileName.substring(fileName.indexOf("."), fileName.indexOf(".") + 4).equalsIgnoreCase(".xls"))) {
            fileName += ".xls";
        }
    }

    private void writeFile(Workbook workbook) {
        FileOutputStream out;
        try {
            out = new FileOutputStream(this.getFileName());
            workbook.write(out);
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(ExcelCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the headers
     */
    public ArrayList<String> getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }

    /**
     * @return the data
     */
    public ArrayList<ArrayList> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList<ArrayList> data) {
        this.data = data;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the types
     */
    public ArrayList<Integer> getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(ArrayList<Integer> types) {
        this.types = types;
    }

    /**
     * @return the top
     */
    public ArrayList<String> getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(ArrayList<String> top) {
        this.top = top;
    }

    /**
     * @return the footer
     */
    public ArrayList<String> getFooter() {
        return footer;
    }

    /**
     * @param footer the footer to set
     */
    public void setFooter(ArrayList<String> footer) {
        this.footer = footer;
    }

    /**
     * @return the totalIntegerCellStyle
     */
    public CellStyle getTotalIntegerCellStyle() {
        return totalIntegerCellStyle;
    }

    /**
     * @param totalIntegerCellStyle the totalIntegerCellStyle to set
     */
    public void setTotalIntegerCellStyle(CellStyle totalIntegerCellStyle) {
        this.totalIntegerCellStyle = totalIntegerCellStyle;
    }

    /**
     * @return the totalDoubleCellStyle
     */
    public CellStyle getTotalDoubleCellStyle() {
        return totalDoubleCellStyle;
    }

    /**
     * @param totalDoubleCellStyle the totalDoubleCellStyle to set
     */
    public void setTotalDoubleCellStyle(CellStyle totalDoubleCellStyle) {
        this.totalDoubleCellStyle = totalDoubleCellStyle;
    }
}