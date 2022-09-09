import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;

public class JWTVerify {
    private final String certsUrl;

    public JWTVerify(String certsUrl) {
        this.certsUrl = certsUrl;
    }

    void verify(String encryptedToken) throws MalformedURLException {
        DecodedJWT jwt = JWT.decode(encryptedToken);
        JwkProvider provider = new UrlJwkProvider(new URL(certsUrl));
        Jwk jwk;
        Algorithm algorithm;
        try {
            jwk = provider.get(jwt.getKeyId());
            algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        } catch (JwkException e) {
            throw new RuntimeException(e);
        }
        jwt.getClaims().forEach((key, value) -> System.out.println(key + " : " + value.asString()));

        algorithm.verify(jwt);
        System.out.println();
        System.out.println("Token is OK");
    }

}
