package pl.lostworld.lostworldbackend.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.lostworld.lostworldbackend.user.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .cors()
                    .and()
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/users/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler((req,res,auth)->{
                        for (GrantedAuthority authority : auth.getAuthorities()) {
                            log.info(authority.getAuthority());
                        }
                        log.info("Logged in: " + auth.getName());
                        res.setStatus(200);
                    })
                    .failureHandler((req,res,exp)->{
                        log.info("Error during logginng in");
                        String errMsg="";
                        if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
                            errMsg="Invalid username or password.";
                        }else{
                            errMsg="Unknown error - " + exp.getMessage();
                        }
                        res.sendError(403, errMsg);
                    })
                .and()
                .logout()
                    .logoutUrl("/users/logout")
                    .logoutSuccessHandler((req,res,auth)->{
                        if (auth != null) {
                            log.info("Logout: " + auth.getName());
                        } else {
                            log.info("Someone tried to logout");
                        }
                        res.setStatus(200);
                    });
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
}
