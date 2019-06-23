package com.example.demo.immutable.object;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImmutableTest {
    public static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4);
    public static ImmutableSet<Integer> set = ImmutableSet.copyOf(list);
    public static ImmutableMap<Integer, Integer> map = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4)
            .build();

    public static void main(String[] args) {
        log.info("list.size:" + list.size());
        log.info("set.size:" + set.size());
        log.info("map.size:" + map.size());
    }

}
