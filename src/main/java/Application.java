import java.net.MalformedURLException;

public class Application {
    public static void main(String[] args) throws MalformedURLException {
        String encryptedToken = "token";
        JWTVerify jwtVerify = new JWTVerify("url");
        jwtVerify.verify(encryptedToken);
    }
}
