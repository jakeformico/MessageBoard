package group.project.messageboard.security;

import group.project.messageboard.models.Person;
import group.project.messageboard.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PersonRepository personRepo) {
        return email -> {
            Person person = personRepo.findByEmail(email);
            if (person != null) {
                return person;
            }
            throw new UsernameNotFoundException(
                    "User with email  '" + email + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // Todo: Try to see if this works for testing
                .authorizeHttpRequests()
                .requestMatchers( "/api/post", "/api/person").hasRole("PERSON")
                .requestMatchers("/", "/login", "/register", "/inputPage").permitAll()

                .and()
                .formLogin()
                .loginPage("/login")

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/api/post")

                .and()
                .logout()
                .logoutSuccessUrl("/")

                // Non-secured h2-console
                .and()
                .csrf()
                .ignoringRequestMatchers("/h2-console/**")

                // Allow pages to be loaded in frames from the same origin; needed for
                // H2-Console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .build();
    }

}