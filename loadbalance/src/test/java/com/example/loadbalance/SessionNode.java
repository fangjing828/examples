package com.example.loadbalance;

import java.util.HashSet;
import java.util.Set;

public class SessionNode extends IntNode {
    private final Set<Session> stickySession = new HashSet<>();

    public SessionNode(int value) {
        super(value);
    }

    @Override
    public void bindSession(Session session) {
        stickySession.add(session);
    }

    @Override
    public boolean containsSession(Session session) {
        return stickySession.contains(session);
    }
}
