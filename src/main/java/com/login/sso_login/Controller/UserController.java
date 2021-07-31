package com.login.sso_login.Controller;

import com.login.sso_login.constant.ResponseContants;
import com.login.sso_login.model.User;
import com.login.sso_login.service.UserService;
import com.login.sso_login.utils.JackSonUtils;
import com.login.sso_login.utils.SecretUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liurong
 * @date 2020/10/14 21:32
 */
@RestController
@RequestMapping(value = "/rest/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String LONGIN_COOKIE_KEY = "login_uid";

    private static final String LONGIN_TOKEN_KEY = "Token";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        try {
            String userName = user.getUserName();
            String passWord = user.getPassWord();
            passWord = SecretUtils.AesEncrypt(passWord);
            user = userService.getUser(userName,passWord);
            if (user != null) {
                String encryptUserName = SecretUtils.AesEncrypt(userName);
                String token = userService.getLoginTokenByUser(user);
                Map<String, Object> map = new HashMap<>();
                Cookie cookie = new Cookie(LONGIN_COOKIE_KEY, encryptUserName);
                cookie.setPath("/");
                response.addCookie(cookie);
                map.put(LONGIN_COOKIE_KEY, token);
                map.put(LONGIN_TOKEN_KEY, token);
                map.put("user",user);
                result.put(ResponseContants.DATA, map);
                result.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
                response.addHeader(LONGIN_TOKEN_KEY, token);
            } else {
                result.put(ResponseContants.STATUS, ResponseContants.FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseContants.STATUS, ResponseContants.FAIL);
            result.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return result;
    }


    @PostMapping("/logout")
    public Object logout(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.logout(user);
            result.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseContants.STATUS, ResponseContants.FAIL);
            result.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return result;
    }


    @PostMapping("/registry")
    public Object registry(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        try {
            userService.registry(user);
            result.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseContants.STATUS, ResponseContants.FAIL);
            result.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return result;
    }


    @GetMapping("/test/{id}")
    public Object login(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        try {
            if (id == 2) {
                HttpSession session = request.getSession();
                if (session.isNew()) {
                    session.setAttribute("name", "lirong23");
                    Cookie cookie = new Cookie("ronge_cookie_name12", "ronge_cookie_value12");
                    cookie.setMaxAge(5 * 60);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                } else {
                    logger.info("session.id:{} , session.name:{}", session.getId(), session.getAttribute("name"));
                }
                System.out.println("ronge23......");
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        logger.info("21...cookie.name:{},cookie.value:{}", cookie.getName(), cookie.getValue());
                    }
                }
            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        logger.info("31...cookie.name:{},cookie.value:{}", cookie.getName(), cookie.getValue());
                    }
                }
            }

            result.put("ronge", "ronge" + id);
            System.out.println("hello hot update");
            result.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result.put(ResponseContants.STATUS, ResponseContants.FAIL);
            result.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return result;
    }
}
