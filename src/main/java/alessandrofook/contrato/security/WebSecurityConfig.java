package alessandrofook.contrato.security;

import alessandrofook.contrato.service.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = CredencialService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CredencialService service;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeRequests()
        .antMatchers("/home").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .anyRequest().authenticated()
        .and()

        // filtra requisições de login
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)

        // filtra outras requisições para verificar a presença do JWT no header
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), service));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // cria uma conta default
//    auth.inMemoryAuthentication()
//        .withUser("admin")
//        .password("password")
//        .roles("ADMIN");
    auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
  }
}