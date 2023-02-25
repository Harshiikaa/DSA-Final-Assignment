/*
Question 4
        a)	Design and Implement LFU caching
*/

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {
    private int capacity;
    private Map<Integer, Integer> cache;
    private Map<Integer, Integer> freq;
    private Map<Integer, LinkedHashSet<Integer>> freqKeys;
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freq = new HashMap<>();
        this.freqKeys = new HashMap<>();
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        int value = cache.get(key);
        int keyFreq = freq.get(key);
        freq.put(key, keyFreq + 1);
        freqKeys.get(keyFreq).remove(key);
        if (keyFreq == minFreq && freqKeys.get(keyFreq).isEmpty()) {
            minFreq++;
        }
        freqKeys.computeIfAbsent(keyFreq + 1, k -> new LinkedHashSet<>()).add(key);
        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }
        if (cache.size() >= capacity) {
            int evict = freqKeys.get(minFreq).iterator().next();
            cache.remove(evict);
            freq.remove(evict);
            freqKeys.get(minFreq).remove(evict);
        }
        cache.put(key, value);
        freq.put(key, 1);
        freqKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
    }
}



