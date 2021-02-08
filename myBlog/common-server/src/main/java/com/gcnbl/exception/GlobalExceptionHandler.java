package com.gcnbl.exception;

import com.gcnbl.results.Result;
import com.gcnbl.results.ResultFactory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.AccessException;

@ControllerAdvice
@Slf4j
@ResponseBody  //以json形式返回
public class GlobalExceptionHandler {

    /**
     * 全局异常处理  可以通用，因为如果知道具体异常的话，会走特定异常处理】
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        log.info("发生异常----------------------------");
        e.printStackTrace();//输出异常堆栈信息
        log.error(e.getMessage());
        return Result.error();
    }

    /**
     * 特定异常处理
     *
     * @param e
     * @return
     */
    //算数异常
    @ExceptionHandler(ArithmeticException.class)
    public Result error(ArithmeticException e) {
        log.info("算数异常----------------------------");
        log.error(e.getMessage());
        return Result.setResult(ResultFactory.my_error);
    }


    //sql语法异常
    /*@ExceptionHandler(BadSqlGrammarException.class)
    public Result error(BadSqlGrammarException e) {
        log.info("sql语法异常----------------------------");
        log.error(e.getMessage());
        return Result.setResult(ResultFactory.bad_sql_grammar);
    }*/

    //访问异常
    //@Order(-1)
    @ExceptionHandler(AccessException.class)
    public Result error(AccessException e) {
        log.info("访问异常----------------------------");
        log.error(e.getMessage());
        return Result.setResult(ResultFactory.forbidden);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result error(NullPointerException e) {
        log.info("空指针异常----------------------------");
        log.error(e.getMessage());
        return Result.setResult(ResultFactory.NullPointerException);
    }

    //MyException
    @ExceptionHandler(MyException.class)
    public Result error(MyException e) {
        log.info("自定义全局异常类----------------------------");
        log.error(e.getMessage());      //打印错误日志
        log.error(ExceptionUtil.getMessage(e));   //打印错误详细日志
        return Result.setResult(ResultFactory.NullPointerException);
    }

}