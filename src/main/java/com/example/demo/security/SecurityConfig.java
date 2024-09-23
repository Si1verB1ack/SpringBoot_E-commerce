package com.example.demo.security;

import com.example.demo.services.admin.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1) // Admin configurations should be more specific
    public static class AdminSecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(registry -> registry
                            .requestMatchers("/admin/register", "/admin/login", "/admin/registration-process", "/admin/assets/**").permitAll()
//                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().permitAll()
                    )
                    .formLogin(form -> form
                            .loginPage("/admin/login")
                            .loginProcessingUrl("/admin/login")
                            .defaultSuccessUrl("/admin/dashboard?loginSuccess=true", true)
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/admin/logout")
                            .logoutSuccessUrl("/admin/login?logout")
                            .permitAll()
                    )
                    .csrf(AbstractHttpConfigurer::disable)
                    .build();
        }

        @Bean
        public UserDetailsService adminUserDetailsService() {
            return new AdminService();
        }

        @Bean
        public BCryptPasswordEncoder adminPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider adminAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(adminUserDetailsService());
            provider.setPasswordEncoder(adminPasswordEncoder());
            return provider;
        }
    }

//    @Configuration
//    @Order(2) // User configurations should be less specific
//    public static class UserSecurityConfig {
//        @Bean
//        public SecurityFilterChain securityFilterChainUser(HttpSecurity http) throws Exception {
//            return http
//                    .authorizeHttpRequests(registry -> registry
//                            .requestMatchers("/home", "/", "/user/**").permitAll()
//                            .requestMatchers("/user/account/**").hasRole("USER")
//                            .anyRequest().authenticated()
//                    )
//                    .formLogin(form -> form
//                            .loginPage("/user/login")
//                            .loginProcessingUrl("/user/login")
//                            .defaultSuccessUrl("/home?loginSuccess=true", true)
//                            .permitAll()
//                    )
//                    .logout(logout -> logout
//                            .logoutUrl("/user/logout")
//                            .logoutSuccessUrl("/user/login?logout")
//                            .permitAll()
//                    )
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .build();
//        }
//
//        @Bean
//        public UserDetailsService userDetailsService() {
//            return new UserService();
//        }
//
//        @Bean
//        public BCryptPasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//        @Bean
//        public AuthenticationProvider userAuthenticationProvider() {
//            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//            provider.setUserDetailsService(userDetailsService());
//            provider.setPasswordEncoder(passwordEncoder());
//            return provider;
//        }
//    }
}
