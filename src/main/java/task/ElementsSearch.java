package task;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by alexeya on 09.07.2019.
 */
public class ElementsSearch {

    public static ElementsCollection searchXpathColl(String element){
        return $$(By.xpath("//*[@class='x-date-inner']//tbody//tr//td"));
    }

    public static SelenideElement searchId(String element) {
        return $(By.id(element));
    }

    public static SelenideElement searchText(String element) {
        return $(byText(element));
    }

    public static SelenideElement searchName(String element){
        return $(By.name(element));
    }

    public static SelenideElement searchClass(String element){
        return $(element);
    }

    public static ElementsCollection searchClassColl(String element) {
        return $$(element);
    }

    public static ElementsCollection searchTextColl(String element) {
        return $$(byText(element));
    }

}
