package com.javarush.entity;

import com.javarush.exception.ApplicationException;
import com.javarush.repository.ResultCode;

import java.io.InvalidClassException;

public class Result {

    private ResultCode resultCode;

    private ApplicationException applicationException;

    public Result(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Result(ResultCode resultCode, ApplicationException applicationException) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }
}
