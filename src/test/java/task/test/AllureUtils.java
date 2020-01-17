package task.test;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by alexeya on 17.01.2020.
 */
public class AllureUtils {
    @Attachment(value = "{0}", type = "image/png")
    public static byte[] makeScreenshot(String attachName) {
        byte[] array = {1};

        try {
            return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }

        return array;
    }
}
