package spring.oauth.restapi.global.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Component
public class JwtUtils {
    /**
     * Using:
     * OAuthService; OAuthUserResponseDto
     * UserService; signup
     * User validation; JwtExpiredValidator
     */

    private static SecretKey JWT_SECRET_KEY;

    /* setSignKey() 에 Secret Key 를 직접 넣는 방법의 경우 deprecated 되어서 byte[] 형으로 받아야 한다 */
    public JwtUtils(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        JWT_SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    // 회원가입 시 Google, KaKao User data 를 decode 할 때 사용하는 메서드
    public static Map<String, Object> decodeJwt(String jwt) {
        String jwtSecretKey = "";

        if (getJwtSecretKey() == null) {
            Properties properties = new Properties();
            InputStream inputStream = JwtUtils.class.getClassLoader().getResourceAsStream("application-jwt.properties");

            try {
                if (inputStream != null) {
                    properties.load(inputStream);
                    jwtSecretKey = properties.getProperty("jwt.secret");
                    byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
                    JWT_SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
                }
            } catch (IOException e) {
                log.error("e = ", e);
            }
        }

        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(getJwtSecretKey())
                .build().parseClaimsJws(jwt);
        return jws.getBody();
    }

    public static SecretKey getJwtSecretKey() {
        return JWT_SECRET_KEY;
    }


}

