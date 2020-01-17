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
import task.steps.FillKschf;
import task.steps.QvitkiFastRun;
import task.steps.TitlesWorkflow;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.logIn;

/**
 * Created by alexeya on 11.07.2019.
 */



public class KschfDoThree {

    //Полное ДО для КСЧФ третьей функции. С отправкой квитанций и созданием титула

    @BeforeClass
    public void setuping() {
        BaseTest.setUpBrowser();
        open("http://postgresf.comita.lan:8080/ccwe/");
        logIn("megatester01", "gfhjkmnhb");
    }

    @Epic(value = "Создание и отправка документа")
    @Feature(value = "КСЧФ - ФУНКЦИЯ 3, минимальное заполнение полей")
    @Test(dataProvider = "loginVars", dataProviderClass = DataProvidersAny.class)
    @Severity(value = SeverityLevel.BLOCKER)
    public void workflowSchf(String adresat, String urla, String login, String passWord) {
        String kschfDocName = FillKschf.fillingKschf(3, adresat); // создаём документ КСЧФ с третьей функцией
        Assert.assertTrue($(byText(kschfDocName)).isDisplayed()); // проверяем, что документ создался
        MainPage.sendingDoc(kschfDocName); // отправляем документ
        QvitkiFastRun.workWithQvitki(kschfDocName, urla, login, passWord); //полный цикл квитанций после отправки документа (все проверки встроены в методы) ЛОГАУТ
        TitlesWorkflow.titleKschf(kschfDocName, urla, login, passWord); // создаем титул ЛОГАУТ
    }

    @AfterClass(alwaysRun = true)
    public void closing() {
        BaseTest.closeAll();
    }
}
