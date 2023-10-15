package com.valeriygulin.config;

import com.valeriygulin.security.jwt.JwtConfigurer;
import com.valeriygulin.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                //AdminController
                .antMatchers(HttpMethod.POST, "/admin").permitAll()
                .antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
                //ApprenticeController
                .antMatchers(HttpMethod.POST, "/apprentice").permitAll()
                .antMatchers(HttpMethod.GET, "/apprentice", "/apprentice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/apprentice").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/apprentice/**").hasRole("ADMIN")
                //TrainerController
                .antMatchers(HttpMethod.POST, "/trainer").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/trainer","/trainer/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/trainer").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/trainer").hasRole("ADMIN")
                //TrainerScheduleController
                .antMatchers(HttpMethod.POST, "/trainer_schedule/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/trainer_schedule/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/trainer_schedule/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/trainer_schedule/**").hasRole("TRAINER")
                .antMatchers(HttpMethod.DELETE, "/trainer_schedule/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/trainer_schedule/**").hasRole("TRAINER")
                //TrainingController
                .antMatchers(HttpMethod.POST, "/training/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/training/**").hasRole("APPRENTICE")
                .antMatchers(HttpMethod.GET, "/training/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/training/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/training/**").hasRole("APPRENTICE")

                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
