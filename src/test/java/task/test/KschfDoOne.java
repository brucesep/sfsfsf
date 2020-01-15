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
import task.steps.FillKschf;
import task.steps.QvitkiFastRun;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.logIn;

/**
 * Created by alexeya on 10.07.2019.
 */

@Listeners({CustomTestListener.class})

public class KschfDoOne {

    //Полное ДО для КСЧФ первой функции. С отправкой квитанций и созданием уточненения

    @BeforeClass
    public void setuping() {
        BaseTest.setUpBrowser();
        open("http://postgresf.comita.lan:8080/ccwe/");
        logIn("megatester01", "gfhjkmnhb");
    }

    @Epic(value = "Создание и отправка документа")
    @Feature(value = "КСЧФ - ФУНКЦИЯ 1, минимальное заполнение полей")
    @Test(dataProvider = "loginVars", dataProviderClass = DataProvidersAny.class)
    @Severity(value = SeverityLevel.BLOCKER)
    public void workflowKschf(String adresat, String urla, String login, String passWord) {
        String kschfDocName = FillKschf.fillingKschf(1, adresat); //создаем КСЧФ первой функции
        Assert.assertTrue($(byText(kschfDocName)).isDisplayed()); // проверяем что документ создался
        MainPage.sendingDoc(kschfDocName); // отправляем документ
        QvitkiFastRun.workWithQvitki(kschfDocName, urla, login, passWord); //полный цикл квитанций после отправки документа (все проверки встроены в методы) C ЛОГАУТОМ
        QvitkiFastRun.utochnenia(kschfDocName, urla, login, passWord); // создание уточнения и проверка квитанций (все проверки встроены в методы)
    }

    @AfterClass(alwaysRun = true)
    public void closing() {
        BaseTest.closeAll();
    }
}
