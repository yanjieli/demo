package com.example.demo.immutable.object;

import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnmodifiedMap {
    public static final Map<Integer, Integer> map = Maps.newHashMap();
    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
    }
    public static final Map<Integer, Integer> map2 = Collections.unmodifiableMap(map);

    public static void main(String[] args) {
        log.info("map.size:" + map.size());
        map.put(5, 6);
        log.info("map.size:" + map.size());
        // map2.put(3, 4);
        Map<Integer, Integer> map3 = getMap();
        map3.put(6, 7);
        log.info("map2.size:" + map3.size());
    }

    public static Map<Integer, Integer> getMap() {
        return Collections.unmodifiableMap(map);
    }

}
