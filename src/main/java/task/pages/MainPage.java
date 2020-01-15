package task.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import task.steps.TitleCreate;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static task.ElementsSearch.*;
import static task.HelpMeths.sleepTime;

/**
 * Created by alexeya on 08.07.2019.
 */
public class MainPage {

    public static String newDoc = "newDocumetns";
    public static String docList = ".x-menu-list .x-menu-list-item";
    public static String chernoviki = "Черновики";
    public static String obrabotkaDocov = "Подписать и Отправить";
    public static String obrabotkaZaver = "обработка завершена";
    public static String zakrit = "Закрыть";
    public static String qvitancii = "Квитанции";
    public static String papkaOtpr = "Отправленные";
    public static String otpravkaQvitov = "Подписать и отправить...";
    public static String obrabotkaDocumenta = "Обработка документа...";
    public static String papkaVhod = "Входящие";
    public static String prichinaUtoch = "msg";
    public static String podpisIOtpravkaUtoch = "Подписать и Отправить";
    public static String otpavitPrin = "Отправить";

    public static void titleDoptDoprr(String docName){
        $(byText(docName)).closest("tr").find(byText("Сформировать титул")).click();
        TitleCreate.titulTovarWork();
        sleepTime(500);
        searchText(papkaVhod).click();
    }

    public static void titleKschf(String docName){
        $(byText(docName)).closest("tr").find(byText("Сформировать титул")).click();
        TitleCreate.titulForKschf();
        sleepTime(500);
        searchText(papkaVhod).click();
    }

    public static void titleSchf(String docName) {
        $(byText(docName)).closest("tr").find(byText("Сформировать титул")).click();
        TitleCreate.titulForSchf();
        searchText(papkaVhod).click();
    }

    public static void otprPrinat(String docName){
        searchText(papkaOtpr).click();
        sleepTime(1000);
        searchText(papkaVhod).click();
        while (true){       // цикл для ХАБа. Ждём когда в списке появится документ
            if (searchText(docName).isDisplayed()){ // если документ отображается - выходим из цикла
                break;
            } else { //если не отображается снова кликаем в папку
                searchText(papkaVhod).click();
            }
        }
        $(byText(docName)).closest("tr").find(byText("Принять")).click(); //жмём кнопку "Принять"
        sleepTime(1000);
        $(byText("Требуется подпись документа Вашей электронной подписью.")).isDisplayed(); // принимаем документа
        sleepTime(1000);
        searchTextColl(otpavitPrin).last().click();
        $(byText("Требуется подпись документа Вашей электронной подписью.")).waitUntil(Condition.disappear, 10000);
        sleepTime(500);
        for (int i = 0; i < 100; i++){
            if ($(byText(docName)).closest("tr").find(byText("Принять")).isDisplayed()){
                searchText(papkaVhod).click();
            } else {
                $(byText(docName)).click();
                break;
            }
        }
        MainPage.checkQvitKol(2, docName, 1); // проверяем квитанции
    }

    public static void utochOtpravka(String docName) { //создание уточнения
        $(byText(docName)).closest("tr").find(byText("Сформировать уточнение")).click(); //формируем уточнение
        sleepTime(1000); //таймаут на всякий случай
    }

    public static void zaprosUtoch(String docName) { //запрос уточнения
        $(byText(docName)).closest("tr").find(byText("Запросить уточнение")).click(); //кликаем в ссылку
        searchName(prichinaUtoch).waitUntil(Condition.appear, 3000).setValue("АВТО Причина уточнения");//указываем причину уточнения
        sleepTime(1000);
        searchTextColl(podpisIOtpravkaUtoch).last().click(); //подписываем
        searchName(prichinaUtoch).waitUntil(Condition.disappear, 10000);//дожидаемся закрытия окна
    }

    public static void sendQvitUniversal(String docName, int papka) {
        if (papka == 1) { //переходим в папку Отправитель/Получатель смотря кто создаёт квитанции
            searchText(papkaOtpr).click();  // 1 - Отправленные
        } else { // 2 - Входящие
            sleepTime(2000);
            searchText(papkaVhod).click();
            while (true){       // цикл для ХАБа. Ждём когда в списке появится документ
                if (searchText(docName).isDisplayed()){ // если документ отображается - выходим из цикла
                    break;
                } else { //если не отображается снова кликаем в папку
                    searchText(papkaVhod).click();
                }
            }
        }
        $(byText(docName)).closest("tr").find(byText("Отправить извещение о получении")).waitUntil(Condition.appear, 3000).click(); //начинаем отправлять квитанции
        sleepTime(1000);
        searchTextColl(otpravkaQvitov).last().waitUntil(Condition.appear, 7000).click();  // пробуем дождаться появления и исчезновения кнопки через другой метод
        searchTextColl(obrabotkaDocumenta).last().waitUntil(Condition.disappear, 7000); // ждём когда исчезнет окно с квитанциями.
        sleepTime(500);
        if (papka == 1) { //ещё ращ обновляем папку, в которой находимся
            searchText(papkaOtpr).click();
        } else {
            searchText(papkaVhod).click();
        }
    }

    public static void checkQvitKol(Integer kolvo, String docName, Integer papka) {
        $(byText(docName)).click(); //проверяем количество квитанций. Отправленные - 0, Входящие - 1
        while (true) {
            searchTextColl(qvitancii).get(papka).click();
            sleepTime(1000);
            ElementsCollection tsepochka0 = $$(By.xpath("//*[@class='x-panel-body x-panel-body-noborder']//div"));
            ElementsCollection tsepochka = tsepochka0.last().$$(By.xpath("table"));
            if (tsepochka.size() == kolvo) {
                searchTextColl(zakrit).last().click();
                break;
            } else {
                searchTextColl(zakrit).last().click();
            }
        }
    }

    public static void creatDocAny(Integer documentum) {
        searchId(newDoc).doubleClick();
        searchClassColl(docList).get(documentum).doubleClick();
    }

    @Step("Продавец. Отправка документа.")
    public static void sendingDoc(String docName) {
        searchText(chernoviki).click();
        $(byText(docName)).closest("tr").find(byText("Отправить документ")).click();
        searchTextColl(obrabotkaDocov).last().isDisplayed();
        searchTextColl(obrabotkaDocov).last().click();
        searchTextColl(obrabotkaZaver).last().waitUntil(Condition.appear, 1000);
        searchTextColl(zakrit).last().click();
    }
}
