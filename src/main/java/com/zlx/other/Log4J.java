package com.zlx.other;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Log4J {

    private static final Logger logger = LogManager.getLogger(Log4J.class);

    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");

//        String arg = args[0];
        Scanner scanner = new Scanner(System.in);
        String arg = scanner.nextLine();
        logger.error("hello:"+arg);
    }
}
