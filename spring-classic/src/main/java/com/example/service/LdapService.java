package com.example.service;

import com.example.ldap.entities.LdapUser;
import com.example.ldap.repositories.LdapUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

@Service
public class LdapService {

    @Autowired
    private LdapUserRepository ldapUserRepository;

    public LdapUser findUser(final String email) {
        return ldapUserRepository.findOne(LdapUtils.newLdapName("cn=" + email + ",cn=users,ou=accounts"));
    }
}
