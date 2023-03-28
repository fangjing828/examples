package com.examples.protobuf.parse;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/28
 */
public class ParseTest {
    CompleteNotify completeNotify;

    @BeforeEach
    void setUp() {
        completeNotify = CompleteNotify.newBuilder()
                .setAccountId(1)
                .setUserId(2)
                .setVersion(3)
                .setSendTime(4)
                .addPosition(Position.newBuilder()
                        .setSymbol("symbol-1")
                        .setBaseCoin("baseCoin-1")
                        .setQuoteCoin("quoteCoin-1")
                        .setSettleCoin("settleCoin-1")
                        .setPosition("position-1")
                        .build())
                .addPosition(Position.newBuilder()
                        .setSymbol("symbol-2")
                        .setBaseCoin("baseCoin-2")
                        .setQuoteCoin("quoteCoin-2")
                        .setSettleCoin("settleCoin-2")
                        .setPosition("position-2")
                        .build())
                .build();
    }

    @Test
    void testSimpleNotifyWithoutMiddleFields() throws InvalidProtocolBufferException {
        SimpleNotifyWithoutMiddleFields simpleNotify = SimpleNotifyWithoutMiddleFields.newBuilder()
                .setAccountId(ThreadLocalRandom.current().nextLong())
                .setUserId(ThreadLocalRandom.current().nextLong())
                .setSendTime(System.currentTimeMillis())
                .build();
        CompleteNotify completeNotify = CompleteNotify.parseFrom(simpleNotify.toByteArray());

        assertEquals(simpleNotify.getAccountId(), completeNotify.getAccountId());
        assertEquals(simpleNotify.getUserId(), completeNotify.getUserId());
        assertEquals(simpleNotify.getSendTime(), completeNotify.getSendTime());
    }

    @Test
    void testSetSimpleFieldByByteString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InvalidProtocolBufferException {
        Method BYTE_STRING_WRAP = ByteString.class.getDeclaredMethod("wrap", byte[].class);
        BYTE_STRING_WRAP.setAccessible(true);
        CompleteNotifyGateway completeNotifyGateway = CompleteNotifyGateway.newBuilder()
                .addPosition(completeNotify.getPosition(0).toByteString())
                // 从数组转成 ByteString 内存性能最优做法
                .addPosition((ByteString) BYTE_STRING_WRAP.invoke(null, completeNotify.getPosition(1).toByteArray()))
                .build();
        CompleteNotify targetNotify = CompleteNotify.parseFrom(completeNotifyGateway.toByteArray());
        assertEquals(completeNotify.getPositionList().get(0), targetNotify.getPositionList().get(0));
        assertEquals(completeNotify.getPositionList().get(1), targetNotify.getPositionList().get(1));
    }

    @Test
    void testSetComplexFieldByByteString() throws InvalidProtocolBufferException {
        CompleteNotify notify = CompleteNotify.newBuilder()
                .setWallet(Wallet.newBuilder()
                        .setBalance(999999999)
                        .setBaseCoin("BTC")
                        .setQuoteCoin("USD")
                        .setSettleCoin("USDT")
                        .build())
                .build();
        CompleteNotifyGateway completeNotifyGateway = CompleteNotifyGateway.newBuilder()
                .setWallet(notify.getWallet().toByteString())
                .build();
        CompleteNotify newNotify = CompleteNotify.parseFrom(completeNotifyGateway.toByteArray());
        System.out.println(notify.equals(newNotify));
    }

    @Test
    void testPartialParse_whenMissMiddleFields() throws InvalidProtocolBufferException {
        SimpleNotifyWithoutMiddleFields simpleNotify =
                SimpleNotifyWithoutMiddleFields.parseFrom(completeNotify.toByteArray());
        assertEquals(simpleNotify.getAccountId(), completeNotify.getAccountId());
        assertEquals(simpleNotify.getUserId(), completeNotify.getUserId());
        assertEquals(simpleNotify.getSendTime(), completeNotify.getSendTime());
    }

    @Test
    void testPartialParse_whenMissTailFields() throws InvalidProtocolBufferException {
        SimpleNotifyWithoutTailFields simpleNotify = SimpleNotifyWithoutTailFields.parseFrom(completeNotify.toByteArray());
        assertEquals(simpleNotify.getAccountId(), completeNotify.getAccountId());
        assertEquals(simpleNotify.getUserId(), completeNotify.getUserId());
    }


}
