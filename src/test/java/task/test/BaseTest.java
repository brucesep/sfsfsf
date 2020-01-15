package task.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by alexeya on 08.07.2019.
 */
public class BaseTest {

    static ChromeDriver webDriver;

    public static void setUpBrowser() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("load-extension=C:\\Users\\alexeya\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\iifchhfnnmpdbibifmljnfjhpififfog\\1.2.7_1");
        options.addArguments("start-maximized");  //задаем размер окна
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        webDriver = new ChromeDriver(options); // передаем в хром все наши опции
        webDriver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS); //длительность неявных ожиданий
        WebDriverRunner.setWebDriver(webDriver);
        Configuration.screenshots = false;  //запрещаем селениду делать скрины
     }

    public static void closeAll() {
        webDriver.close(); // закрываем драйвер в самом конце
    }
}
