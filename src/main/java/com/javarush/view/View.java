package com.javarush.view;

import com.javarush.entity.Result;

public interface View {

    String[] getParamethers();

    void printResult(Result result);
}
