package task.steps;

import io.qameta.allure.Step;
import task.pages.MainPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static task.ElementsSearch.*;
import static task.HelpMeths.randomCount;
import static task.HelpMeths.sleepTime;

/**
 * Created by alexeya on 12.07.2019.
 */
public class FillDoppr extends MainPage {

    public static String docNumer = "document.documentInfo.dateNumberDoc.numberDoc";
    public static String zakazchik = "Выбрать..";
    public static String vibor = "ОК";
    public static String valuta = "document.documentInfo.currency.nameCurrent";
    public static String rosRub = "643 Российский рубль";
    public static String openTable = ".x-grid3-col.x-grid3-cell.x-grid3-td-startDate";
    public static String calends = ".x-form-trigger.triger-date-n-style";
    public static String dayOne = "//*[@class='x-date-inner']//tbody//tr//td";
    public static String nextTable = ".x-grid3-col.x-grid3-cell.x-grid3-td-nameWork";
    public static String autocount = "Автоподсчет суммы";
    public static String nameWork = "nameWork";
    public static String okei = "okei";
    public static String shtuki = "796 шт.";
    public static String kolich = "count";
    public static String priceNds = "priceWithNDS";
    public static String sohranitt = "Сохранить";
    public static String zakrTabl = "Ок";
    public static String closeAndSave = ".x-tool.x-tool-close";
    public static String submitOk = "OK";

    static public String forNommerr;

    @Step("ДОПРР. Заполнение таблицы.")
    public static void fillTable() {
        searchClass(openTable).doubleClick(); // открываем таблицу
        searchClassColl(calends).get(3).click(); // заполняем период дат
        searchXpathColl(dayOne).get(0).click();
        searchClassColl(calends).get(4).click();
        searchXpathColl(dayOne).last().click();
        searchClass(nextTable).doubleClick(); //открываем следующую таблицу
        searchText(autocount).isDisplayed();
        searchText(autocount).click();
        sleepTime(500);
        searchName(nameWork).setValue("АВТО наименование работ");
        searchName(okei).setValue("шт").click();
        searchTextColl(shtuki).last().isDisplayed();
        searchTextColl(shtuki).last().click();
        searchName(kolich).sendKeys("\b");
        searchName(kolich).setValue("142");
        searchName(priceNds).sendKeys("\b");
        searchName(priceNds).setValue("12560");
        searchTextColl(sohranitt).last().click();
        searchText(zakrTabl).click();
    }

    @Step("ДОПРР. Продавец. Создание документа.")
    public static String fillingDoprr(String adresat) {
        MainPage.creatDocAny(1); // создаём документ
        forNommerr = "АВТО MIN " + randomCount(); // генерим данные для номера
        searchName(docNumer).setValue(forNommerr);
        searchTextColl(zakazchik).get(0).click(); // выбираем адресата хаб не хаб
        searchText(adresat).isDisplayed();
        searchText(adresat).click();
        searchText(vibor).click();
        searchName(valuta).setValue("рос").click();
        searchTextColl(rosRub).get(0).isDisplayed();
        searchTextColl(rosRub).get(0).click();
        fillTable(); // заполняем таблицу
        searchClassColl(closeAndSave).last().click();  // закрываем документ и сохраняем
        $(byText("Сохранить изменения перед закрытием?")).isDisplayed();
        $(byText("Да")).click();
        $(byText("Документ успешно сохранен!")).isDisplayed();
        searchTextColl(submitOk).last().click();
        Date date = new Date();  // собираем полное наименование документа с учетом формата дат
        DateFormat dateF = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateF.format(date);
        String docNum = "Документ о передаче результатов работ (документ об оказании услуг) №" + forNommerr + " от " + dateStr;
        return docNum;
    }

}
