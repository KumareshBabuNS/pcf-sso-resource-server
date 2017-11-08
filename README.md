(Assume you have created SSO service instance.) 

Before cf push, create resource in PCF SSO Dashboard ![alt text](/resource.png)


Then make sure you have these dependencies 
```xml
<dependency>
    <groupId>org.springframework.security.oauth</groupId>
    <artifactId>spring-security-oauth2</artifactId>
    <version>2.2.0.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
<dependency>
    <groupId>io.pivotal.spring.cloud</groupId>
    <artifactId>spring-cloud-sso-connector</artifactId>
    <version>2.1.1.RELEASE</version>
</dependency>
```


Need below SecurityConfig for Resource ID
```java
@EnableResourceServer
@Configuration
public class SecurityConfig {

    @Configuration
    protected static class ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId("contact");
        }
    }
}
```

In order to make the ```@PreAuthorize``` annotation work, you need 
```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
```

Please refer to Client Credential sample [here](https://github.com/dwong-pivotal/pcf-sso-client-cred)