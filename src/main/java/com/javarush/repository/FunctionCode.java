package com.javarush.repository;

import com.javarush.services.*;

public enum FunctionCode {
    ENCODE (new Encode()),
    DECODE (new Decode()),
    BROOT_FORCE_A (new BrootForceH()),
    BROOT_FORCE_H (new BrootForceA()),
    STATISTICAL (new StatisticalDecode()),
    HANDY (new Handy()),
    UNSUPPORTED_FUNCTION(new UnsupportedFunction());

    private Function function;
    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
