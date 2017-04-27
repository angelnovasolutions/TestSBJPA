package netgloo.utils;

import java.io.*;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {

	public void readXlsx(String ruta, int sheetIndex) {
		try {
				// Get the file
				File inputFile = new File(ruta);
			
		        // Get the workbook instance for XLSX file
		        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(inputFile));
	
		        // Get first sheet from the workbook
		        XSSFSheet sheet = wb.getSheetAt(sheetIndex);
	
		        Row row;
		        Cell cell;
	
		        // Iterate through each rows from first sheet
		        Iterator<Row> rowIterator = sheet.iterator();
	
		        while (rowIterator.hasNext()) {
		                row = rowIterator.next();
	
		                // For each row, iterate through each columns
		                Iterator<Cell> cellIterator = row.cellIterator();
		                
		                while (cellIterator.hasNext()) {
		                	
			                cell = cellIterator.next();
		
			                switch (cell.getCellType()) {
		
				                case Cell.CELL_TYPE_BOOLEAN:
				                        System.out.println(cell.getBooleanCellValue());
				                        break;
			
				                case Cell.CELL_TYPE_NUMERIC:
				                        System.out.println(cell.getNumericCellValue());
				                        break;
			
				                case Cell.CELL_TYPE_STRING:
				                        System.out.println(cell.getStringCellValue());
				                        break;
			
				                case Cell.CELL_TYPE_BLANK:
				                        System.out.println(" ");
				                        break;
			
				                default:
				                        System.out.println(cell);
		
			                }
		                }
		        }
		}

		catch (Exception e) {
		        System.err.println("Exception :" + e.getMessage());
		}
	}

	
	public void readXls(String ruta, int sheetIndex) {
		
		try {
				// Get the file
				File inputFile = new File(ruta);
		        
				// Get the workbook instance for XLS file
		        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
		        // Get first sheet from the workbook
		        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		      
		        Cell cell;
		        Row row;
	
		        // Iterate through each rows from first sheet
		        Iterator<Row> rowIterator = sheet.iterator();
		        
		        while (rowIterator.hasNext()) {
		        	
		                row = rowIterator.next();
	
		                // For each row, iterate through each columns
		                Iterator<Cell> cellIterator = row.cellIterator();
		                
		                while (cellIterator.hasNext()) {

		                	cell = cellIterator.next();
	
		                	if(cell.toString().equals("Cuenta Origen") || cell.toString().equals("Descripción") || cell.toString().equals("Fecha Aplicación") || cell.toString().equals("Número Cliente Origen")){
		                		System.out.println("Celda: " + cell.toString() + "  |  Fila: " + row.getRowNum() + "  |  Celda: " + cell.getColumnIndex());
		                	}
		
//			                switch (cell.getCellType()) {
//		
//				                case Cell.CELL_TYPE_BOOLEAN:
//				                        System.out.println(cell.getBooleanCellValue());
//				                        break;
//			
//				                case Cell.CELL_TYPE_NUMERIC:
//				                        System.out.println(cell.getNumericCellValue());
//				                        break;
//			
//				                case Cell.CELL_TYPE_STRING:
//				                        System.out.println(cell.getStringCellValue());
//				                        break;
//			
//				                case Cell.CELL_TYPE_BLANK:
//				                        System.out.println(" ");
//				                        break;
//			
//				                default:
//				                        System.out.println(cell);
//			                }
		                }
		        }
	
		} 
	
		catch (FileNotFoundException e) {
		        System.err.println("Exception" + e.getMessage());
		}
		
		catch (IOException e) {
		        System.err.println("Exception" + e.getMessage());
		}
	}

	
	public static void main(String[] args) 
	{
			ExcelReader er = new ExcelReader();
			
//			String rutaXLS = "/home/angel/CargaArchivo/Excel9703.xls";
			String rutaXLS = "/home/angel/CargaArchivo/DispersionNomina.xls";
			String rutaXLSX = "/home/angel/CargaArchivo/Excel0313.xlsx";
			
			int sheetXLS = 0;
			int sheetXLSX = 0;
			
	        er.readXls(rutaXLS, sheetXLS);
//	        er.readXlsx(rutaXLSX, sheetXLSX);
	}

	
}






































































/*


//    		System.out.println(
//    		"IDLineType: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getAdbFileLine().getIDLineType() + " | " +
//    				"IDField: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getIDField()  + " | " +
//    				"Field: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getField()  + " | " +
//    				"LongField: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getLongField()  + " | " +
//    				"StartField: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getStartField()  + " | " +
//    				"EndField: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getEndField()  + " | " +
//    				"IDFormat: " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getAdbCtFieldFormat().getIDFormat()
//    				);


 */
