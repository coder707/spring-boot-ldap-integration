package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.repository.config.EnableLdapRepositories;
import org.springframework.security.ldap.authentication.PasswordComparisonAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;

@Configuration
@EnableLdapRepositories("com.example.repositories.ldap")
public class LdapConfiguration {

    @Autowired
    private LdapServerConfigurer ldapServerConfigurer;

    @Bean
    public LdapTemplate ldapTemplate() {
        final LdapTemplate ldapTemplate = new LdapTemplate();

        ldapTemplate.setContextSource(ldapServerConfigurer.contextSource());

        return ldapTemplate;
    }

    @Bean
    public FilterBasedLdapUserSearch filterBasedLdapUserSearch() {
        return new FilterBasedLdapUserSearch("ou=accounts", "cn={0}", ldapServerConfigurer.contextSource());
    }
}
