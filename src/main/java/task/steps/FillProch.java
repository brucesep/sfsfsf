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

/**
 * Created by alexeya on 12.07.2019.
 */
public class FillProch extends MainPage {

    public static String docNumer = "NumberDoc";
    public static String viborr = "Выбрать..";
    public static String zakrit = "ОК";
    public static String theme = "SUBJECT";
    public static String soprtext = "ADDITIONALTEXT";
    public static String closeAndSave = ".x-tool.x-tool-close";
    public static String submitOk = "OK";
    public static String grupp = "Групповая рассылка:";
    public static String addAdr = ".x-action-col-icon.x-action-col-0.undefined";
    public static String checkAddr = ".x-grid3-col.x-grid3-cell.x-grid3-td-checker.x-grid3-cell-first";

    static public String forNnn;

    @Step("Прочий документ. Продавец. Создание документа. Рассылка {rass}.")
    public static String fillingProch(String rass, String adresat) {
        MainPage.creatDocAny(5); // создаём документ
        forNnn = "АВТО " + rass + " " + randomCount(); // генерим номер документа
        searchName(docNumer).setValue(forNnn);
        if (rass.equals("обычная")) {  //делаем разные прочие - Групповая или обычная рассылка
            searchTextColl(viborr).last().click();
            searchText(adresat).isDisplayed();
            searchText(adresat).click();
            searchText(zakrit).click();
        } else {
            searchText(grupp).click();
            searchClassColl(addAdr).last().click();
            searchText(adresat).isDisplayed();
            searchClassColl(checkAddr).last().click();
            searchText(zakrit).click();
        }
        searchName(theme).setValue("АВТО тема письма");
        searchName(soprtext).setValue("АВТО сопроводительный текст письма");
        searchClassColl(closeAndSave).last().click();  // закрываем документ
        $(byText("Сохранить изменения перед закрытием?")).isDisplayed();
        $(byText("Да")).click();
        $(byText("Документ успешно сохранен!")).isDisplayed();
        searchTextColl(submitOk).last().click();
        Date date = new Date();  // формируем полное наименование документа с учётом формата дат
        DateFormat dateF = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateF.format(date);
        String docNumber = "Прочий документ №" + forNnn + " от " + dateStr;
        return docNumber;
    }
}
