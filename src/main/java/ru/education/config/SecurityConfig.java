package ru.education.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.education.jwt.JWTLoginFilter;
import ru.education.jwt.JwtAuthenticationFilter;
import ru.education.security.SecurityUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(SecurityBeansConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityUserDetailsManager userDetailsManager;

    @Autowired
    public SecurityConfig(SecurityUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll() //началльная страница доступ для всех
                .antMatchers(HttpMethod.POST, "/login").permitAll() //адрес для авторизации доступ для всех
                .anyRequest().authenticated() //остальные запросы только для авторизованных
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), //добавить обработчик к запросу на /login
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); //обработчик всех остальных запросов
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsManager);
    }
}
