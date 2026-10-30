package benedicto.learnspring.config;

import benedicto.learnspring.security.CustomUserDetailsService;
import benedicto.learnspring.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService customerUserDetailsService;

    public SecurityConfig (JwtAuthenticationFilter jwtAuthFilter, CustomUserDetailsService customerUserDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //1. CSRF tidak diperlukan sebab kita menggunakan stateless REST API (JWT disimpan di local storage) bukan berbasis session/cokkies
                .csrf(AbstractHttpConfigurer::disable)

                //2. Buat session manajemen menjadi stateless (spring tidak menyimpan session di memori server)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        //mendata endpoint public yang bebas diakses tanpa perlu login
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/v3/api/docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        //endpoint lain wajib membawa token
                        .anyRequest().authenticated()
                )

                //4. Daftarkan AuthenticationProvider (penghubung UserDetailsService + BCrypt)
                .authenticationProvider(authenticationProvider())

                //5. Pasang satpam sebelum filter autentikasi bawaan spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    //Bean untuk mengenkripsi password menggunakan algoritma BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Provider autentikasi yang memeriksa username & verifikasi hash password
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customerUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //Engine utama yang akan dipanggil saat proses login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
