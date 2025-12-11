package org.deu.miu.cs545de.websecurityproject1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // In-memory users: Bob (sales) and Mary (finance)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // Bob = employee in sales department
        UserDetails bob = User.withUsername("bob")
                .password(passwordEncoder.encode("bob123"))
                .roles("EMPLOYEE", "SALES")      // -> ROLE_EMPLOYEE, ROLE_SALES
                .build();

        // Mary = employee in finance department
        UserDetails mary = User.withUsername("mary")
                .password(passwordEncoder.encode("mary123"))
                .roles("EMPLOYEE", "FINANCE")    // -> ROLE_EMPLOYEE, ROLE_FINANCE
                .build();

        return new InMemoryUserDetailsManager(bob, mary);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // HTTP security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())   // easier for Postman testing
                .authorizeHttpRequests(auth -> auth
                        // /shop is accessible by everyone
                        .requestMatchers("/shop").permitAll()

                        // /orders is accessible by all employees
                        .requestMatchers("/orders").hasRole("EMPLOYEE")

                        // /payments is accessible only by finance employees
                        .requestMatchers("/payments").hasRole("FINANCE")

                        // any other request must be authenticated
                        .anyRequest().authenticated()
                )
                // Use HTTP Basic auth (good for Postman)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
