package id.co.mandiri.parkinglot.frontend.controller.config;

import id.co.mandiri.parkinglot.frontend.controller.config.handler.CustomSuccessAuthenticationHandler;
import id.co.mandiri.parkinglot.frontend.controller.config.handler.CustomSuccessLogutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    Environment environment;

    @Bean
    public CustomSuccessAuthenticationHandler successAuthenticationHandler() {
        return new CustomSuccessAuthenticationHandler();
    }

    @Bean
    public CustomSuccessLogutHandler successLogutHandler(){
        return  new CustomSuccessLogutHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "state/**", "js/**", "/validate", "/login", "css/**").permitAll();
        http.authorizeRequests(requests -> requests.antMatchers("/").permitAll().anyRequest().authenticated());
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/parking/list", true) //menggunakan url getMapping
                .failureUrl("/login?error=true")
                .permitAll();
        http.logout(logout -> logout
                .logoutUrl("logout")
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(successLogutHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );

        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(customDataSource());
        manager.setUsersByUsernameQuery("select username, password, enabled from app_user where username=?");
        manager.setAuthoritiesByUsernameQuery("select username ,role_name from user_role  where username = ?");
        manager.setRolePrefix("ROLE_");
        return manager;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder)throws Exception {

        builder.userDetailsService(userDetailsManager()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    DriverManagerDataSource customDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name")));
        driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
        driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return driverManagerDataSource;
    }

}
