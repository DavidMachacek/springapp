package com.david.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.david.demo.user.RepositoryUserDetailsService;
import com.david.demo.user.SimpleSocialUserDetailsService;
import com.david.demo.user.UserRepository;

@EnableResourceServer
@Configuration
@Import(PasswordEncoderConfiguration.class)
@Order(1) //TODO add to override default OAuth2 intercepter, but then you are refused to login to any page!
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Configures form login
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?error=bad_credentials")
                //Configures the logout function
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                //Configures url based authorization
                .and()
                .authorizeRequests()
                //Anyone can access the urls
                .antMatchers(
                        "/**",
                        "/auth/**",
                        "/login",
                        "/signup/**",
                        "/facebook",
                        "/fb",
                        "/user/register/**",
                        "/api/**", "/users", "/customers", "/api/customers", "/user/registration"
                ).permitAll()
                //The rest of the our application is protected.
                //.antMatchers("/**").hasRole("USER")
                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                .and()
                .apply(new SpringSocialConfigurer())
                .and().csrf().disable();
        /*
                .authorizeRequests()
                .antMatchers("/", "/customers", "/api/customers", "/v2/**").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
                */
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new CustomAuthenticationManager(authenticationProvider);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new DBAuthenticationProvider(userRepository, passwordEncoder);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new RepositoryUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
