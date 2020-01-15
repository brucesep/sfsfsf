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
 * Created by alexeya on 10.07.2019.
 */
public class FillKschf extends MainPage {

    public static String viborFunc = ".x-form-trigger.x-form-arrow-trigger.arrow-n-combo-invalid";
    public static String samaFuncia = ".x-combo-list-inner div";
    public static String nomerDoca = "document.documentInfo.numberDoc";
    public static String correctSf = ".x-grid3-cell-inner.x-grid3-col-numberSf.x-unselectable";
    public static String nomSf = "numberSf";
    public static String calends = ".x-form-trigger.triger-date-n-style";
    public static String calenDates = ".x-date-active.x-date-today.x-date-selected";
    public static String sohranit = "Сохранить";
    public static String pokupateli = "Выбрать..";
    public static String submitOk = "ОК";
    public static String valutaRaz = "document.documentInfo.dopInfoHozLive.nameCurrent";
    public static String rosRubls = "643 Российский рубль";
    public static String valutaDva = "document.documentInfo.codeCurrency";
    public static String rekviziti = "document.contentHozLive3.reqDocument";
    public static String soderOper = "document.contentHozLive3.operationInfo";
    public static String closeAndSave = ".x-tool.x-tool-close";
    public static String openTable = ".x-grid3-cell-inner.x-grid3-col-okeiBefore.x-unselectable";
    public static String autoCount = "Автоподсчет суммы";
    public static String naimTovar = "nameProduct";
    public static String okeiDo = "okeiBefore";
    public static String shtuki = "796 шт.";
    public static String okeiPosle = "okeiAfter";
    public static String kolvoDo = "countProductBefore";
    public static String kolvoPosle = "countProductAfter";
    public static String stoimDo = "summProductWithNDS.priceBeforeChange";
    public static String stoimPosle = "summProductWithNDS.priceAfterChange";
    public static String zakritOk = "OK";

    static public String forNomer;

    @Step("КСЧФ. Заполнение таблицы.")
    public static void fillTableKschf() {
        searchClass(openTable).doubleClick(); // открываем таблицу
        sleepTime(500);
        searchText(autoCount).click(); //заполянем поля таблицы
        searchName(naimTovar).click();
        searchName(naimTovar).setValue("АВТО наименование товара");
        searchName(okeiDo).setValue("шт").click();
        searchTextColl(shtuki).last().isDisplayed();
        searchTextColl(shtuki).last().click();
        searchName(okeiPosle).setValue("шт").click();
        searchTextColl(shtuki).last().isDisplayed();
        searchTextColl(shtuki).last().click();
        searchName(kolvoDo).sendKeys("\b");
        searchName(kolvoDo).setValue("15");
        searchName(kolvoPosle).sendKeys("\b");
        searchName(kolvoPosle).setValue("20");
        sleepTime(500);
        searchName(stoimDo).setValue("14500");
        searchName(stoimPosle).setValue("15800");
        searchTextColl(sohranit).last().click(); // закрываем таблицу
    }

    @Step("КСЧФ. Продавец. Создание документа.")
    public static String fillingKschf(int function, String adresat) {
        MainPage.creatDocAny(3);
        viborFuncii(function); // выбираем тип функции
        forNomer = "АВТО: MIN " + randomCount(); //генерим данные для номера документа
        searchName(nomerDoca).setValue(forNomer); // заполняем поля документа
        searchClass(correctSf).doubleClick(); // тут заполняем данные о СФ - корректировка
        searchName(nomSf).setValue("1");
        searchClassColl(calends).last().click();
        searchClassColl(calenDates).last().click();
        searchTextColl(sohranit).last().click(); //тут закончили с корректировкой
        searchTextColl(pokupateli).get(0).click(); // тут заполняем адресата хаб не хаб
        searchText(adresat).isDisplayed();
        searchText(adresat).click();
        searchText(submitOk).click(); // закончили с выбором адресата
        searchName(valutaRaz).setValue("рос").click(); // продолжаем заполнять поля документа
        searchTextColl(rosRubls).get(0).isDisplayed();
        searchTextColl(rosRubls).get(0).click();
        searchName(valutaDva).setValue("рос").click();
        searchTextColl(rosRubls).get(1).isDisplayed();
        searchTextColl(rosRubls).get(1).click();
        searchName(rekviziti).setValue("АВТО реквизиты передаточных документов");
        sleepTime(500);
        searchName(soderOper).setValue("АВТО содержание операции");
        fillTableKschf();   // заполняем таблицу
        searchClassColl(closeAndSave).last().click(); // закрываем и сохраняем документ
        $(byText("Сохранить изменения перед закрытием?")).isDisplayed();
        $(byText("Да")).click();
        $(byText("Документ успешно сохранен!")).isDisplayed();
        searchTextColl(zakritOk).last().click();
        Date date = new Date(); // приводим дату к нужному формату и генерим переменную с полным именем документа
        DateFormat dateF = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = dateF.format(date);
        String docNumer;
        if (function == 1) {
            docNumer = "Корректировочный счет-фактура №" + forNomer + " от " + dateStr;
        } else {
            if (function == 2) {
                docNumer = "Корректировочный счет-фактура и\\или документ об изменении стоимости отгруженных товаров (выполненных работ) №" + forNomer + " от " + dateStr;
            } else {
                docNumer = "Документ об изменении стоимости отгруженных товаров (выполненных работ) №" + forNomer + " от " + dateStr;
            }
        }
        return docNumer;
    }

    public static void viborFuncii(int vibor) {
        searchClassColl(viborFunc).get(0);
        switch (vibor) {
            case 1:
                searchClassColl(samaFuncia).get(0).click();
                break;
            case 2:
                searchClassColl(samaFuncia).get(1).click();
                break;
            case 3:
                searchClassColl(samaFuncia).get(2).click();
                break;
        }
    }

}
