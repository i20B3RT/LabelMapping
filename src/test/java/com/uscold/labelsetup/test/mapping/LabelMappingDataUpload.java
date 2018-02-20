package com.uscold.labelsetup.test.mapping;

import com.uscold.labelsetup.test.Abstract;
import com.uscold.labelsetup.test.utility.AssistPage;
import org.openqa.selenium.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class LabelMappingDataUpload extends Abstract {

    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/test-input/LabelSetup.xlsx";

    @DataProvider()
    public Object[][] getLabelData() {
        Object data[][] = AssistPage.getTestData("LabelMapping",TESTDATA_SHEET_PATH);
        return data;
    }


//priority = 1, description = "TC: Search drivers at the whse level",
    @Test(priority = 1,dataProvider = "getLabelData")
    public void LabelMapping(String whseNum,String labelForm, String labelType) throws InterruptedException {

        extentTest =extent.startTest("TC: Validate driver on the Yard overlook page at warehouse"+whseNum +".");

        Calendar dateTwo = Calendar.getInstance();
        Date dateOne = dateTwo.getTime();
        DateFormat dateForm = new SimpleDateFormat("YYMMddhhmmss");
        String tDay = dateForm.format(dateOne);

        AssistPage.chooseMod(driver,"Label Setup");
//        AssistPage.chooseWarehouse(driver, WHSE_NUM);


        AssistPage.sendInput(driver,"id","gs_lblFormat",labelForm);
        driver.findElement(By.id("gs_lblFormat")).sendKeys(Keys.ENTER);

        //xpath for clicking on the first radio button
        AssistPage.click(driver.findElement(By.xpath("//tr[2][@class='ui-widget-content jqgrow ui-row-ltr']/td[1]")));
        AssistPage.click(driver.findElement(By.id("editRecord")));

        driver.findElement(By.xpath("//table[@id='list']//a[contains(@class, 'chosen-single')]")).click();
        List<WebElement> accTypes = driver.findElements(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        accTypes.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labelType)).findFirst().get().click();
//        click(accTypes);

//        System.out.println("[AssistPage] [INFO] [Label mapped Successfully] [update_ts: " + tDay+ "] Label Format "+labelForm+" - Label Type "+labelType+" at warehouse " + whseNum);

//        System.out.println("[AssistPage] [INFO] [Added Successfully] [update_ts: " + tDay.substring(3,2)+"-"+tDay.substring(6,2)+"20"+tDay.substring(1,2)+" "+tDay.substring(7,2)+":"+tDay.substring(9,2)+":"+tDay.substring(11,2) + "]" + "This label format "+labelForm+" was successfully updated to this label type "+labelType+" at warehouse " + whseNum);

        AssistPage.click(driver.findElement(By.id("updateRecord")));
//        AssistPage.isElementPresen(driver.findElement(By.id("editRecord")));
//        System.out.println("[AssistPage] [INFO] [Added Successfully] [update_ts: " + tDay.substring(3,2)+"-"+tDay.substring(6,2)+"20"+tDay.substring(1,2)+" "+tDay.substring(7,2)+":"+tDay.substring(9,2)+":"+tDay.substring(11,2) + "]" + "This label format "+labelForm+" was successfully updated to this label type "+labelType+" at warehouse " + whseNum);
        System.out.println("[AssistPage] [INFO] [Label mapped Successfully] [update_ts: " + tDay+ "] Label Format "+labelForm+" - Label Type "+labelType+" at warehouse " + whseNum);

    }

    @DataProvider()
    public Object[][] getLabelAssignmentData() {
        Object data[][] = AssistPage.getTestData("LabelAssignment",TESTDATA_SHEET_PATH);
        return data;
    }

    @Test(priority = 2,dataProvider = "getLabelAssignmentData")
    public void LabelAssignment(
            String whsenumber     ,
            String labeltype      ,
            String labelformat    ,
            String numofcopies    ,
            String customernumber ,
            String consigneenumber,
            String productnumber    ) throws InterruptedException {

        extentTest = extent.startTest("Label format " + labelformat + " was mapped to type " + labeltype + " at warehouse " + whsenumber + ".");

        Calendar dateTwo = Calendar.getInstance();
        Date dateOne = dateTwo.getTime();
        DateFormat dateForm = new SimpleDateFormat("YYMMddhhmmss");
        String tDay = dateForm.format(dateOne);

        AssistPage.chooseMod(driver,"Label Setup");
//        AssistPage.chooseWarehouse(driver, WHSE_NUM);

//        //Navigate to Label assignment
//        driver.findElement(By.id("searchText")).clear();
//        driver.findElement(By.id("searchText")).sendKeys("label setup");
//        //driver.manage().wait();
//        driver.findElement(By.linkText("Label Setup")).click();

        //Click on the Label Mapping tab
        click(driver.findElement(By.xpath("//div[@id='labelAsignment']/a/span")));

        //Click on the Add new record button
        click(driver.findElement(By.id("addNewRecord")));

        //This will click on the label type dropdown to display the list
        click(driver.findElement(By.xpath("//div[@title='Please Select The Label Type.']/a[contains(@class, 'chosen-single')]/span")));
        //This will create the list
        List<WebElement> accTypess = driver.findElements(By.xpath(".//div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        //This will filter and match and click on the appropriate value
        click(accTypess.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labeltype)).findFirst().get());
//        click(accTypes);


        //This will click on the label format dropdown to display the list
        click(driver.findElement(By.xpath("//div[@title='Please Select The Label Format.']/a[contains(@class, 'chosen-single')]/span")));
        //This will create the list
        List<WebElement> accTypesss = driver.findElements(By.xpath("//div[@title='Please Select The Label Format.']/div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        //This will filter and match and click on the appropriate value
        click(accTypesss.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(labelformat)).findFirst().get());
//        click(accTypes);

        //This needs to be converted to string and send value to field.
        //--|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        //--|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//        AssistPage.sendInput(driver, "xpath", "//input[@title='Enter The # of Copies.']", numofcopies.toString());
        AssistPage.sendInput(driver, "xpath", "//input[@title='Enter The # of Copies.'][@name='noOfCopies']", numofcopies.toString());
        System.out.println(numofcopies);
        System.out.println(numofcopies.toString());
        driver.findElement(By.xpath("//input[@title='Enter The # of Copies.'][@name='noOfCopies']")).sendKeys(numofcopies.toString());

        //This will click on the label format dropdown to display the list
        driver.findElement(By.xpath("//div[@title='Please Select The Customer #.']/a[contains(@class, 'chosen-single')]/span")).click();
        //This will create the list
        List<WebElement> accTypessss = driver.findElements(By.xpath("//div[@title='Please Select The Customer #.']/div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
        //This will filter and match and click on the appropriate value
        driver.findElement(By.xpath("//div[@title='Please Select The Customer #.']/div[contains(@class, 'chosen-drop')]/div[contains(@class, 'chosen-search')]/input")).sendKeys(customernumber.toString());
        accTypessss.stream().filter(el -> el.getAttribute("innerText").trim().contains(customernumber)).findFirst().get().click();
//        click(accTypes);


        driver.findElement(By.xpath("//input[@class='consigneeTextBox editable ui-autocomplete-input']")).sendKeys(consigneenumber);
        click(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all']/li[@class='ui-menu-item']/a[contains(text(),'" + consigneenumber + "')]"));
        //Validate that the product description was autofilled correctly
        WebElement descProd = driver.findElement(By.xpath("//tr[@id='OEDCREATEPOPUP-1']/td[contains(@class, 'wordWrap')][2]"));
        if (!descProd.isDisplayed() && !descProd.getText().toLowerCase().contains(consigneenumber))
            throw new RuntimeException("Failed to click on product code from autosuggest");


        if (!productnumber.trim().equals("")) {
            //This will click on the label format dropdown to display the list
            driver.findElement(By.xpath("//div[@title='Please Select The Product.']/a[contains(@class, 'chosen-single')]/span")).click();
            //This will create the list
            List<WebElement> accTypesssss = driver.findElements(By.xpath("//div[@title='Please Select The Product.']/div[@class='chosen-drop']//ul[@class='chosen-results']/li"));
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accTypes);
            //This will filter and match and click on the appropriate value
            accTypesssss.stream().filter(el -> el.getAttribute("innerText").trim().equalsIgnoreCase(productnumber)).findFirst().get().click();
//        click(accTypes);
        }


        AssistPage.click(driver.findElement(By.xpath("//button[@id='saveNewRecord']")));

        //check whether the assginment was saved
        WebElement statusMsg = driver.findElement(By.id("msgDisp"));
        try {
            if (!statusMsg.isDisplayed() && !statusMsg.getText().contains("Label Assignment created Succesfully."))
                throw new RuntimeException("[ERROR] This label form " + labelformat + " " + labeltype + " failed to be assigned to " + customernumber + "/" + consigneenumber + "/" + productnumber + " at the " + whsenumber + " warehouse.");
        } catch (RuntimeException e) {
            System.out.println("[ERROR] This label form " + labelformat + " " + labeltype + " failed to be mapped");
            e.printStackTrace();
        }

        //This will print the action
        System.out.println("[AssistPage] [INFO] [Added Successfully] [update_ts: " + tDay.substring(3,2)+"-"+tDay.substring(6,2)+"20"+tDay.substring(1,2)+" "+tDay.substring(7,2)+":"+tDay.substring(9,2)+":"+tDay.substring(11,2)+ "]" + " This label format " + labelformat + " was mapped to this label type=================>" + labeltype + " and assigned to " + customernumber + "/" + consigneenumber + "/" + productnumber + " at the " + whsenumber + " warehouse.");


    }

}

