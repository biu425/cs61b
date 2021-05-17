package lab9;

import org.xml.sax.ext.Attributes2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    public MyHashMap(int initialSize) {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        int i = hash(key);
        return buckets[i].get(key);

    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (value == null) {
            return;
        }
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
        }
        int i = hash(key);
        if (!buckets[i].containsKey(key)) {
            size++;
            buckets[i].put(key, value);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    private void resize(int newSize) {
       MyHashMap newBuckets = new MyHashMap(newSize);
       for (K key : this.keySet()) {
           newBuckets.put(key, this.get(key));
       }
       this.size = newBuckets.size;
       this.buckets = newBuckets.buckets;

    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        if (size() == 0) {
            return null;
        }
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            keyset.addAll(buckets[i].keySet());
        }
        return keyset;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int i = hash(key);
        size--;
        return buckets[i].remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int i = hash(key);
        size--;
        return buckets[i].remove(key, value);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        MyHashMap mhm = new MyHashMap();
        mhm.put("hello", 5);
        mhm.put("cat", 10);
        mhm.put("fish", 22);
        mhm.put("zebra", 90);
        mhm.remove("hello");
        Iterator<String> i = mhm.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
