package task.steps;

import static task.steps.QvitkiWorkflow.*;

/**
 * Created by alexeya on 10.07.2019.
 */
public class QvitkiFastRun {

    //ПЕРВЫЙ ПАК КВИТАНЦИЙ
    public static void workWithQvitki(String docName, String urla, String loginName, String passWord){
        otpravkaQvitkaOtpr01(docName); // первая отправка квитанций Отправителем
        otpavkaQvitkovPoluch01(docName, urla, loginName, passWord); //отправка квитанций Получателем
        proverkaQvitkovPoluch01(docName); //проверка всех квитанций у Отправителя
    }

    //УТОЧНЕНИЯ С КВИТАНЦИЯМИ
    public static void utochnenia(String docName, String urla, String loginName, String passWord){
        ZaprosUtochnenia.zaprosUtochnenia(docName, urla, loginName, passWord); //отправка уточнения и квитанций с логином и разлогином
        proverkaQvitkovUtoch01(docName); // проверяем количество квитанций
        proverkaQvitkovOtprLast(docName, urla, loginName, passWord);
    }
}
