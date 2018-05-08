package com.dedsec.toolbox;

import java.util.ArrayList;
import java.util.Scanner;

public class aio {
    public static void main(String[] args) {
        String ipAdd = null;
        Scanner read = new Scanner(System.in);
        ArrayList<String> iprange = new ArrayList<String>();
        NetworkMath netMath = new NetworkMath();
        // testing for login error
        System.out.println("Enter IP Address.\n");
        ipAdd = read.next();
        iprange = netMath.networkRange(ipAdd);
        System.out.print(iprange);
        for (int x = 0; x < iprange.size(); x++) {
            System.out.println(iprange.get(x));
        }
    }
}