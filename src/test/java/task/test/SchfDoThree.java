package task.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task.pages.MainPage;
import task.steps.FilLSchf;
import task.steps.QvitkiFastRun;
import task.steps.TitlesWorkflow;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.logIn;

/**
 * Created by alexeya on 10.07.2019.
 */

@Listeners({CustomTestListener.class})

public class SchfDoThree {

    //Полное ДО для СЧФ третьей функции. С отправкой квитанций и созданием титула

    @BeforeClass
    public void setuping() {
        BaseTest.setUpBrowser();
        open("http://postgresf.comita.lan:8080/ccwe/");
        logIn("megatester01", "gfhjkmnhb");
    }


    @Epic(value = "Создание и отправка документа")
    @Feature(value = "СЧФ - ФУНКЦИЯ 3, минимальное заполнение полей")
    @Test(dataProvider = "loginVars", dataProviderClass = DataProvidersAny.class)
    @Severity(value = SeverityLevel.BLOCKER)
    public void workflowSchf(String adresat, String urla, String login, String passWord) {
        String schfDocname = FilLSchf.fillingSchf(3, adresat); // создаём документ СЧФ с третьей функцией
        Assert.assertTrue($(byText(schfDocname)).isDisplayed()); // проверяем, что документ создался
        MainPage.sendingDoc(schfDocname); // отправляем документ
        QvitkiFastRun.workWithQvitki(schfDocname, urla, login, passWord); //полный цикл квитанций после отправки документа (все проверки встроены в методы) ЛОГАУТ
        TitlesWorkflow.titleSchf(schfDocname, urla, login, passWord); // создаем титул ЛОГАУТ
    }

    @AfterClass(alwaysRun = true)
    public void closing() {
        BaseTest.closeAll();
    }
}
