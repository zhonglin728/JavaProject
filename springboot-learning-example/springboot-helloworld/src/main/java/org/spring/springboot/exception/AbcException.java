package org.spring.springboot.exception;

/**
 * @ClassName AbcException
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/14
 * @Version V1.0
 **/
public class AbcException extends OrderPeriodException {

    public AbcException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
