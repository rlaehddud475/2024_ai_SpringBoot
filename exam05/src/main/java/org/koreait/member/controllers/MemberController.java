package org.koreait.member.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.koreait.member.services.LoginService;
import org.koreait.member.validators.JoinValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final JoinValidator joinValidator;
    private final LoginService loginService;
  /*  @GetMapping("/join")
    public String join(@ModelAttribute("requestJoin") RequestJoin form, Model model){

        return "member/joinForm";
    }*/
    @ModelAttribute("apples")
    public List<String> apples(){
        return List.of("사과1","사과2","사과3");
    }
    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model){
        return "member/joinForm";
    }
    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors) {

    /*    joinValidator.validate(form, errors);*/ // 커맨드 객체 검증

        if (errors.hasErrors()) { // 검증 실패! - reject, rejectValue가 한번이라도 호출 되었다!
            return "member/joinForm"; // 검증 실패하면 사용자에게 양식을 다시 보여주고, 검증 실패 정보를 제공!
        }

        // 검증 성공시 - 가입 처리 서비스 호출

        return "redirect:/member/login"; // 가입 성공시 로그인 페이지로 이동
    }
    @GetMapping("/login")
    public String login(@ModelAttribute RequestLogin form, @CookieValue(name = "savedEmail",required = false) String savedEmail){
        if (savedEmail !=null){
            form.setEmail(savedEmail);
            form.setSaveEmail(true);
        }
        return "member/login";
    }
    @PostMapping("/login")
    public String loginPx(@Valid RequestLogin form, Errors errors,  HttpSession session){
        if(errors.hasErrors()){
            return "member/login";
        }
        loginService.process(form);
        ;
        return "redirect:/";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/member/login";
    }
    public void initBinder(WebDataBinder binder){
        binder.setValidator(joinValidator);
    }
}
