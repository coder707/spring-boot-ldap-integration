package com.example;

import com.example.service.LdapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LdapTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapTest.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        LdapService ldapService = applicationContext.getBean(LdapService.class);

        LOGGER.info("The service was properly initialized - {}", (ldapService != null));
    }
}
