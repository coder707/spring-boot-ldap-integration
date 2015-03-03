package com.example.repositories.ldap;

import com.example.entities.ldap.LdapUser;
import org.springframework.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {
}
