package alessandrofook.contrato.service;

import alessandrofook.contrato.exception.FailedAuthenticationException;
import alessandrofook.contrato.model.pessoa.Pessoa;
import alessandrofook.contrato.security.model.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class AuthenticationService {

    private final String ISSUER = "random";
    private final String secretKey = "random";

    @Autowired
    private PessoaService service;

    public Pessoa authenticate(UserCredentials credentials) {
        Pessoa user = service.getByRegistration(credentials.getRegistration());

        if (BCrypt.checkpw(credentials.getPassword(), user.getSenha()))
            return user;

        throw new FailedAuthenticationException();
    }

    public String tokenFor(Pessoa user) {
        Date expiration = Date.from(LocalDateTime.now().plusHours(24 * 7).toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .setSubject(user.getNome())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Pessoa getPessoaFromToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return  service.getByRegistration(claims.getBody().getSubject().toString());
    }
}
