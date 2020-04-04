package pl.lostworld.lostworldbackend.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.lostworld.lostworldbackend.authentication.JwtAuthenticationEntryPoint;
import pl.lostworld.lostworldbackend.authentication.JwtAuthenticationFilter;
import pl.lostworld.lostworldbackend.user.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //mod
//    @Autowired
//    private SpringDataUserDetailsService customUserDetailsService;
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler())
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .anyRequest()
                        .permitAll()
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

        http.addFilterBefore(customJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    //mod
    @Bean
    public JwtAuthenticationFilter customJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public JwtAuthenticationEntryPoint unauthorizedHandler() {
        return new JwtAuthenticationEntryPoint();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
