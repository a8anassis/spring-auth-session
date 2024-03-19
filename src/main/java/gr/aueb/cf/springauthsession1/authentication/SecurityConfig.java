package gr.aueb.cf.springauthsession1.authentication;

import gr.aueb.cf.springauthsession1.model.Role;
import gr.aueb.cf.springauthsession1.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                /**
                 * Enables Cross-Origin Resource Sharing (CORS) for all requests.
                 *
                 * @param cors a configurer for CORS
                 */
                .cors((cors) -> cors
                        .configurationSource(corsConfigurationSource())
                )
                /**
                 * Disables Cross-Site Request Forgery (CSRF) protection for all requests.
                 *
                 * @param http the object that allows to add filters to the security chain
                 * @return the object that allows to add filters to the security chain
                 */
                .csrf(AbstractHttpConfigurer::disable)    // when JWT is used
                /**
                 * Configures URL-based access control rules for the application.
                 *
                 * @param authorize the object that allows to add URL-based access control rules
                 */
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/teachers/register").permitAll()
                        .requestMatchers("/students/**").hasAnyAuthority(Role.STUDENT.name())
                        .requestMatchers("/teachers/**").hasAnyAuthority(Role.TEACHER.name())

                        // no need for logout.permitAll, logout filter
                        // is applied before all authentication filters
                        //.requestMatchers("/logout").permitAll()
                        .requestMatchers("/styles/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                                /**
                                 * Configures the form login functionality.
                                 *
                                 * @param formLogin a configurer for form login
                                 */
                                .loginPage("/login")                    // NOT default
                                .permitAll()
                                //.defaultSuccessUrl("/dashboard")        // NOT default

                        //.loginProcessingUrl("/login")         // default for post requests
                        //.usernameParameter("username")        // default
                        //.passwordParameter("password")        // default
                        //.failureUrl("/login?error")           // default
                )

                /**
                 * Enables HTTP Basic authentication. That is client is expected to send a
                 * 'username' and 'password' while the authentication scheme is based on
                 * session cookies.The basic authentication scheme is not considered secure
                 * and should only be used in situations where the communication between the
                 * client and server is secure (e.g. over HTTPS).
                 *
                 * @param http the object that allows to add filters to the security chain
                 * @return the object that allows to add filters to the security chain
                 */
                .httpBasic(withDefaults())


                /**
                 * By default, responds both to GET /logout and POST /logout
                 */
                .logout((logout) -> logout
                        //.logoutUrl("/logout")                         // default
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                /**
                 * Configures the remember me functionality.
                 *
                 * @param rememberMeConfigurer a configurer for remember me
                 * @return the object that allows to add filters to the security chain
                 */
                .rememberMe((rememberMeConfigurer) -> rememberMeConfigurer
                        //  .tokenRepository(persistentTokenRepository())
                        //.key("AppKey")
                        .alwaysRemember(true)
                        //.rememberMeParameter("rememberMe")
                        .rememberMeCookieName("remember-me")
                        .tokenValiditySeconds(24 * 60 * 60)
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://codingfactory.aueb.gr", "http://localhost:4200"));
        configuration.setAllowedMethods(List.of("*")); // List.of("GET","POST")
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}