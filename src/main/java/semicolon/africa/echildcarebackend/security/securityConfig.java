package semicolon.africa.echildcarebackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import semicolon.africa.echildcarebackend.data.models.enumClasses.Roles;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class securityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(AbstractHttpConfigurer::disable)
//            .headers(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:3000")))
                .authorizeHttpRequests((auth -> {
                    auth.requestMatchers("/api/v1/auth/register", "/api/v1/auth/login",
                            "/api/v1/verifyEmail", "/api/v1/test", "/api/v1/verifyEmail/sendOtp", "/api/v1/verifyEmail/sendOtp/confirmOtp",
                            "/api/v1/parent/addChild",   "/api/v1/parentBookedSessions",  "/api/v1/careTaker/clockIn",
                            "/api/v1/careTaker/clockOut", "/api/v1/parent/updateProfile",
                            "/api/v1/careTakerBookedSessions", "/api/v1/careTaker/bookPreferredCareTaker", "/api/v1/careTakerWorkHistory",
                            "/api/v1/parent/addChild", "/api/v1/bookCareTaker", "/api/v1/parent/updateProfile", "/api/v1/userProfileData"
                    ).permitAll();

                }))
//                .authorizeHttpRequests((auth -> {
//                    auth.requestMatchers("/api/v1/parentBookedSessions", "/api/v1/parent/addChild", "/api/v1/careTaker/clockIn",
//                            "/api/v1/careTaker/clockOut", "/api/v1/parent/updateProfile", "/api/v1/bookCareTaker",
//                            "/api/v1/careTakerBookedSessions", "/api/v1/careTaker/bookPreferredCareTaker").authenticated();
//                }))
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers()
//                .addHeaderWriter(
//                        new StaticHeadersWriter("Access-Control-Allow-Origin", "address for your front-end here")
//                );
//    }

}
