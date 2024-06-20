package com.example.demo.security;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final Environment env;
    private final UserRepo userRepo;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/actuator","/actuator/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->form
                        .loginPage("/login")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        .frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin().disable())
                );

        return http.build();
    }
    @Bean
    public UserDetailsService localUserDetailsService() {
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepo.findByName(username).orElseThrow(() -> {
                    log.info("User not found with username: {}", username);
                    return new UsernameNotFoundException("유저를 찾을 수 없습니다.");
                });

                //임시
                String[] auths;
                if(username.equals("admin")){
                    auths = new String[]{"ADMIN","USER"};
                }else{
                    auths = new String[]{"USER"};
                }

                return org.springframework.security.core.userdetails.User.builder()
                        .username(username)
                        .passwordEncoder(passwordEncoder()::encode)
                        .password(user.getPassword())
                        .authorities(auths)
                        .build();
            }
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
