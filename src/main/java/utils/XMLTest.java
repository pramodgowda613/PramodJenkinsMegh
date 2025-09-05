package utils;

import org.apache.poi.ss.usermodel.Row;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.initBase;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class XMLTest {
	private LinkedHashMap<String, String> setHeaders = new LinkedHashMap<String, String>();
	private String strKeyName = "";
	private int lastRowNum = 0;
	private Row headerRow = null;
	private Row dataRow = null;
	private String strHeaderName = "";
	private boolean printlog, valueFound;

	public void readprintxml(String fileName) {
		try {
			// Load and parse the XML file
			String filePath = fileName;
			// Create a Path object
			Path path = Paths.get(filePath);
			if (Files.exists(path) != true) {
				System.out.println("File does not exist.");
				return;
			}
			// Get the file name without extension
			String fileNameWithExtension = path.getFileName().toString();
			String fileNameWithoutExtension = fileNameWithExtension.substring(0,
					fileNameWithExtension.lastIndexOf('.'));
			printlog = false;
			setHeaders.clear();
			File xmlFile = new File(fileName); // Update with your XML file path
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();
			// Start printing from the root element
			if (printlog)
				System.out.println("Root element: " + document.getDocumentElement().getNodeName());
			// Excel
//			String strsheetName = document.getDocumentElement().getNodeName();
			initBase.sheet = initBase.workbook.getSheet(fileNameWithoutExtension);
			if (initBase.sheet == null) {
				// If the sheet doesn't exist, create a new one
				initBase.sheet = initBase.workbook.createSheet(fileNameWithoutExtension);
			}
			// Get the last row number to start writing new data
			lastRowNum = initBase.sheet.getLastRowNum();
			if (lastRowNum <= 0) {
				lastRowNum = 0; // returns -1 hence this hard coded value to 0
				headerRow = initBase.sheet.createRow(0);
			}
			headerRow = initBase.sheet.getRow(0);
			try {
				dataRow = initBase.sheet.createRow(++lastRowNum);
			} catch (Exception e) {
			}
			addHeader(document.getDocumentElement(), 0);
			if (setHeaders.containsKey("Value"))
				valueFound = true;
			else
				valueFound = false;
			printNode(document.getDocumentElement(), 0);
//			for (int i = 0; i < setHeaders.size(); i++) {
//				initBase.sheet.autoSizeColumn(i);
//			}
//			initBase.workbook.write(initBase.outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printNode(Node node, int level) {
		try {
//			if (Integer.parseInt(prevLvl) != level) {
//				System.out.println(level);
//			}
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				// Print the current node's tag name and value
				printIndent(level);
//				strHeaderName = element.getTagName();
				String strNodeNam = element.getTagName();
//				addHeaderInExcel(strNodeNam);
				if (printlog)
					System.out.println("Element: " + element.getTagName());
				// Print the text content if it exists
				String textContent = element.getTextContent().trim();
				if (!textContent.isEmpty()) {
					printIndent(level + 1);
					if (textContent.length() < 100) {
						if (printlog)
							System.out.println("Value: " + textContent);
//						addHeaderInExcel(strNodeNam);
						int nColIndex = Utils.findColumnIndex(headerRow, strNodeNam);
						if (strNodeNam.toString().toUpperCase().contains("ID")) {
							if (strKeyName.length() > 0) {
							} else {
								strKeyName = textContent;
							}
						}
						try {
							initBase.sheet.getRow(lastRowNum).getCell(nColIndex).getBooleanCellValue();
						} catch (Exception e) {
						}
//						if (strNodeNam.equalsIgnoreCase("value") != true && (bfound == false)) {
//							lastRowNum = lastRowNum + 1;
//							dataRow = initBase.sheet.createRow(lastRowNum);
//						} else {
//							dataRow = initBase.sheet.getRow(lastRowNum);
//						}
						if (textContent.trim().length() > 0) {
							if (valueFound != true) { // This means no values
								if (strNodeNam.equalsIgnoreCase("ID")) {
									lastRowNum = lastRowNum + 1;
									dataRow = initBase.sheet.createRow(lastRowNum);
								} else {
									dataRow = initBase.sheet.getRow(lastRowNum);
								}
							} else if (strNodeNam.equalsIgnoreCase("VALUE") != true) {
								lastRowNum = lastRowNum + 1;
								dataRow = initBase.sheet.createRow(lastRowNum);
							} else {
								dataRow = initBase.sheet.getRow(lastRowNum);
							}
							
//							if (strNodeNam.equalsIgnoreCase("VALUE") != true) {
//								lastRowNum = lastRowNum + 1;
//								dataRow = initBase.sheet.createRow(lastRowNum);
//							} else {
//								dataRow = initBase.sheet.getRow(lastRowNum);
//							}
							dataRow.createCell(nColIndex).setCellValue(textContent);
							initBase.workbook.write(initBase.outputStream);
						}
					}
				}
				// Print attributes
				for (int i = 0; i < element.getAttributes().getLength(); i++) {
					printIndent(level + 1);
					if (printlog)
						System.out.println("Attribute: " + element.getAttributes().item(i).getNodeName() + " = "
								+ element.getAttributes().item(i).getNodeValue());
				}
				// Recursively print child nodes
				NodeList children = element.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					printNode(children.item(i), level + 1);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while processing printNode : " + e.getMessage());
		}
	}

	private void printIndent(int level) {
		for (int i = 0; i < level; i++) {
			if (printlog)
				System.out.print("    ");
		}
	}
//	// Helper method to check if a tag name already exists in the header row
//	private boolean headerExists(Row headerRow, String tagName) {
//		for (Cell cell : headerRow) {
//			if (cell.getStringCellValue().equals(tagName)) {
//				return true;
//			}
//		}
//		return false;
//	}

	private boolean addHeaderInExcel(String strNodeNam) {
		boolean bfound = false;
		try {
			if (setHeaders.containsKey(strNodeNam) != true) {
				setHeaders.put(strNodeNam, strNodeNam.toUpperCase());
				int cellIdx = setHeaders.size() - 1;
				headerRow.createCell(cellIdx).setCellValue(strHeaderName);
				initBase.workbook.write(initBase.outputStream);
				bfound = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bfound;
	}

	private void addHeader(Node node, int level) {
		try {
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				// Print the current node's tag name and value
				strHeaderName = element.getTagName();
				String strNodeNam = element.getTagName();
				addHeaderInExcel(strNodeNam);
				// Recursively print child nodes
				NodeList children = element.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					addHeader(children.item(i), level + 1);
				}
			}
		} catch (Exception e) {
			System.out.println("Error while processing addHeader function : " + e.getMessage());
		}
	}
}
