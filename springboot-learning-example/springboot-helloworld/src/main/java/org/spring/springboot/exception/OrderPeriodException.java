package org.spring.springboot.exception;

/**
 * @ClassName OrderPeriodException
 * @Description: TODO
 * @Author zhonglin
 * @Date 2019/8/14
 * @Version V1.0
 **/
public class OrderPeriodException extends RuntimeException {

    private static final long serialVersionUID = 6958499248468627021L;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误上下文
     */
    private String errorContext;


    public OrderPeriodException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public OrderPeriodException(OrderExceptionEnum orderExceptionEnum) {
        super(orderExceptionEnum.getErrorMsg());
        this.errorCode = orderExceptionEnum.getErrorCode();
    }

    public OrderPeriodException(String errorCode, String errorMsg, Throwable throwable) {
        super(errorMsg, throwable);
        this.errorCode = errorCode;
    }

    public OrderPeriodException(OrderExceptionEnum orderExceptionEnum, Throwable throwable) {
        super(orderExceptionEnum.getErrorMsg(), throwable);
        this.errorCode = orderExceptionEnum.getErrorCode();
    }

    /**
     * Getter method for property <tt>errorCode</tt>.
     *
     * @return property value of errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property <tt>errorCode</tt>.
     *
     * @param errorCode value to be assigned to property errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Getter method for property <tt>errorContext</tt>.
     *
     * @return property value of errorContext
     */
    public String getErrorContext() {
        return errorContext;
    }

    /**
     * Setter method for property <tt>errorContext</tt>.
     *
     * @param errorContext value to be assigned to property errorContext
     */
    public void setErrorContext(String errorContext) {
        this.errorContext = errorContext;
    }

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            //throw new AbcException("","CCCCCCCCCC");
            throw new OrderPeriodException(OrderExceptionEnum.SYSTEM_ERROR, e);
        }
    }


}
