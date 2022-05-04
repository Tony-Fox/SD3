package com.example.sd2;

import com.example.sd2.service.SpringDataJpaUserDetailsService;
import com.example.sd2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity // <1>
@EnableGlobalMethodSecurity(prePostEnabled = true) // <2>
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { // <3>



//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//				.authorizeRequests()
//				.anyRequest().permitAll()
//				.and().httpBasic();
//	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*", "http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Autowired
	private SpringDataJpaUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(this.userDetailsService)
				.passwordEncoder(UserService.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors(withDefaults())
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").authenticated()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers(HttpMethod.GET, "/cars").authenticated()
				.anyRequest().authenticated()
				.and()
				.httpBasic();

//				.authorizeRequests()
//					.antMatchers("/", "/main.css").permitAll()
//					.anyRequest().authenticated()
//					.and()
//				.formLogin()
//					.defaultSuccessUrl("/", true)
//					.permitAll()
//					.and()
//				.httpBasic()
//					.and()
//				.csrf().disable()
//				.logout()
//						.logoutSuccessUrl("/");
	}

}