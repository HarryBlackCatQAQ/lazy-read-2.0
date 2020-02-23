package com.hhr.lazyread.model;

/**
 * @Author: Harry
 * @Date: 2019-09-20 15:24
 * @Version 1.0
 */
public interface ResultStatusCode {
    /**
     * 成功
     */
    public static final int OK = 20000;
    public static final String OK_MES = "成功";

    /**
     * 失败
     */
    public static final int ERROR = 40001;
    public static final String ERROR_MES = "失败";
    public static final String ERROR_MES_SYS = "系统繁忙请稍后再试";


    /**
     * 用户名或密码错误
     */
    public static final int LOGINERROR = 40002;
    public static final String LOGINERROR_MES = "用户名或密码错误";

    /**
     * 权限不足
     */
    public static final int ACCESSERROR = 40003;
    public static final String ACCESSERROR_MES = "权限不足";

    /**
     * 远程调用失败
     */
    public static final int REMOTEERROR = 40004;
    public static final String REMOTEERROR_MES = "远程调用失败";

    /**
     * 重复操作
     */
    public static final int REPERROR = 40005;
    public static final String REPERROR_MES = "重复操作";

    /**
     * token错误或者失效
     */
    public static final int TOEKNERROR = 40006;
    public static final String TOEKNERROR_MES = "token错误或者失效";

    /**
     * 验证码错误
     */
    public static final int VALIDATE_CODE_ERROR = 40007;
    public static final String VALIDATE_CODE_ERROR_MES = "验证码错误";
}
