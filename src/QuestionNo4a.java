/*
Question 4
        a)	Design and Implement LFU caching
*/

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

// Constructor that takes the capacity of the cache as a parameter and initializes the instance variables
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
    // Method for retrieving the value associated with a given key from the cache
    public int get(int key) {
        // If the key is not in the cache, return -1
        if (!cache.containsKey(key)) {
            return -1;
        }
        // Retrieve the value associated with the key and the key's frequency
        int value = cache.get(key);
        int keyFreq = freq.get(key);
        // Increment the key's frequency in the frequency map
        freq.put(key, keyFreq + 1);
        // Remove the key from its old frequency key set
        freqKeys.get(keyFreq).remove(key);
        // If the old frequency was the minimum frequency and its key set is now empty, update the minimum frequency
        if (keyFreq == minFreq && freqKeys.get(keyFreq).isEmpty()) {
            minFreq++;
            // Add the key to its new frequency key set
        }
        freqKeys.computeIfAbsent(keyFreq + 1, k -> new LinkedHashSet<>()).add(key);
        // Return the value associated with the key
        return value;
    }

    // Method for inserting a key-value pair into the cache
    public void put(int key, int value) {
        // If the capacity is zero or negative, do nothing
        if (capacity <= 0) {
            return;
        }
        // If the key is already in the cache, update its value and frequency
        if (cache.containsKey(key)) {
            cache.put(key, value);
            get(key);
            return;
        }
        // If the cache is at capacity, evict the least frequently used key
        if (cache.size() >= capacity) {
            int evict = freqKeys.get(minFreq).iterator().next();
            cache.remove(evict);
            freq.remove(evict);
            freqKeys.get(minFreq).remove(evict);
        }
        // Add the new key-value pair to the cache, with frequency 1
        cache.put(key, value);
        freq.put(key, 1);
        // Add the key to the frequency key set for frequency 1
        freqKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        // Update the minimum frequency to 1
        minFreq = 1;
    }

    // Main method for testing the LFU cache
    public static void main(String[] args) {
        // Create a new LFU cache with capacity 5
        LFUCache lfuCache = new LFUCache(5);
        // Add some key-value pairs to the cache
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        // Retrieve the value associated with key 1 (should be 1)
        System.out.println(lfuCache.get(1)); // returns 1
        // Add another key-value pair to the cache, evicting key 2
        lfuCache.put(3, 3); // evicts key 2
        System.out.println(lfuCache.get(4)); // returns -1 (not found)
        System.out.println(lfuCache.get(3)); // returns 3
        lfuCache.put(4, 4); // evicts key 1
        System.out.println(lfuCache.get(5)); // returns -1 (not found)
        System.out.println(lfuCache.get(3)); // returns 3
        System.out.println(lfuCache.get(4)); // returns 4

    }
}

