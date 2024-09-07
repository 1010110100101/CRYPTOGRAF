package com.javarush;


import com.javarush.app.Application;
import com.javarush.controller.MainController;
import com.javarush.entity.Result;
import com.javarush.repository.ResultCode;
import com.javarush.view.ConsoleView;
import com.javarush.view.View;

import static com.javarush.constants.ApplicationCompletionConstants.FINISH_TASK;

public class EntryPoint {
    public static void main(String[] args) {

        //збирання програми
        View view = new ConsoleView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);

        //запуск "двигуна"
        while(true) {
            Result result = application.run();

            //обробка результатів виконання програми
            application.printResult(result);

            if(result.getResultCode() == ResultCode.EXIT)
                break;
        }
    }
}