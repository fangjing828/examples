package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Coin {
    /**缓存coin实例*/
    private static final ConcurrentMap<String, Coin> instances = new ConcurrentHashMap<>(7);
    /**币种代码，如BTC, ETH, BIT*/
    private final String code;

    private Coin(String code) {
        this.code = code;
    }

    /**
     * 获取币种代码
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * 根据币种名称获取币种实例，若币种不存在则创建后放入缓存再返回，多线程情况下以第一次放入缓存的实例为准
     *
     * @param coinName the coin name
     * @return the coin instance
     */
    public static Coin getInstance(String coinName) {
        if(coinName == null || coinName.isBlank()) {
            throw new IllegalArgumentException("coinName is null or blank");
        }
        return instances.computeIfAbsent(coinName, Coin::new);
    }

    public static Coin usdc() {
        return Coin.getInstance("USDC");
    }

    public static Coin usdt() {
        return Coin.getInstance("USDT");
    }

    public static Coin usd() {
        return Coin.getInstance("USD");
    }

    public static Coin btc() {
        return Coin.getInstance("BTC");
    }

    public static Coin eth() {
        return Coin.getInstance("ETH");
    }

    public static Coin bit() {
        return Coin.getInstance("BIT");
    }
}
