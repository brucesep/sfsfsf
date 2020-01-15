package task.steps;

import com.codeborne.selenide.Condition;

import static task.ElementsSearch.searchName;
import static task.ElementsSearch.searchTextColl;
import static task.HelpMeths.sleepTime;

/**
 * Created by alexeya on 10.07.2019.
 */
public class TitleCreate {

    public static String soderOper = "infoBuyer.infoHozLive4.infoCauseProduct.infoOper";
    public static String soderOperKschf = "infoBuyer.infoHozLive4.infoSoglasChange.infoOper";
    public static String otpravka = "Отправить";
    public static String obrabotka = "Обработка документов";
    public static String podpisOtpr = "Подписать и Отправить";
    public static String obrabotkaZaver = "обработка завершена";
    public static String zakritie = "Закрыть";

    public static void titulForSchf() {  // создание титула для СЧФ
        searchName(soderOper).setValue("АВТО Содержание операции");
        searchTextColl(otpravka).last().click();
        searchTextColl(obrabotka).last().waitUntil(Condition.appear, 10000);
        sleepTime(1000);
        searchTextColl(podpisOtpr).last().click();
        searchTextColl(obrabotkaZaver).last().waitUntil(Condition.appear, 7000);
        searchTextColl(zakritie).last().click();
    }

    public static void titulForKschf() { // создание титула для КСЧФ
        searchName(soderOperKschf).setValue("АВТО Содержание операции");
        searchTextColl(otpravka).last().click();
        searchTextColl(obrabotka).last().waitUntil(Condition.appear, 10000);
        sleepTime(1000);
        searchTextColl(podpisOtpr).last().click();
        searchTextColl(obrabotkaZaver).last().waitUntil(Condition.appear, 7000);
        searchTextColl(zakritie).last().click();
    }

    public static void titulTovarWork() { // создание титула для остального
        searchTextColl(otpravka).last().click();
        searchTextColl(obrabotka).last().waitUntil(Condition.appear, 10000);
        sleepTime(1000);
        searchTextColl(podpisOtpr).last().click();
        searchTextColl(obrabotkaZaver).last().waitUntil(Condition.appear, 7000);
        searchTextColl(zakritie).last().click();
    }
}
