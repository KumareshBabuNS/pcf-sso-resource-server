package com.example.manulifedemoproducer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class SecurityConfig {


    @Configuration
    protected static class ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId("contact");
        }

//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			http
//					.authorizeRequests()
//					.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('contact.read')")
//					.antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('contact.admin')");
//		}
    }
}

