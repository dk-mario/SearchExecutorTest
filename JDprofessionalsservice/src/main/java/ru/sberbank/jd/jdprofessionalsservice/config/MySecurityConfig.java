package ru.sberbank.jd.jdprofessionalsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // корень - без авторизации, приветсвенная страница
        // далее - авторизация
        http/*.csrf().ignoringAntMatchers("/admin/update") // для POST/GET запросов из формочек!
                .and()*/
                .logout()
                .logoutSuccessUrl("/") // при выходе редиректим на главную страницу
                .and()
                .authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/images/*").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/about").permitAll()
                .antMatchers("/user/create").permitAll()
                //.antMatchers("/api/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin();
/*
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied.jsp");
*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
