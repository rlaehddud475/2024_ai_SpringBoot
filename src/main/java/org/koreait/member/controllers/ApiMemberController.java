package org.koreait.member.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.member.constants.Authority;
import org.koreait.member.entities.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class ApiMemberController {
    @GetMapping("/test1")
    public Member test1() {
        Member member = new Member();
        member.setSeq(1L);
        member.setEmail("user01@test.org");
        member.setName("이이름");
        member.setPassword("12345678");
        member.setAuthority(Authority.USER);
        member.setRegDt(LocalDateTime.now());
        member.setModDt(LocalDateTime.now());

        return member;
    }

    @GetMapping("/test2")
    public List<Member> test2() {

        List<Member> members = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setSeq((long) i);
            member.setEmail("user" + i + "@test.org");
            member.setName("사용자" + i);
            member.setPassword("12345678");
            member.setAuthority(Authority.USER);
            member.setRegDt(LocalDateTime.now());
            member.setModDt(LocalDateTime.now());
            members.add(member);
        }
        return members;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/test3")
    public void test3(@RequestBody @Valid RequestLogin form, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().stream().flatMap(c -> Arrays.stream(c.getCodes())).collect(Collectors.joining(","));
            throw new BadRequestException(message);
        }

    }
/*@ExceptionHandler({Exception.class})
public String errorHandler(){return null;}
}*/
}