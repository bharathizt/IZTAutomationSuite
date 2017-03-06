package com.izt.snow.automation.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is used by the script to perform WebDriver related operations.
 * 
 * @author siddharth ravindran
 */
public class BaseClass {

	/**
	 * Get a string representing the current URL that the browser is looking at.
	 * 
	 * @return
	 */
	public String getCurrentUrl() {
		return Driver.driver.getCurrentUrl();
	}

	/**
	 * Get the source of the current page.
	 * 
	 * @return
	 */
	public String getPageSource() {
		return Driver.driver.getPageSource();
	}

	/**
	 * The title of the current page.
	 * 
	 * @return
	 */
	public String getTitle() {
		return Driver.driver.getTitle();
	}

	/**
	 * Returns the current window handle.
	 * 
	 * @return
	 */
	public String getWindowHandle() {
		return Driver.driver.getWindowHandle();
	}

	/**
	 * Return a set of window handles which can be used to iterate over all open
	 * windows.
	 */
	public void getURL(String URL) {
		Driver.driver.get(URL);

	}

	public Set<String> getWindowHandles() {
		return Driver.driver.getWindowHandles();
	}

	/**
	 * Gets the Option interface
	 * 
	 * @return
	 */
	public Options manage() {
		return Driver.driver.manage();
	}

	/**
	 * An abstraction allowing the driver to access the browser's history and to
	 * navigate to a given URL.
	 * 
	 * @return
	 */
	public Navigation navigate() {
		return Driver.driver.navigate();
	}

	/**
	 * Quits this driver, closing every associated window.
	 * 
	 */
	public void quit() {
		Driver.driver.quit();

	}

	/**
	 * Close the current window, quitting the browser if it's the last window
	 * currently open.
	 */
	public void close() {
		Driver.driver.close();
	}

	/**
	 * Send future commands to a different frame or window.
	 * 
	 * @return
	 */
	public TargetLocator switchTo() {
		return Driver.driver.switchTo();
	}

	/**
	 * Switch the focus of future commands for this driver to the window with
	 * the given name/handle.
	 * 
	 * @param nameOrHandle
	 */
	public void switchToWindow(String nameOrHandle) {

		Set<String> set = Driver.driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		String parent = itr.next();
		String child = itr.next();
		if (nameOrHandle.equalsIgnoreCase("child")) {
			Driver.driver.switchTo().window(child);
		} else if (nameOrHandle.equalsIgnoreCase("parent")) {
			Driver.driver.switchTo().window(parent);
		}

	}

	/**
	 * Switches to alert
	 */
	public void switchToAlert() {
		Driver.driver.switchTo().alert();

	}

	/**
	 * Accepts the alert.
	 */
	public void acceptAlert() {
		Driver.driver.switchTo().alert().accept();

	}

	/**
	 * Send future commands to a different frame
	 * 
	 * @return frame
	 */
	public WebDriver switchToFrame(String frameId) {
		return Driver.driver.switchTo().frame(frameId);
	}

	/*
	 * Selects the Preferred Value From Drop Down
	 * 
	 * @param WebElement , Dropdown Value
	 */
	public void selectDropdown(WebElement wb, String expectedValue) {

		Select sel = new Select(wb);
		List<WebElement> lst = sel.getOptions();
		int count = lst.size();
		for (int i = 0; i < count; i++) {
			String actual = lst.get(i).getText();
			if (actual.equalsIgnoreCase(expectedValue)) {
				lst.get(i).click();
				break;
			}
		}
	}

	/*
	 * Reading Data From Propertie Files
	 * 
	 * @param String Key , String File Name
	 */
	public String readData(String key, String fileName) {
		String value = "";
		try {

			Properties properties = new Properties();
			File file = new File(fileName);
			if (file.exists()) {
				properties.load(new FileInputStream(file));
				value = properties.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/*
	 * Writing Data To Propertie Files
	 * 
	 * @param String Key , String Value , String File Name
	 */
	public void addData(String key, String val, String fileName) {
		try {
			File file = new File(fileName);
			Properties properties = new Properties();
			properties.load(new FileInputStream(file));
			FileOutputStream obj = new FileOutputStream(file);
			properties.setProperty(key, val);
			properties.store(obj, "Update data into file ");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Implicitly Wait Statement (Wait till Page get Load)
	 */
	public void pageLoadWait() {

		Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/*
	 * Explicit Wait Statement (Wait Till WebElement Present)
	 * 
	 * @param LinkName
	 */
	public void waitForLinkPresent(String linkName) {
		WebDriverWait wait = new WebDriverWait(Driver.driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.linkText(linkName)));
	}

	/*
	 * Explicit Wait Statement
	 * 
	 * @param webElement
	 */
	public void waitForXpathPresent(String wbXpath) {
		WebDriverWait wait = new WebDriverWait(Driver.driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(wbXpath)));
	}

	public void selectFromList(List<WebElement> wb, String exceptedValue) {

		List<WebElement> lst = wb;
		for (int i = 0; i < lst.size(); i++) {
			String actual = lst.get(i).getText();
			if (actual.equalsIgnoreCase(exceptedValue)) {
				lst.get(i).click();
				break;
			}
		}

	}

	

	/*
	 * @provide Sheet Name RowNum and celNum
	 */
	/* Fetching data from Excel Sheet */
	public String getExcelData(String sheetName, int rowNum, int celnum)
			throws EncryptedDocumentException, InvalidFormatException,
			IOException {
		String file=this.readData("ExcelPath", "home.properties");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.getCell(celnum);
		String data = "";
		if (cel.getCellType() == 1) {
			data = cel.getStringCellValue();

		} else if (cel.getCellType() == 0) {
			long data1 = (long) cel.getNumericCellValue();
			data = String.valueOf(data1);

		}
		return data;

	}

	/*
	 * @provide Sheet Name RowNum , celNum and Data
	 */
	/* Writing data into Excel Sheet */
	public void setExcelData(String sheetName, int rowNum, int colNum,
			String data) throws EncryptedDocumentException,
			InvalidFormatException, IOException {
		String file=this.readData("ExcelPath", "home.properties");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(colNum);
		cel.setCellType(cel.CELL_TYPE_STRING);
		FileOutputStream fos = new FileOutputStream(file);
		cel.setCellValue(data);
		wb.write(fos);
	}

	public void actions() {
		Actions act = new Actions(Driver.driver);
		act.sendKeys(Keys.ENTER).perform();
	}

	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void getScreenShot(String fileName) throws IOException {
		String file=this.readData("ScreenShotPath", "home.properties");
		EventFiringWebDriver edriver = new EventFiringWebDriver(Driver.driver);
		File srcImg = edriver.getScreenshotAs(OutputType.FILE);
		File dst = new File(file + fileName + ".png");
		FileUtils.copyFile(srcImg, dst);
	}

}