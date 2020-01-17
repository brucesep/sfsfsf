package task.test;

import io.qameta.allure.Attachment;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by alexeya on 12.07.2019.
 */
public class CustomTestListener extends TestListenerAdapter {

    @Attachment
    public String getInfo() {
        return getWebDriver().getCurrentUrl();
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        AllureUtils.makeScreenshot("Failure Screenshot for " + tr.getName());
        getInfo();
        getJsErrors();
    }

    @Attachment
    private String getJsErrors() {
        String jsErrors = "";
        Logs log = getWebDriver().manage().logs();

        List<LogEntry> logsEntries = log.get("browser").getAll();

        for (LogEntry entry : logsEntries) {
            if (entry.getLevel().toString().equals("SEVERE")) {
                jsErrors += entry.toString() + "\n";
            }
            if (entry.getMessage().toLowerCase().contains("mixed") && entry.getMessage().toLowerCase().contains("content")) {
                jsErrors += entry.toString() + "\n";
            }
        }

        return jsErrors;
    }



 //   private Logger log = LoggerFactory.getLogger(CustomTestListener.class);

    //    @Override
//    public void onTestSuccess(ITestResult result){
//        makeScreenshot();
//        log.info("Test SUCCESS: " + result.getName());
//    }

/*    @Override
    public void onTestFailure(ITestResult result){
        makeScreenshot();
        log.info("Test FAILED: " + result.getName());
        if (result.getThrowable() != null){
            result.getThrowable().printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot(){
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    } */

}
