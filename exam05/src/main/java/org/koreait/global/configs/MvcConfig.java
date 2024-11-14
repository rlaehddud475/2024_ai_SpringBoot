package org.koreait.global.configs;


import lombok.RequiredArgsConstructor;
import org.koreait.member.validators.JoinValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    private final JoinValidator joinValidator;

    @Override
    public Validator getValidator() {
        return joinValidator;
    }
}
