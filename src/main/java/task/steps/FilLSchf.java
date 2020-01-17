package task.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import task.pages.MainPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static task.ElementsSearch.*;
import static task.HelpMeths.randomCount;
import static task.HelpMeths.sleepTime;

/**
 * Created by alexeya on 08.07.2019.
 */
public class FilLSchf extends MainPage {

    //СОЗДАЁМ СЧФ. С УКАЗАНИЕМ ФУНКЦИИ.

    static String closeAndSave = ".x-tool.x-tool-close";
    static String chooseFunction = ".x-form-trigger.x-form-arrow-trigger.arrow-n-combo-invalid";
    static String samaFuncia = ".x-combo-list-inner div";
    static String docNumber = "document.documentInfo.numberDoc";
    static String onzheGruzo = "checkedCargoSender";
    static String tablePoluch = "Получатели";
    static String submitOk = "ОК";
    static String submitOkKol = "OK";
    static String valuraRaz = "document.documentInfo.codeCurrency";
    static String rosRub = "643 Российский рубль";
    static String soderOper = "document.infoHozLive.infoHoz.infoOper";
    static String mainTable = ".x-grid3-cell-inner.x-grid3-col-nameProduct.x-unselectable";
    //static String mainTable = "20%";
    static String autoCount = "Автоподсчет суммы";
    static String naimTovara = "nameProduct";
    static String okei = "okei";
    static String stuka = "796 шт.";
    static String kolichestvo = "countProduct";
    static String stoimSNds = "priceWithNDS";
    static String sohranit = "Сохранить";

    static public String forNomer;

    @Step("СЧФ. Заполнение таблицы.")
    public static void fillTable() {
        searchClass(mainTable).doubleClick();
        //searchText(mainTable).doubleClick();
        sleepTime(500);
        searchText(autoCount).click();
        searchName(naimTovara).setValue("АВТО: наименование товара");
        searchName(okei).setValue("шт").click();
        searchTextColl(stuka).last().isDisplayed();
        searchTextColl(stuka).last().click();
        searchName(kolichestvo).sendKeys("\b");
        searchName(kolichestvo).setValue("126");
        sleepTime(500);
        searchName(stoimSNds).sendKeys("\b\b\b\b");
        searchName(stoimSNds).setValue("4568.65");
        searchName(kolichestvo).click();
        searchTextColl(sohranit).last().click();
    }

    @Step("СЧФ. Продавец. Создание документа.")
    public static String fillingSchf(int function, String adresat) {
        MainPage.creatDocAny(4); //создаём новый документ
        viborFunc(function);  // Выбираем функцию
        forNomer = "АВТО: MIN " + randomCount(); // генерим номер документа
        searchName(docNumber).setValue(forNomer); //заполняем номер документа
        searchName(onzheGruzo).click();
        if (function != 1) {
            searchName(soderOper).setValue("АВТО содержание операции.");
        }
        searchName(valuraRaz).setValue("рос").click(); // указываем валюту
        searchTextColl(rosRub).get(0).isDisplayed();
        searchTextColl(rosRub).get(0).click();
        $(By.id("panelRecipientColumn")).$(".x-action-col-icon.x-action-col-0.undefined").click(); // выбираем получателя
        searchText(tablePoluch).isDisplayed();
        searchText(adresat).isDisplayed(); //адресата передаем из вызывающего метода
        searchText(adresat).click(); //выбираем адресата хаб не хаб
        searchText(submitOk).click();
        fillTable();                           //заполняем таблицу - отдельный метод
        searchClassColl(closeAndSave).last().click(); // закрываем документ
        searchText("Сохранить изменения перед закрытием?").isDisplayed();
        searchText("Да").click();
        searchTextColl(submitOkKol).get(0).click();
        Date date = new Date();                    // в следующем блоке присваиваем переменной сгенерённое имя документа
        DateFormat dateF = new SimpleDateFormat("dd-MM-yyyy"); // конвертируем текущую дату в правильный формат
        String dateStr = dateF.format(date);
        String docNumber; // и добавляем дату к наименованию документа и его номеру
        if (function == 1) {
            docNumber = "Счет-фактура №" + forNomer + " от " + dateStr;
        } else {
            if (function == 2) {
                docNumber = "Счет-фактура и документ об отгрузке товаров (выполнении работ) №" + forNomer + " от " + dateStr;
            } else {
                docNumber = "Документ об отгрузке товаров (выполнении работ) №" + forNomer + " от " + dateStr;
            }
        }
        return docNumber; // возвращаем в метод полное имя документа с типом номером и датой
    }

    public static void viborFunc(Integer vibor) {  //выбираем функцию СЧФ
        searchClass(chooseFunction).click();
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
