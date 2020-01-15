package task.steps;

import io.qameta.allure.Step;
import task.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.logIn;
import static task.HelpMeths.logOut;
import static task.HelpMeths.sleepTime;

/**
 * Created by alexeya on 10.07.2019.
 */
public class ZaprosUtochnenia {

    @Step("Покупатель. Запрос уточнения.")
    public static void  zaprosUtochnenia(String docName, String urla, String loginName, String passWord){
        open(urla);
        logIn(loginName, passWord);
        MainPage.zaprosUtoch(docName);
        sleepTime(1500);
        MainPage.checkQvitKol(6, docName, 1);
        logOut();
    }
}
