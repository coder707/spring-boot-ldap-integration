package com.example.entities.ldap;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry (
        objectClasses = {
                "posixAccount",
                "account"
        },

        base = "cn=users,ou=accounts"
)
public final class LdapUser {

    @Id
    private Name dn;

    @Attribute(name = "uid")
    @DnAttribute(value = "cn", index = 1)
    private String uid;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "uidNumber")
    private String uidNumber;

    @Attribute(name = "gidNumber")
    private String gidNumber;

    @Attribute(name = "homeDirectory")
    private String homeDirectory;

    @Attribute(name = "userPassword")
    private String password;

    public Name getDn() {
        return dn;
    }

    public void setDn(final Name dn) {
        this.dn = dn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(final String commonName) {
        this.commonName = commonName;
    }

    public String getUidNumber() {
        return uidNumber;
    }

    public void setUidNumber(final String uidNumber) {
        this.uidNumber = uidNumber;
    }

    public String getGidNumber() {
        return gidNumber;
    }

    public void setGidNumber(final String gidNumber) {
        this.gidNumber = gidNumber;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(final String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
