package cn.ipaynow.security.demo.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhihang
 * @date 2019-06-03 16:27
 */
@Data
public class RespBean implements Serializable {


    private String code;
    private String msg;
    private Object result;
    private String jwtToken;

    public RespBean(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

}
