package com.example.loadbalance;

import java.util.HashSet;
import java.util.Set;

public class LoadSessionNode extends SessionNode {
    private int load;
    private Set<Session> sessions;
    public LoadSessionNode(int value) {
        super(value);
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
        this.sessions = new HashSet<>();
    }

    public boolean add(Session session) {
        if (this.sessions.size() >= load) {
            return false;
        }

        this.sessions.add(session);
        return true;
    }
}
