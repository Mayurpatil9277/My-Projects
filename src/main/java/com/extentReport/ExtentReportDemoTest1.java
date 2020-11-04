
package com.extentReport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.lang.model.element.Element;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportDemoTest1 
{
	
	@Test
    public void testMethodPass()
    {
     Assert.assertTrue(true);
    }
     
    @Test
    public void testMethodFail()
    {
     Assert.assertTrue(false);
    }
     
    @Test
    public void testMethodSkip()
    {
     throw new SkipException("Skipped Intentionally");
    }
}