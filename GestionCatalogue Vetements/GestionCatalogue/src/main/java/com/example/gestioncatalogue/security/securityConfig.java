package com.example.gestioncatalogue.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class securityConfig {
    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;
  /*  @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            // Redirect to the home page after logout
            httpServletResponse.sendRedirect("/");
        };
    }*/


    // @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build()
        );
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.formLogin(form->form.permitAll());

        security.httpBasic(Customizer.withDefaults());

        security.authorizeHttpRequests(authorize->authorize.requestMatchers("/user/**").hasAuthority("USER"));
        security.authorizeHttpRequests(authorize->authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
        security.exceptionHandling(authorize->authorize.accessDeniedPage("/errorPage"));
        security.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());
        security.userDetailsService(userDetailsService);


        security.csrf(c-> c.disable());
        return security.build();
    }
}
