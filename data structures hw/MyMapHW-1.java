/*
 * CIST 004B1 Spring 2024
 * HW Week 10 Problem 1
 * Description: Implement mymap using double hashing
 * Input: n/a
 * Output: n/a
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/22/2024
 */

package chapter27;

import java.util.*;


class MyMap2<K, V> implements MyMapHW<K, V>{
	
	
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	private static int MAXIMUM_CAPACITY = 1 << 30;
	private int capacity;
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.50f;
	private float loadFactorThreshold;
	private int size = 0;
	ArrayList<MyMapHW.Entry<K,V>> table;
	public MyMapHW.Entry<K, V> SENTINEL = new MyMapHW.Entry<>(null, null);
	
	public MyMap2() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	public MyMap2(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	public MyMap2(int initialCapacity, float loadFactor) {
		if (initialCapacity > MAXIMUM_CAPACITY) {
			this.capacity = MAXIMUM_CAPACITY;
		} else {
			this.capacity = trimToPowerOf2(initialCapacity);
		}
		
		this.loadFactorThreshold = loadFactor;
		table = new ArrayList<>();
		for (int i = 0; i < capacity; i++) {
			table.add(null);
		}
	}
	

	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity *= 2;
		}
		return capacity;
	}

	@Override
	public void clear() {
		size = 0;
		removeEntries();
		
	}

	private void removeEntries() {
		table.clear();
		
	}

	@Override
	public boolean containsKey(K key) {
		if(get(key) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null) {
				if (table.get(i).getValue() == value) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<MyMapHW.Entry<K, V>> set = new HashSet<>();
		
		for (int i = 0; i < capacity; i++) {
			if(table.get(i) != null) {
				set.add(table.get(i));
			}
		}
		return set;
	}

	@Override
	public V get(K key) {
		
		int hashed = hash(key.hashCode());
		int index = hashed;
		int j = 1;
		while (table.get(index) != null) {
			if (table.get(index).getKey() == null) {
				return null;
			}
			if (table.get(index).getKey().equals(key)) {
				return table.get(index).getValue();
			} 
			
			
			index = hashed + j*secondHash(key.hashCode());
			j++;
			index %= capacity;
			
		}
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();
		
		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null) {
				set.add(table.get(i).getKey());
			}
		}
		return set;
	}

	@Override
	public V put(K key, V value) {
		int hashed = hash(key.hashCode());
		int j;
		int index;
		if(get(key) != null) {
			index = hashed;
			j = 1;
			while(true) {
				if (table.get(index).getKey().equals(key)) {
					Entry<K, V> grab = table.get(index);
					V oldValue = grab.getValue();
					grab.value = value;
					table.set(index, grab);
					return oldValue;
				}
				
				index = hashed + j*secondHash(key.hashCode());
				j++;
				index %= capacity;
			}
		}
		
		if(size > capacity*loadFactorThreshold) {
			if (capacity == MAXIMUM_CAPACITY) {
				throw new RuntimeException("Exceeding maximum capacity");
			}
			rehash();
		}
		
		int otherIndex = hash(key.hashCode());
		int jj = 0;
		while (table.get(otherIndex) != null && !(table.get(otherIndex).getKey() == null)) {
			otherIndex = hashed + jj * secondHash(key.hashCode());
			jj++;
			otherIndex %= capacity;
		}
		table.set(otherIndex, new MyMapHW.Entry<K, V>(key, value));
		
		size++;
		return null;
	}

	@Override
	public void remove(K key) {
		
		int hashed = hash(key.hashCode());
		int index = hashed;
		int j = 1;
		
		while (true) {
			if (table.get(index) != null && table.get(index).getKey().equals(key)) {
				table.set(index, SENTINEL); 
				size--;
				break;	
			}
			
			index = hashed + j*secondHash(key.hashCode());
			j++;
			index %= capacity;
		}
		
		return;
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public Set<V> values() {
		Set<V> set = new HashSet<>();
		
		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null) {
				set.add(table.get(i).getValue());
			}
		}
		return set;
	}
	
	private int hash(int hashCode) {
		return supplementalHash(hashCode & (capacity-1));
	}
	
	private int secondHash(int hashCode) {
		return 7 - hashCode % 7;
	}
	
	private static int supplementalHash(int h) {
		h^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	private void rehash() {
		Set<Entry<K,V>> set = entrySet();
		capacity *= 2;
		table = new ArrayList<>();
		table.addAll(Collections.nCopies(capacity, null));
		size = 0;
		
		for (Entry<K,V> entry : set) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public String toString() {
		if (table.isEmpty()) {
			return ("[]");
		}
		StringBuilder builder = new StringBuilder("[");
		
		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null) {
				builder.append(table.get(i));
			}
		}
		builder.append("]");
		return builder.toString();
	}
	
}

public interface MyMapHW<K, V> {
	/** Remove all of the entries from this map */
	public void clear();

	/** Return true if the specified key is in the map */
	public boolean containsKey(K key);

	/** Return true if this map contains the specified value */
	public boolean containsValue(V value);

	/** Return a set of entries in the map */
	public java.util.Set<Entry<K, V>> entrySet();

	/** Return the first value that matches the specified key */
	public V get(K key);

	/** Return true if this map contains no entries */
	public boolean isEmpty();

	/** Return a set consisting of the keys in this map */
	public java.util.Set<K> keySet();

	/** Add an entry (key, value) into the map */
	public V put(K key, V value);

	/** Remove the entries for the specified key */
	public void remove(K key);

	/** Return the number of mappings in this map */
	public int size();

	/** Return a set consisting of the values in this map */
	public java.util.Set<V> values();

	/** Define inner class for Entry */
	public static class Entry<K, V> {
		K key;
		V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "{" + key + ", " + value + "}";
		}
	}
}

/*Output:
 *  output from TestMyMap values:

Entries in map: [{2, Two}{3, Three}]
Entries in map: [{5, Five}{2, Two}{3, Three}]
Entries in map: [{5, Five}{2, Two}{3, New Three}]
Entries in map: [{2, Two}{3, New Three}{5, Five}{11, Eleven}]
Two
New Three
Five
Entries in map: [{2, Two}{3, New Three}{5, Five}{11, Eleven}]
Entries in map: [{2, Two}{11, Latest Eleven}{5, Five}{11, New Eleven}{19, Nineteen}]
Entries in map: []
 */

