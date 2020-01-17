package task.test;

import org.testng.annotations.DataProvider;

/**
 * Created by alexeya on 09.07.2019.
 */
public class DataProvidersAny {

    @DataProvider(name = "loginVars")
    public static Object[][] loginVars(){
        return new Object[][]{
                {"311156349", "http://postgresf.comita.lan:8080/ccwe/", "megatester02", "gfhjkmnhb"},
                {"338840248", "http://postgresf9.comita.lan:8080/ccwe/", "megatester_hub", "gfhjkmnhb"}
        };
    }
}
