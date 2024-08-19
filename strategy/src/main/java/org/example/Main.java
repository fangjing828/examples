package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        double E = 1000;
        double r = 0.2;
        double X = 0;
        for (int i = 0; i < 100; i ++) {
            if (ThreadLocalRandom.current().nextInt(10) >= 6) {
                E = E  * (1 - r);
                X -= 1000 * r;
                System.out.println("lost:" + E);
            } else {
                E = E  * (1 + r);
                X += 1000 * r;
                System.out.println("win:" + E);
            }
//            if (E < 10) {
//                break;
//            }
        }
        System.out.println("winX:" + (X - 100));

    }
}