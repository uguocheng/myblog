package com.gcnbl.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
//@ApiModel(value = "自定义全局异常类")
public class MyException extends RuntimeException {
    private static final long serialVersionUID = -5381209361866573047L;

    public MyException() {
        System.out.println("自定义全局异常类");
    }
}