package ro.space.wserver.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserCredentialsConfig {

    // Define a UserDetailsService bean to manage user details
    @Bean
    public UserDetailsService userDetailsService() {
        // Create an in-memory user details manager
        // not the best option in case of a large number of users
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Define user1 with username "user1" and password "password1"
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("password1"))
                .roles("USER")
                .build();

        // Define user1 with username "user2" and password "password2"
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("password2"))
                .roles("USER")
                .build();

        // Add the defined users to the user details manager
        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    // Define a PasswordEncoder bean for password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
