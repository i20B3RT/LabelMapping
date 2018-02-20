package com.uscold.labelsetup.test;

/**
 * ********************************************
 * * Created by ${"Robert Morales"} on 2/14/2018.
 * ********************************************
 **/



import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;


public abstract class BaseTestNGTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTestNGTest.class);

    @BeforeClass
    public void beforeTestConfig(final ITestContext testContext) {
        LOGGER.info(String.format("starting test %s", this.getClass().getSimpleName()));

    }


    @AfterClass
    public void afterTestconfig(final ITestContext testContext) {
        LOGGER.info(String.format("Stopping test %s", this.getClass().getSimpleName()));
    }

}
