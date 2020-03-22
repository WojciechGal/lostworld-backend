package pl.lostworld.lostworldbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.lostworld.lostworldbackend.user.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginProcessingUrl("/users/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/users/sec")
                    .failureHandler((req,res,exp)->{
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
                    .logoutSuccessUrl("/users/sec");
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
