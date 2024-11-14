package org.koreait.global.validators;

public interface MobileValidator {
    default boolean checkMobile(String num ){
        num=num.replaceAll("\\D","");
        String pattern="^[016]\\d{3,4}\\d{4}$";
        return num.matches(pattern);
    }

}
