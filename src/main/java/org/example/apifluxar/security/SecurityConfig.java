package org.example.apifluxar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    final CustomAccessDeniedHandler customAcessDeniedHandler;
    final JwtAuthenticationFilter  jwtAuthenticationFilter;

    public SecurityConfig(CustomAccessDeniedHandler customAcessDeniedHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customAcessDeniedHandler = customAcessDeniedHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-resources",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers("api/employee/login","api/employee/update/password","api/email/send").permitAll()
                        .requestMatchers("api/**").hasRole("G")
                        .requestMatchers("api/employee/profile/","api/employee/update/photo/site","api/employee/").hasRole("A")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(excepition -> excepition
                        .accessDeniedHandler(customAcessDeniedHandler));
        return http.build();
    }

    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 1 << 12, 3);
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
//    }


}
