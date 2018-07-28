package alessandrofook.contrato.security;

import alessandrofook.contrato.service.CredencialService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final CredencialService service;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CredencialService service) {
        super(authenticationManager);
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(TokenAuthenticationService.HEADER_STRING);

        if(header == null || !header.startsWith(TokenAuthenticationService.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken userToken = getAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(userToken);
        chain. doFilter(request, response);

    }


    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(TokenAuthenticationService.HEADER_STRING);

        if(token == null) return null;

        String username = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET)
                .parseClaimsJws(token.replace(TokenAuthenticationService.TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();


        UserDetails userDetails = service.loadUserByUsername(username);

        System.out.println(userDetails.getAuthorities());

        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()) : null;

    }
}
