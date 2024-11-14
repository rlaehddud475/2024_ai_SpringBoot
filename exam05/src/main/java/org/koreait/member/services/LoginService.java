package org.koreait.member.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.koreait.member.controllers.RequestLogin;
import org.koreait.member.entties.Member;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final HttpSession session;
    private final HttpServletResponse response;
    public void process(RequestLogin login){
        Member member = new Member();
        member.setSeq(1l);
        member.setEmail(login.getEmail());
        member.setName("이이름");
        member.setRegDt(LocalDateTime.now());

        session.setAttribute("loggedMember",member);
        Cookie cookie = new Cookie("savedEmail",login.getEmail());
        if (login.isSaveEmail()){
            cookie.setMaxAge(60*60*24*30);

        }else {
            cookie.setMaxAge(0);

        }
        response.addCookie(cookie);
    }
}
