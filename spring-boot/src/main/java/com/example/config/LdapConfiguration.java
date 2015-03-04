package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.repository.config.EnableLdapRepositories;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;

@Configuration
@PropertySource("classpath:ldap.properties")
@EnableLdapRepositories("com.example.repositories.ldap")
public class LdapConfiguration {

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

    @Bean
    public LdapTemplate ldapTemplate() {
        final LdapTemplate ldapTemplate = new LdapTemplate();

        ldapTemplate.setContextSource(contextSource());

        return ldapTemplate;
    }

    @Bean
    public FilterBasedLdapUserSearch filterBasedLdapUserSearch() {
        return new FilterBasedLdapUserSearch("ou=accounts", "cn={0}", contextSource());
    }
}
