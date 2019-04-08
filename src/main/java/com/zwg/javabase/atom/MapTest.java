package com.zwg.javabase.atom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 张文刚
 * @Date: 2019/02/15  18:03
 * @Version: V1.0
 * @Description:
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Object> map = new MyMap<>(8);
        map.put("aa", 11);
        map.put("bb", 11);
        map.put("cc", 11);
        map.put("dd", 11);
        System.out.println(map);
    }

    public static class MyMap<K, V> extends HashMap {

        public String toString() {
            Iterator<Entry<K, V>> i = entrySet().iterator();
            if (!i.hasNext())
                return "{}";

            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (; ; ) {
                Entry<K, V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                sb.append(key == this ? "(this Map)" : key);
                sb.append(':');
                sb.append(value == this ? "(this Map)" : value);
                if (!i.hasNext())
                    return sb.append('}').toString();
                sb.append(',').append(' ');
            }
        }


        public MyMap(int initialCapacity) {
            super(initialCapacity);
        }
    }


}
