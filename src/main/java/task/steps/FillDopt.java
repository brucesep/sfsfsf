package task.steps;

import io.qameta.allure.Step;
import task.HelpMeths;
import task.pages.MainPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static task.ElementsSearch.*;
import static task.HelpMeths.sleepTime;

/**
 * Created by alexeya on 11.07.2019.
 */
public class FillDopt extends MainPage {

    static public String forNomm;

    public static String docNumer = "document.documentInfo.sellerInfo.dateNumberDoc.numberDoc";
    public static String onzheRaz = "(он же грузоотправитель)";
    public static String onzheDva = "(он же грузополучатель)";
    public static String pokupatel = "Выбрать..";
    public static String podtverOk = "ОК";
    public static String valuta = "document.documentInfo.sellerInfo.currency.nameCurrent";
    public static String rosRub = "643 Российский рубль";
    public static String openTable = ".x-grid3-col.x-grid3-cell.x-grid3-td-mesaure";
    public static String autoCount = "Автоподсчет суммы";
    public static String naimTov = "name";
    public static String charTov = "property";
    public static String okei = "okei";
    public static String shtuki = "796 шт.";
    public static String kolvoMest = "countPlace";
    public static String kolvoPered = "nettoSend";
    public static String summaNds = "summWithNDS";
    public static String sohranit = "Сохранить";
    public static String closeAndSave = ".x-tool.x-tool-close";
    public static String zakritOk = "OK";

    @Step("ДОПТ. Заполнение таблицы.")
    public static void fillTable() {
        searchClass(openTable).doubleClick(); // открываем таблицу
        searchText(autoCount).click(); //заполняем таблицу
        sleepTime(500);
        searchName(naimTov).setValue("АВТО наименование товара");
        searchName(charTov).setValue("АВТО характеристика товара");
        searchName(okei).setValue("шт").click();
        searchTextColl(shtuki).get(0).isDisplayed();
        searchTextColl(shtuki).get(0).click();
        searchName(kolvoMest).sendKeys("\b");
        searchName(kolvoMest).setValue("15");
        searchName(kolvoPered).sendKeys("\b");
        searchName(kolvoPered).setValue("15");
        searchName(summaNds).sendKeys("\b");
        searchName(summaNds).setValue("1400");
        searchTextColl(sohranit).last().click();
    }

    @Step("ДОПТ. Продавец. Создание документа.")
    public static String fillingDopt(String adresat) {
        MainPage.creatDocAny(2);
        forNomm = "АВТО MIN " + HelpMeths.randomCount(); // генерим номер документа
        searchName(docNumer).setValue(forNomm); // заполняем поля документа
        searchText(onzheRaz).click();
        searchText(onzheDva).click();
        searchTextColl(pokupatel).get(0).click();
        searchText(adresat).isDisplayed();
        searchText(adresat).click();
        searchText(podtverOk).click();
        searchName(valuta).setValue("рос").click();
        searchTextColl(rosRub).get(0).isDisplayed();
        searchTextColl(rosRub).get(0).click();
        fillTable(); // заполняем таблицу
        searchClassColl(closeAndSave).last().click(); //сохраняем и закрываем документ
        $(byText("Сохранить изменения перед закрытием?")).isDisplayed();
        $(byText("Да")).click();
        $(byText("Документ успешно сохранен!")).isDisplayed();
        searchTextColl(zakritOk).last().click();
        Date date = new Date(); // приводим дату к нужному формату
        DateFormat dateF = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateF.format(date);
        String docNumer = "Документ о передаче товара №" + forNomm + " от " + dateStr; // генерим полное наименование документа
        return docNumer;
    }
}
