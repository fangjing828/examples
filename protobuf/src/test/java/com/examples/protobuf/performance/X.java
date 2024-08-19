package com.examples.protobuf.performance;

public class X {
    public static void main(String[] args) {
        int size = 10000000;  // One million.
        for (int i = 0; i < 10; i++) {
            new MyThread(size).start();
        }

        long stop = 0;
        long start = System.nanoTime();

        int loops = 1000000;  // One million.
        for ( int i = 0; i < loops; i++ ) {
            String uuid = java.util.UUID.randomUUID().toString();
        }

        stop = System.nanoTime();

        long elapsed = ( stop - start );

        System.out.println( "UUIDs: " + loops );
        System.out.println( "Nanos: " + elapsed );
        System.out.println( "Nanos per uuid: " + ( elapsed / loops ) + " ( micros per: " + ( elapsed / loops / 1000 ) + " )" );
    }

    static class MyThread extends Thread {

        private int loops;

        public MyThread( int loops ) {
            this.loops = loops;
        }

        @Override
        public void run() {
            java.util.UUID uuid;
            for ( int i = 0; i < this.loops; i++ ) {
                uuid = java.util.UUID.randomUUID();
            }
        }
    }
}
