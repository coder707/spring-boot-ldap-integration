package com.example.ldap.repositories;

import com.example.ldap.entities.LdapUser;
import org.springframework.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {
}
