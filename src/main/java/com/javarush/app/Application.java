package com.javarush.app;

import com.javarush.controller.MainController;
import com.javarush.entity.Result;
import com.javarush.repository.FunctionCode;
import com.javarush.services.Function;

import static com.javarush.constants.FunctionCodeConstants.*;
import static com.javarush.repository.ResultCode.EXIT;

public class Application {

    private final MainController mainController;
    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run() {

        String[] paramethers = mainController.getView().getParamethers();

        if(ifExit(paramethers))
            return new Result(EXIT);

        String targetFunc = paramethers[1];
        Function function = getFunction(targetFunc);

        return function.execute(paramethers);
    }

    private boolean ifExit(String[] paramethers) {
        if(paramethers == null || paramethers.length == 0)
            return false;

        for(int i = 0; i < paramethers.length; i++)
        {
            if(paramethers[i] == null)
                continue;

            if(paramethers[i].equalsIgnoreCase("q"))
                return  true;
        }
        return  false;
    }

    private Function getFunction(String mode) {
        return switch (mode) {
            case "1" -> FunctionCode.valueOf(ENCODE).getFunction();
            case "2" -> FunctionCode.valueOf(DECODE).getFunction();
            case "3a" -> FunctionCode.valueOf(BROOT_FORCE_A).getFunction();
            case "3h" -> FunctionCode.valueOf(BROOT_FORCE_H).getFunction();
            case "4" -> FunctionCode.valueOf(STATISTICAL).getFunction();
            case "5" -> FunctionCode.valueOf(HANDY).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }

    public void printResult(Result result) {
        mainController.getView().printResult(result);
    }
}
