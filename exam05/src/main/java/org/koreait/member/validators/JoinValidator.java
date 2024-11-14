package org.koreait.member.validators;

import org.koreait.global.validators.MobileValidator;
import org.koreait.member.controllers.RequestJoin;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Configuration
public class JoinValidator implements Validator, MobileValidator {
//커멘드 객체 검증 한정(RequestJoin)커멘트 객체만을 검증
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestJoin form=(RequestJoin)target;

        String email =form.getEmail();
        String password=form.getPassword();
        String confirmPassword= form.getConfirmPassword();;
        String name=form.getName();
        boolean agree=form.isAgree();
/*        if(email==null||email.isBlank()){
            errors.rejectValue("email", "Required","이메일을 입력하세요");
        }

        if(password==null||password.isBlank()){
            errors.rejectValue("password", "Required","비밀번호를 입력하세요");
        }
        if(confirmPassword==null||confirmPassword.isBlank()){
            errors.rejectValue("confirmPassword", "Required","비밀번호를 확인하세요");
        }
        if(name==null||name.isBlank()){
            errors.rejectValue("email", "Required","회원명을 입력하세요");
        }
        if (!agree){
            errors.rejectValue("agree", "Agree","회원가입 약관에 동의해 주세요");
        }*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agree","Agree");
        if(password != null && confirmPassword != null && !password.equals(confirmPassword)){
            errors.rejectValue("confirmPassword", "Mismatch");
        }
        String mobile=form.getMobile();
    if (mobile != null && !mobile.isBlank()&& !checkMobile(mobile)){
        errors.rejectValue("mobile","Mobile");
    }
    /*    boolean result=false;
        if(!result){
            errors.reject("Fail.join");
        }*/
    }

  
}
