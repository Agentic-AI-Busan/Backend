package hyu.erica.capstone.config;

import hyu.erica.capstone.security.JwtAuthenticationFilter;
import hyu.erica.capstone.security.JwtTokenProvider;
import hyu.erica.capstone.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf( (csrf) -> csrf.disable());

        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(new AntPathRequestMatcher("/api/users/sign-up")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/users/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/users/check-email")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api-docs")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/**", "GET")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**", "GET")).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));
        return http.build();
    }
    private JwtAuthenticationFilter getJwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, authService);
    }
}