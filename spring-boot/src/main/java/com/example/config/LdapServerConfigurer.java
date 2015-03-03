package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@PropertySource("classpath:ldap.properties")
public class LdapServerConfigurer {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.userDn}")
    private String ldapManagerDn;

    @Value("${ldap.password}")
    private String ldapPassword;

    @Bean
    public LdapContextSource contextSource() {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(ldapUrl);

        contextSource.setUserDn(ldapManagerDn);
        contextSource.setPassword(ldapPassword);

        return contextSource;
    }
}
