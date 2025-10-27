package org.example.apifluxar.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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
@SecurityScheme(name = SecurityConfig.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT",scheme = "bearer")
public class SecurityConfig {

    public static final String SECURITY = "BearerAuth";
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
                            "/webjars/**",
                            "/api/employee/login",
                            "/api/employee/update/password",
                            "/api/email/send"
                    ).permitAll()
                    .requestMatchers("/api/employee/**").hasAnyRole("A", "G")
                    .requestMatchers("/api/**").hasRole("G")
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



}
