package cn.ipaynow.security.demo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuzhihang
 * @date 2019-06-04 10:19
 */
@Controller
public class PageController {

    @GetMapping("/toAdmin")
    @ResponseBody
    public String toAdmin() {
        System.out.println("PageController.toAdmin|收到请求");
        return "收到 toAdmin 请求";
    }

    @GetMapping("/toEmployee")
    @ResponseBody
    public String toEmployee() {
        System.out.println("PageController.toEmployee|收到请求");
        return "收到 toEmployee 请求";
    }

    @GetMapping("/toUser")
    @ResponseBody
    public String toUser() {
        System.out.println("PageController.toUser|收到请求");

        return "toUser 请求成功";
    }
    @RequestMapping("/toAbout")
    @ResponseBody
    public String toAbout() {
        System.out.println("PageController.toAbout|收到请求");
        return "about 成功";
    }


    @RequestMapping("/toHome")
    @ResponseBody
    public String toHome() {
        System.out.println("PageController.toHome|收到请求");
        return "home 成功";
    }
}
