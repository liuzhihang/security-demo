package cn.ipaynow.security.demo.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhihang
 * @date 2019-06-04 10:04
 */
@Data
public class DbUser implements Serializable {

    private String userName;

    private String password;

    private String role;

}
