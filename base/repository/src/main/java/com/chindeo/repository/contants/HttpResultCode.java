package com.chindeo.repository.contants;

/**
 * 全局接口返回code
 */
public interface HttpResultCode {

    int SUCCESS = 200;// 正常

    int TOKEN_INVALID_301 = 301;// token失效
    int TOKEN_INVALID_401 = 401;// token失效

    static boolean isTokenInvalid(int code){
        return code == TOKEN_INVALID_301 || code == TOKEN_INVALID_401;
    }
}
