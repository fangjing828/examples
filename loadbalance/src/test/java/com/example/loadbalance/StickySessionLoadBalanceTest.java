package com.example.loadbalance;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

class StickySessionLoadBalanceTest {
    StickySessionLoadBalance lb = new StickySessionLoadBalance();

    @Test
    void testSelect() {
        List<SessionNode> nodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            nodes.add(new SessionNode(i));
        }

        for (int i = 0; i < 100; i++) {
            Session.newSession();
            SessionNode selectedNode = lb.select(nodes);
            System.out.println(selectedNode);
            for (int j = 0; j < 10; j++) {
                assertSame(selectedNode, lb.select(nodes));
            }
        }
    }
}