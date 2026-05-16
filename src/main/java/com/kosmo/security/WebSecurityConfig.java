package com.kosmo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:WHITELIST.properties")
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailService;  // CustomUserDetailsService 사용

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;  // CustomAuthenticationSuccessHandler 주입

    @Value("#{'${WHITELIST}'.split(',')}")
    private String[] whiteList;  // 쉼표로 구분된 값을 배열로 변환하여 저장

    @Bean
    public WebSecurityCustomizer configure() {
        return (web -> web.ignoring().requestMatchers("/static/**", "/images/**", "/courseimages/**", "/Instructorimages/**",
                "/userimages/**", "/css/**", "/js/**","/homeimages/**","/coursefiles/**", "/notice/**","/communityupload/**","/fragments/**"));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(whiteList).permitAll()  // 화이트리스트 URL은 인증 없이 접근 가능
                        .requestMatchers("/find-id").permitAll()  // 화이트리스트 URL은 인증 없이 접근 가능
                        .requestMatchers("/findpassword/**").permitAll()  // 화이트리스트 URL은 인증 없이 접근 가능
                        .requestMatchers("/check-username").permitAll()  // /checkusername 경로는 인증 없이 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // /admin/** 경로는 ADMIN 권한만 허용
                        .requestMatchers("/auth/**").hasAnyRole("STUDENT", "ADMIN")  // /auth/** 경로는 STUDENT 또는 ADMIN 권한 허용
                        .requestMatchers("/instructor/**").hasAnyRole("INSTRUCTOR", "ADMIN")  // /instructor/** 경로는 INSTRUCTOR 또는 ADMIN 권한 허용
                        .requestMatchers("/cart/**").hasAnyRole("STUDENT", "INSTRUCTOR", "ADMIN")  // /cart/** 경로는 STUDENT, INSTRUCTOR, ADMIN 권한 허용
                        .requestMatchers("/community/**").hasAnyRole("STUDENT", "INSTRUCTOR", "ADMIN")  // /community/** 경로는 STUDENT, INSTRUCTOR, ADMIN 권한 허용
                        .requestMatchers("/error").permitAll()  // 에러 페이지에 대한 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("STUDENT", "ADMIN")  // /my/** 경로는 STUDENT 또는 ADMIN 권한 허용
                        .anyRequest().authenticated()  // 그 외 모든 경로는 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login")  // 로그인 페이지 설정
                        .successHandler(successHandler)  // 로그인 성공 후 처리할 핸들러 설정
                        .permitAll()  // 로그인 페이지는 모두 접근 가능
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/").invalidateHttpSession(true))  // 로그아웃 후 홈으로 리디렉션
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/accessDenied") // 권한이 없을 경우 처리
                        .authenticationEntryPoint((request, response, authException) -> {
                            // 커스텀 에러 페이지로 리디렉션 로그인 안한사람이 들어올 경우
                            response.sendRedirect("/custom-error");
                        })
                )
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .headers(headers -> headers.frameOptions().sameOrigin());  // 동일 도메인에서만 iframe 허용

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 관리자 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);  // CustomUserDetailsService 사용
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }
}
