package com.project.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionToMapUtils {
	public static <K,V> Map<K,List<V>> getKVListMap(K key,V value, Map<K,List<V>> map){
		if(map == null) throw new NullPointerException("map is not initialize");
		if(map.containsKey(key)) {
			map.get(key).add(value);
		}else {
			List<V> list = new ArrayList<V>();
			list.add(value);
			map.put(key, list);
		}
		return map;
	}
	public static <K,V> Map<K,Set<V>> getKVSetMap(K key,V value, Map<K,Set<V>> map){
		if(map == null) throw new NullPointerException("map is not initialize");
		if(map.containsKey(key)) {
			map.get(key).add(value);
		}else {
			Set<V> list = new HashSet<V>();
			list.add(value);
			map.put(key, list);
		}
		return map;
	}
	
	public static <K,V> Map<K,V> getKVMap(K key,V value,Map<K,V> map) {
		if(map == null) throw new NullPointerException("map is not initialize");
		map.put(key, value);
		return map;
	}
}
