package task.steps;

import io.qameta.allure.Step;
import org.testng.Assert;
import task.ElementsSearch;
import task.pages.MainPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static task.HelpMeths.*;

/**
 * Created by alexeya on 10.07.2019.
 */
public class TitlesWorkflow {

    static private String titleName;

    @Step("Создание и отправка титула ДОПРР Покупателем. Проверка получения титула Продавцом.")
    public static void fullTitleRabots(String docName, String urla, String loginName, String passWord){ // создаем титул для ДОПРР со всеми проверками
        Date date = new Date();
        DateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = dateF.format(date);
        titleName = "Титул заказчика № " + FillDoppr.forNommerr + " от " + dateStr; // формируем имя титула с учетом формата дат
        open(urla);
        logIn(loginName, passWord); //логинимся с учетом хаб не хаб
        MainPage.titleDoptDoprr(docName);
        sleepTime(1000);
        Assert.assertTrue($(byText(titleName)).isDisplayed()); // проверяем, что создался
        logOut();
        open("http://postgresf.comita.lan:8080/ccwe/"); // логинимся Отправителем
        logIn("megatester01", "gfhjkmnhb");
        ElementsSearch.searchName(MainPage.papkaOtpr).click();
        while (true){         // из-за хаба поставлен бесконешный цикл, ВОЗМОЖНО ПРИДЕТСЯ ЗАМЕНИТЬ
            if ($(byText(titleName)).isDisplayed()){
                break;
            } else{
                ElementsSearch.searchName(MainPage.papkaOtpr).click();
            }
        }
        Assert.assertTrue($(byText(titleName)).isDisplayed());
        logOut();
    }

    @Step("Создание и отправка титула ДОПТ Покупателем. Проверка получения титула Продавцом.")
    public static void fullTitleTovar(String docName, String urla, String loginName, String passWord){ // создаем титул для ДОПТ со всеми проверками
        Date date = new Date();
        DateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = dateF.format(date);
        titleName = "Титул покупателя № " + FillDopt.forNomm + " от " + dateStr; // формируем имя титула с учетом формата дат
        open(urla);
        logIn(loginName, passWord); //логинимся с учетом хаб не хаб
        MainPage.titleDoptDoprr(docName);
        sleepTime(1000);
        Assert.assertTrue($(byText(titleName)).isDisplayed()); // проверяем что создался
        logOut();
        open("http://postgresf.comita.lan:8080/ccwe/"); // логинимся Отправителем
        logIn("megatester01", "gfhjkmnhb");
        ElementsSearch.searchName(MainPage.papkaOtpr).click();
        while (true){         // из-за хаба поставлен бесконешный цикл, ВОЗМОЖНО ПРИДЕТСЯ ЗАМЕНИТЬ
            if ($(byText(titleName)).isDisplayed()){
                break;
            } else{
                ElementsSearch.searchName(MainPage.papkaOtpr).click();
            }
        }
        Assert.assertTrue($(byText(titleName)).isDisplayed());
        logOut();
    }

    @Step("Создание и отправка титула СЧФ Покупателем. Проверка получения титула Продавцом.")
    public static void titleSchf(String docName, String urla, String loginName, String passWord){ //создаём титул для СЧФ со всеми проверками
        Date date = new Date();
        DateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = dateF.format(date);
        titleName = "Титул покупателя № " + FilLSchf.forNomer + " от " + dateStr; //формируем имя титула с учетом формата дат
        open(urla);
        logIn(loginName, passWord); //логинимся с учетом хаб не хаб
        MainPage.titleSchf(docName); //отправляем титул
        sleepTime(1000);
        Assert.assertTrue($(byText(titleName)).isDisplayed()); //проверяем, что создался
        logOut();
        open("http://postgresf.comita.lan:8080/ccwe/"); // логинимся Отправителем
        logIn("megatester01", "gfhjkmnhb");
        ElementsSearch.searchName(MainPage.papkaOtpr).click();
        while (true){         // из-за хаба поставлен бесконешный цикл, ВОЗМОЖНО ПРИДЕТСЯ ЗАМЕНИТЬ
            if ($(byText(titleName)).isDisplayed()){
                break;
            } else{
                ElementsSearch.searchName(MainPage.papkaOtpr).click();
            }
        }
        Assert.assertTrue($(byText(titleName)).isDisplayed()); // проверка что титул отображается
        logOut();
    }

    @Step("Создание и отправка титула КСЧФ Покупателем. Проверка получения титула Продавцом.")
    public static void titleKschf(String docName, String urla, String loginName, String passWord){ //создаём титул для КСЧФ со всеми проверками
        Date date = new Date();
        DateFormat dateF = new SimpleDateFormat("dd.MM.yyyy");
        String dateStr = dateF.format(date);
        titleName = "Титул покупателя № " + FilLSchf.forNomer + " от " + dateStr; //формируем имя титула с учетом формата дат
        open(urla);
        logIn(loginName, passWord); //логинимся с учетом хаб не хаб
        MainPage.titleKschf(docName); //отправляем титул
        sleepTime(1000);
        Assert.assertTrue($(byText(titleName)).isDisplayed()); //проверяем, что создался
        logOut();
        open("http://postgresf.comita.lan:8080/ccwe/"); // логинимся Отправителем
        logIn("megatester01", "gfhjkmnhb");
        sleepTime(500);
        ElementsSearch.searchName(MainPage.papkaOtpr).click();
        while (true){         // из-за хаба поставлен бесконешный цикл, ВОЗМОЖНО ПРИДЕТСЯ ЗАМЕНИТЬ
            if ($(byText(titleName)).isDisplayed()){
                break;
            } else{
                ElementsSearch.searchName(MainPage.papkaOtpr).click();
            }
        }
        Assert.assertTrue($(byText(titleName)).isDisplayed()); // проверка что титул отображается
        logOut();
    }

}
