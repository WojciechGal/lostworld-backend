package pl.lostworld.lostworldbackend.config;

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
                    .loginPage("users/login")
                    .loginProcessingUrl("/users/loginprocess")
                    .defaultSuccessUrl("/users/sec")
                    .failureUrl("users/login?error")
                .and()
                .logout()
                    .logoutUrl("users/logout")
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
