package collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapDemo {

    public static void main(String[] args) {
        hashMapDemo();
        log.info("***********************");
        linkedHashMapDemo();
    }

    public static void hashMapDemo() {
        Map<String, String> map = new HashMap<>();
        map.put("apple", "苹果1");
        map.put("watermelon", "西瓜2");
        map.put("banana", "香蕉3");
        map.put("peach", "桃子4");
        for (Entry<String, String> s : map.entrySet()) {
            log.info("key={},value={}", s.getKey(), s.getValue());
        }
    }

    public static void linkedHashMapDemo() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("apple", "苹果1");
        map.put("watermelon", "西瓜2");
        map.put("banana", "香蕉3");
        map.put("peach", "桃子4");
        // for (Entry<String, String> s : map.entrySet()) {
        // log.info("key={},value={}", s.getKey(), s.getValue());
        // }
        map.get("watermelon");
        map.get("banana");
        log.info("***********************");
        for (Entry<String, String> s : map.entrySet()) {
            log.info("key={},value={}", s.getKey(), s.getValue());
        }
    }

}
