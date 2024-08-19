package com.example.loadbalance;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ConsumerBalance {
    StickySessionLoadBalance lb = new StickySessionLoadBalance();

    @Test
    public void test() {
        List<LoadSessionNode> nodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nodes.add(new LoadSessionNode(i));
        }

        List<Session> sessions = new ArrayList<>();
        for (int i = 0; i < 107; i++) {
            Session.newSession();
            sessions.add(Session.current());
            SessionNode selectedNode = lb.select(nodes);
            System.out.println(selectedNode);
            for (int j = 0; j < 10; j++) {
                assertSame(selectedNode, lb.select(nodes));
            }
        }

        int load = sessions.size() / nodes.size();
        int mod = sessions.size() % nodes.size();
        for (LoadSessionNode node : nodes) {
            int extra = 0;
            if (mod > 0) {
                mod--;
                extra = 1;
            }
            node.setLoad(load + extra);
        }

        LinkedList<Session> unassignedSessions = new LinkedList<>();
        for (Session session : sessions) {
            if (lb.select(nodes).add(session)) {
                continue;
            }
            unassignedSessions.add(session);
        }

        for (LoadSessionNode node : nodes) {
            while (!unassignedSessions.isEmpty() && node.add(unassignedSessions.getFirst())) {
                unassignedSessions.removeFirst();
            }
        }
    }
}
