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
import task.steps.FillProch;
import task.steps.QvitkiWorkflow;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.logIn;

/**
 * Created by alexeya on 12.07.2019.
 */

@Listeners({CustomTestListener.class})

public class ProchDo {

    //Полное ДО для Прочего документа - Обычная отправка

    @BeforeClass
    public void setuping() {
        BaseTest.setUpBrowser();
        open("http://postgresf.comita.lan:8080/ccwe/");
        logIn("megatester01", "gfhjkmnhb");
    }

    @Epic(value = "Создание и отправка документа")
    @Feature(value = "Прочий документ - минимальное заполнение полей")
    @Test(dataProvider = "loginVars", dataProviderClass = DataProvidersAny.class)
    @Severity(value = SeverityLevel.BLOCKER)
    public void workflowProch(String adresat, String urla, String login, String passWord) {
        String prochDocName = FillProch.fillingProch("обычная", adresat); // создаём документ ДОПРР
        Assert.assertTrue($(byText(prochDocName)).isDisplayed()); // проверяем, что документ создался
        MainPage.sendingDoc(prochDocName); // отправляем документ
        QvitkiWorkflow.otpravkaQvitkovProch(prochDocName, urla, login, passWord); //принимаем прочий и проверяем квитанции
    }

    @AfterClass(alwaysRun = true)
    public void closing() {
        BaseTest.closeAll();
    }
}
