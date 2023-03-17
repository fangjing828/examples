package com.examples.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class OutOfMemoryErrorTest {
    public static void main(String[] args) {
        byte[] bytes = {0x1a, 0x5b};
        String s = new String(bytes);
        System.out.println(s);
        JSON.parseObject("[", new TypeReference<ArrayList<Integer>>() {});
    }
    // https://github.com/alibaba/fastjson/issues/4309
    // Exception in thread "main" com.alibaba.fastjson.JSONException: Java heap space
    //	at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:710)
    //	at com.alibaba.fastjson.JSON.parseObject(JSON.java:394)
    //	at com.alibaba.fastjson.JSON.parseObject(JSON.java:362)
    //	at com.alibaba.fastjson.JSON.parseObject(JSON.java:276)
    //	at com.examples.fastjson.ParseObjectTest.main(ParseObjectTest.java:17)
    //Caused by: java.lang.OutOfMemoryError: Java heap space
    //	at java.base/java.util.Arrays.copyOf(Arrays.java:3512)
    //	at java.base/java.util.Arrays.copyOf(Arrays.java:3481)
    //	at java.base/java.util.ArrayList.grow(ArrayList.java:237)
    //	at java.base/java.util.ArrayList.grow(ArrayList.java:244)
    //	at java.base/java.util.ArrayList.add(ArrayList.java:454)
    //	at java.base/java.util.ArrayList.add(ArrayList.java:467)
    //	at com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(DefaultJSONParser.java:794)
    //	at com.alibaba.fastjson.serializer.CollectionCodec.deserialze(CollectionCodec.java:132)
    //	at com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(DefaultJSONParser.java:705)
    //	... 4 more
}
