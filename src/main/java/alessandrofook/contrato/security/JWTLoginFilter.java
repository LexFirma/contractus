package alessandrofook.contrato.security;

import alessandrofook.contrato.model.autenticacao.Credencial;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  protected JWTLoginFilter(String url, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    Credencial credentials = new ObjectMapper()
        .readValue(request.getInputStream(), Credencial.class);

    System.out.println(credentials.getRole());

    return getAuthenticationManager().authenticate(
        new UsernamePasswordAuthenticationToken(
            credentials.getUsername(),
            credentials.getPassword()
        )
    );
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain,
      Authentication auth) throws IOException, ServletException {

    String username = ((User) auth.getPrincipal()).getUsername();

    TokenAuthenticationService.addAuthentication(response, username);
  }

}