package Test;
import Request.RequestFactory;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.TestResult;
import Utils.ConfigRead;
import Utils.ExtentReportUtils;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    String configFilename;
    Properties configProperties;
    RequestFactory requestFactory;

    String htmlReportFilename;
    String currentworkingDirectory;

    ExtentReportUtils extentReport;

    @BeforeSuite
    public void preSetup() throws IOException {
        currentworkingDirectory = System.getProperty("user.dir");
        configFilename = currentworkingDirectory + "/src/main/resources/config.properties";
        configProperties = ConfigRead.readConfigProperties(configFilename);
        htmlReportFilename = currentworkingDirectory + "/src/main/resources/reports/htmlReport.html";
        extentReport = new ExtentReportUtils(htmlReportFilename);
    }

    @BeforeClass
    public void setup() {
        extentReport.createTestCase("setup: Update all configurrations");
        RestAssured.baseURI = configProperties.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(configProperties.getProperty("portNumber"));
        extentReport.addLog(Status.INFO, "Base Url - " + configProperties.getProperty("baseUrl"));
        extentReport.addLog(Status.INFO, "Base port - " + configProperties.getProperty("portNumber"));
        requestFactory = new RequestFactory();

    }

   /* @AfterMethod
    public void postTestCheck(TestResult result) {
   *//*     if (result.getStatus() == ITestResult.SUCCESS) {
            extentReport.addLog(Status.PASS, "All Test Steps passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            extentReport.addLog(Status.FAIL, "One or more Test Steps Failed");
        } else {
            extentReport.addLog(Status.SKIP, "One or more Test Steps Skipped");
        }*//*
    }*/

    @AfterClass
    public void cleanup() {
        RestAssured.reset();
        extentReport.closeReports();
    }
}
