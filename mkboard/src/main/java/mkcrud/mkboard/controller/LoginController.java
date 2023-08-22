package mkcrud.mkboard.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String initLogin(){
        return "loginform";
    }

    @GetMapping("/callback")
    public String loginCallbackGet(){
        log.info("네이버 로그인 콜백 진입(GET)");
        return "loginformcb";
    }
}
