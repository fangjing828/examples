package com.example.loadbalance;

import java.util.UUID;

public class Session {
    private static final ThreadLocal<Session> LOCAL_SESSION = ThreadLocal.withInitial(() -> new Session(UUID.randomUUID().toString()));

    public static Session current() {
        return LOCAL_SESSION.get();
    }

    public static void newSession() {
        LOCAL_SESSION.set(new Session(UUID.randomUUID().toString()));
    }

    private final String sessionId;

    public Session(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
