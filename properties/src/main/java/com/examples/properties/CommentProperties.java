package com.examples.properties;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author alex.fang
 * @date 2023/3/27
 */
public class CommentProperties extends Properties {
    static final ThreadLocal<String> ORIGIN_CONTENT = new ThreadLocal<>();
    private final Map<String, List<String>> key2Comment = new LinkedHashMap<>();

    static void setOriginContent(String content) {
        ORIGIN_CONTENT.set(content);
    }

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        super.load(inStream);
        List<String> lines = getLines();
        if (lines.isEmpty()) {
            return;
        }
        List<String> comments = new ArrayList<>();
        for (String line : lines) {
            if (Strings.isNullOrEmpty(line)) {
                continue;
            }
            char c = line.charAt(0);

            if (c == '#' || c == '!') {
                comments.add(line);
                continue;
            }

            int index = line.indexOf('=');
            if (index == -1) {
                continue;
            }

            String key = line.substring(0, index).trim();
            key2Comment.put(key, comments);
            comments = new ArrayList<>();
        }
    }

    @Override
    public void store(OutputStream out, String comments) throws IOException {
        Set<String> keys = new LinkedHashSet<>(this.stringPropertyNames());
        boolean firstLine = true;
        for (Map.Entry<String, List<String>> entry : key2Comment.entrySet()) {
            String key = entry.getKey();
            if (keys.remove(key)) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    out.write('\n');
                }
                List<String> comment = entry.getValue();
                if (!comment.isEmpty()) {
                    out.write(Joiner.on('\n').join(comment).getBytes(StandardCharsets.UTF_8));
                    out.write('\n');
                }

                out.write(String.format("%s=%s", key, getProperty(key)).getBytes(StandardCharsets.UTF_8));
            }
        }
        for (String key : keys) {
            if (firstLine) {
                firstLine = false;
            } else {
                out.write('\n');
            }
            out.write(String.format("%s=%s", key, getProperty(key)).getBytes(StandardCharsets.UTF_8));
        }
    }

    List<String> getLines() {
//        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inStream));
        String content = ORIGIN_CONTENT.get();
        if (content == null) {
            return Collections.emptyList();
        }
        return Splitter.on('\n').splitToList(content);
    }
}
