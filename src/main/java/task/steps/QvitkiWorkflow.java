package task.steps;

import io.qameta.allure.Step;
import org.testng.Assert;
import task.ElementsSearch;
import task.pages.MainPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.ElementsSearch.searchText;
import static task.HelpMeths.logIn;
import static task.HelpMeths.logOut;
import static task.pages.MainPage.*;

/**
 * Created by alexeya on 10.07.2019.
 */
public class QvitkiWorkflow {

    @Step("Прочий документ. Квитанции полный цикл.")
    public static void otpravkaQvitkovProch(String docName, String urla, String loginName, String passWord){ //отправка квитанций по Прочим докам
        checkQvitKol(1, docName, 0); //проверяем что квитанция пришла
        logOut();
        open(urla); // логинимся хаб не хаб
        logIn(loginName, passWord);
        otprPrinat(docName); //Приняли прочий и проверили что есть квитация
        logOut();
        open("http://postgresf.comita.lan:8080/ccwe/"); // логинимся отправителем
        logIn("megatester01", "gfhjkmnhb");
        searchText(MainPage.papkaOtpr).click(); // переходим в папку Отправленные
        checkQvitKol(2, docName, 0);  //проверили количество квитков у отправителя
        logOut();
    }

    @Step("Продавец. Создание квитанции после отправки документа.")
    public static void otpravkaQvitkaOtpr01(String docName){ //первая отправка квитанций Отправителем
        checkQvitKol(1, docName, 0); //проверяем, что квитанция есть
        sendQvitUniversal(docName, 1); //отправляем квитанции
        checkQvitKol(2, docName, 0); //проверяем что все квитанции на месте
        logOut(); // разлогиниваемся
    }

    @Step("Покупатель. Создание квитанций после получения документа.")
    public static void otpavkaQvitkovPoluch01(String docName, String urla, String loginName, String passWord){ //отправка квитанций Получателем
        open(urla); //открываем нужный стенд (хаб не хаб)
        logIn(loginName, passWord); // логинимся
        sendQvitUniversal(docName, 2); // отправляем квитанции
        checkQvitKol(4, docName, 1); // проверяем количество квитанций
        sendQvitUniversal(docName, 2); //отправляем второй пак квитанций
        checkQvitKol(5, docName, 1); //проверяем количество квитанций
        logOut(); // разлогиниваемся
    }

    @Step("Продавец. Проверка количества квитанций.")
    public static void proverkaQvitkovPoluch01(String docName){ //проверка, что все квитанции пришли Отправителю
        open("http://postgresf.comita.lan:8080/ccwe/"); //возвращаемся на 2АВ
        logIn("megatester01", "gfhjkmnhb"); //логинимся Отправителем
        searchText(papkaOtpr).click(); //переходим в папку Отправленные
        checkQvitKol(3, docName, 0); //проверяем что все квитанции на месте
        logOut(); // разлогиниваемся
    }

    @Step("Продавец. Создание квитанций и уточнения документа.")
    public static void proverkaQvitkovUtoch01(String docName){ //уточнение и квитанции у Отправителя
        open("http://postgresf.comita.lan:8080/ccwe/");
        logIn("megatester01", "gfhjkmnhb"); //логинимся Отправителем
        ElementsSearch.searchName(papkaOtpr).click(); //переходим в папку Отправленные
        checkQvitKol(4, docName, 0); //проверяем количество квитанций
        sendQvitUniversal(docName, 1);//отправляем квитанции
        checkQvitKol(5, docName, 0); //проверяем количество квитанций
        utochOtpravka(docName); //создаем уточнение
        Assert.assertTrue($(byText(docName)).isDisplayed());//проверяем что документ создался
        logOut(); //разлогиниваемся
    }

    @Step("Покупатель. Проверка количества квитанций после запроса уточнения.")
    public static void proverkaQvitkovOtprLast(String docName, String urla, String loginName, String passWord){//последняя проверка квитанций у Получателя
        open(urla);
        logIn(loginName, passWord); //логинимся хаб не хаб
        checkQvitKol(7, docName, 1); //проверяем что все квитанции на месте
        logOut(); //разлогиниваемся
    }
}
