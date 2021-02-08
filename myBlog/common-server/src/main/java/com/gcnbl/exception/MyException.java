package com.gcnbl.exception;

import lombok.Data;

@Data
//@ApiModel(value = "自定义全局异常类")
public class MyException extends RuntimeException {
    public MyException() {
        System.out.println("自定义全局异常类");
    }
}