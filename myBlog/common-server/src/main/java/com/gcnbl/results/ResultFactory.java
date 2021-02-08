package com.gcnbl.results;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class ResultFactory implements Serializable {
    private static final long serialVersionUID = 3470274347463827641L;
    private Integer code;//返回码
    private String message;//返回消息

    public ResultFactory() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ResultFactory(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public final static ResultFactory ok = new ResultFactory(200, "请求成功");
    public final static ResultFactory created = new ResultFactory(201, "新建/修改成功");
    public final static ResultFactory accepted = new ResultFactory(202, "排队中");
    public final static ResultFactory invalid_request = new ResultFactory(400, "请求错误/无效请求");
    public final static ResultFactory unauthorized = new ResultFactory(401, "没权限");
    public final static ResultFactory forbidden = new ResultFactory(403, "禁止访问");
    public final static ResultFactory not_found = new ResultFactory(404, "不存在对应记录");
    public final static ResultFactory not_acceptable = new ResultFactory(406, "请求格式错误");
    public final static ResultFactory unknown_reason = new ResultFactory(20001, "未知错误");
    public final static ResultFactory bad_sql_grammar = new ResultFactory(21001, "sql语法错误");
    public final static ResultFactory NullPointerException = new ResultFactory(2100, "空指针异常");
    public final static ResultFactory my_error = new ResultFactory(21006, "算术异常错误");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultFactory that = (ResultFactory) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
