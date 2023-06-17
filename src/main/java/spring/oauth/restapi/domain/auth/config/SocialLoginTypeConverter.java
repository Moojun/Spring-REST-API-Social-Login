package spring.oauth.restapi.domain.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import spring.oauth.restapi.domain.auth.domain.SocialLoginType;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String str) {
        return SocialLoginType.valueOf(str.toUpperCase());
    }
}

