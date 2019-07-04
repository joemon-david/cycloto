package com.cycloides.qa.auto.framework.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.cycloides.qa.auto.framework.constants.FilePaths;

public class CommonUtility {
	
	 public String takeScreenShot(RemoteWebDriver driver) throws Throwable {
			//  Thread.sleep(1000);
			  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 			  
			  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			 
			  String destFile = dateFormat.format(new Date()) + ".png"; // Set file name
			               // using current
			               // date time.
			  try {
			   FileUtils.copyFile(scrFile, new File(FilePaths.screenshotFolderPath + "/" + destFile)); 
			  } catch (IOException e) {
			   System.out.println("Image not transfered to screenshot folder");
			   e.printStackTrace();
			  }
			  return destFile;
			 }

}
