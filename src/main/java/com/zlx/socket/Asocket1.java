package com.zlx.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Asocket1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("heavens");
        InetAddress ss = InetAddress.getLocalHost();

        System.out.println(ss);
        System.out.println(address);
    }
}
