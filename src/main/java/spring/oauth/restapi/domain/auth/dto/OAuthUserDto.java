package spring.oauth.restapi.domain.auth.dto;

public interface OAuthUserDto {

    String getId();

    String getEmail();

    String getName();

    String getProvider();   // google, kakao, ...

}