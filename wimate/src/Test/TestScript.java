package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import POM.Dreamboard;
import TestNG.BaseClass;

public class TestScript extends BaseClass {

    @Test
    public void addusers() throws Exception {

        String path = "./testdata/Testdata.xlsx";

        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sh = wb.getSheet("Sheet1");

        DataFormatter df = new DataFormatter();
        Dreamboard db = new Dreamboard(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        driver.switchTo().frame(db.getIframe());

        for (int i = 1; i <= sh.getLastRowNum(); i++) {

            String username = df.formatCellValue(sh.getRow(i).getCell(0));
            String phone    = df.formatCellValue(sh.getRow(i).getCell(1));
            String email    = df.formatCellValue(sh.getRow(i).getCell(2));
            String state    = df.formatCellValue(sh.getRow(i).getCell(3));
            String address  = df.formatCellValue(sh.getRow(i).getCell(4));
            String dob      = df.formatCellValue(sh.getRow(i).getCell(5));
            String gender   = df.formatCellValue(sh.getRow(i).getCell(6));

            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'page-loader')]")));

            db.getAddButton().click();
            db.getUserName().sendKeys(username);
            db.getPhoneNumber().sendKeys(phone);
            db.getEmail().sendKeys(email);
            db.selectState(state);
            db.getAddress().sendKeys(address);
            db.getDate().sendKeys(dob);
            db.selectGender(gender);
            db.getSubmit().click();

            String msg = driver.findElement(By.id("swal2-html-container")).getText();
            System.out.println(msg);

            Row row = sh.getRow(i);
            Cell cell = row.getCell(7);
            if (cell == null) {
                cell = row.createCell(7);
            }

            if (msg.toLowerCase().contains("success")) {

                db.getPassButton().click();
                cell.setCellValue("PASS");

            } else if (msg.toLowerCase().contains("duplicate")) {

                db.getWarningOkButton().click();
                db.getDiscardButton().click();
                db.getConfirmCancelButton().click();
                cell.setCellValue("fail");

            } else {

                cell.setCellValue("UNKNOWN");
            }
        }

        fis.close();

        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);

        fos.close();
        wb.close();

        System.out.println("Excel updated successfully");
    }
}