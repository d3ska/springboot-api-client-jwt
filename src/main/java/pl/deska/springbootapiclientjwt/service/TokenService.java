package pl.deska.springbootapiclientjwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;

@Service
public class TokenService {

    private PrivateKeyReader privateKeyReader;


    public TokenService() {
        this.privateKeyReader = new PrivateKeyReader();
    }

    public String generateJwt(boolean isAdmin) {
        try {
            PrivateKey privateKey = privateKeyReader.get("src/main/resources/key/privatekey");
            Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
            String jwtToken = JWT.create()
                    .withClaim("admin", isAdmin)
                    .withClaim("name", "Mateusz")
                    .sign(algorithm);
            return jwtToken;
        } catch (Exception e) {
            throw new AlgorithmMismatchException(e.getMessage());
        }
    }
}
