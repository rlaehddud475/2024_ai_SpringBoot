package org.koreait.member.validators;

import org.koreait.member.controllers.RequestJoin;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class JoinValidator implements Validator {
//커멘드 객체 검증 한정(RequestJoin)커멘트 객체만을 검증
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestJoin form=(RequestJoin)target;
    }
}
