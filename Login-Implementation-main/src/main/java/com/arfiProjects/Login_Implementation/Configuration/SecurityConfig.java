package com.arfiProjects.Login_Implementation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests(auth-> auth
                .requestMatchers("/", "/register", "/do-register", "/login").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                .anyRequest().authenticated()
            )            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )            .httpBasic(Customizer.withDefaults())
            .csrf(Customizer.withDefaults())
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll());

        return httpSecurity.build();
    }
}
