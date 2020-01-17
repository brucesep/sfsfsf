package task.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import task.pages.MainPage;
import task.steps.FilLSchf;
import task.steps.QvitkiFastRun;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.logIn;

/**
 * Created by alexeya on 08.07.2019.
 */


public class SchfDoOne {

    //Полное ДО для СЧФ первой функции. С отправкой квитанций и созданием уточнения

    @BeforeClass
    public void setuping(){
        BaseTest.setUpBrowser();
        open("http://postgresf.comita.lan:8080/ccwe/");
        logIn("megatester01", "gfhjkmnhb");
    }

    @Epic(value = "Создание и отправка документа")
    @Feature(value = "СЧФ - ФУНКЦИЯ 1, минимальное заполнение полей")
    @Test(dataProvider = "loginVars", dataProviderClass = DataProvidersAny.class)
    @Severity(value = SeverityLevel.BLOCKER)
    public void workflowSchf(String adresat, String urla, String login, String passWord){
        String schfDocname = FilLSchf.fillingSchf(1, adresat); // создаём документ СЧФ с первой функцией
        Assert.assertTrue($(byText(schfDocname)).isDisplayed()); // проверяем, что документ создался
        MainPage.sendingDoc(schfDocname); // отправляем документ
        QvitkiFastRun.workWithQvitki(schfDocname, urla, login, passWord); //полный цикл квитанций после отправки документа (все проверки встроены в методы) C ЛОГАУТОМ
        QvitkiFastRun.utochnenia(schfDocname, urla, login, passWord); // создание уточнения и проверка квитанций (все проверки встроены в методы)
        //заканчивается логаутом
    }

    @AfterClass(alwaysRun = true)
    public void closing(){
        BaseTest.closeAll();
    }
}
