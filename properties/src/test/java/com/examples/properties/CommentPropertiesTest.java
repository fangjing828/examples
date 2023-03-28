package com.examples.properties;


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author alex.fang
 * @date 2023/3/27
 */
class CommentPropertiesTest {
    @Test
    void testWithChineseComment() throws IOException{
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("with_chinese_comment.properties")) {
            asset(is);
        }
    }

    @Test
    void testWithComment() throws IOException{
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("with_comment.properties")) {
            asset(is);
        }
    }

    @Test
    void testWithoutComment() throws IOException{
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("without_comment.properties")) {
            asset(is);
        }
    }

    void asset(InputStream is) throws IOException {
        assertNotNull(is);
        byte[] bytes = is.readAllBytes();

        String content = new String(bytes, StandardCharsets.UTF_8);
        CommentProperties properties = new CommentProperties();
        CommentProperties.setOriginContent(content);
        properties.load(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        properties.store(os, null);
        assertArrayEquals(bytes, os.toByteArray());
    }
}