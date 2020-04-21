package org.spring.springboot.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName ResponseResult
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/9/9
 * @Version V1.0
 **/
@Getter
@Setter
public class ResponseResult<T> {

    //请求成功返回码为：0000
    private static final String successCode = "0000";
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回描述
    private String msg;

    public ResponseResult() {
        this.code = successCode;
        this.msg = "请求成功";
    }

    public ResponseResult(String code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, T data) {
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(T data) {
        this();
        this.data = data;
    }


}
