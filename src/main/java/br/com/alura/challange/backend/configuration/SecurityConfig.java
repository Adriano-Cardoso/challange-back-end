package br.com.alura.challange.backend.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import br.com.alura.challange.backend.configuration.securty.JwtConfigurer;
import br.com.alura.challange.backend.configuration.securty.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.httpBasic().disable().csrf().disable().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().headers()
					.frameOptions().disable().and().authorizeRequests().anyRequest().permitAll();
		} else {
			http.httpBasic().disable().csrf().disable().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().headers()
					.frameOptions().disable().and().authorizeRequests().antMatchers("/swagger.html").permitAll().and()
					.authorizeRequests().antMatchers("/swagger-ui.html").permitAll().and().authorizeRequests()
					.antMatchers("/auth").permitAll().and().authorizeRequests()
					.antMatchers("/despesas/**", "/receitas/**", "/resumo/**").authenticated().and()
					.apply(new JwtConfigurer(jwtTokenProvider));
		}
	}

}
