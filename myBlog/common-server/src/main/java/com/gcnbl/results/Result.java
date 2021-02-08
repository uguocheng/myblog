package com.gcnbl.results;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1963658737616885148L;
    private ResultFactory resultFactory;
    private T model;

    private static Result result = new Result();

    public Result() {
    }

    public Result(ResultFactory resultFactory, T model) {
        this.resultFactory = resultFactory;
        this.model = model;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ResultFactory getResultFactory() {
        return resultFactory;
    }

    public void setResultFactory(ResultFactory resultFactory) {
        this.resultFactory = resultFactory;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public static Result OK() {
        //Result result = new ResultSupport();
        result.setResultFactory(ResultFactory.ok);
        return result;
    }
    public static Result error() {
        //Result result = new ResultSupport();
        result.setResultFactory(ResultFactory.unknown_reason);
        return result;
    }

    public static Result setResult(ResultFactory resultFactory) {
        //Result result = new ResultSupport();
        result.setResultFactory(resultFactory);
        return result;
    }
}
