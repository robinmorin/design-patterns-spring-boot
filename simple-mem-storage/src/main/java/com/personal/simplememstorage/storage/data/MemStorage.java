package com.personal.simplememstorage.storage.data;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MemStorage is a class that implements the storage interface and is used to store objects in memory.
 * This class manages the objects in memory and provides the methods to save, update and get the objects.
 *
 * This class implements the design pattern Template
 * {@link <a href="https://refactoring.guru/design-patterns/template-method">Template Method</a>}
 *
 * @param <K>
 * @param <V>
 */
public abstract class MemStorage<K,V> {

    private final ConcurrentHashMap<K,V> mapContainer = new ConcurrentHashMap<>();

    public V save(K id, V object) {
        return mapContainer.compute(id, (k,v)-> v = object);
    }

    public V update(K id, V newObject) {
        return mapContainer.put(id, newObject);
    }

    public Optional<V> getObject(K id) {
        return Optional.ofNullable(mapContainer.get(id));
    }

    public Optional<K> getLastObject() {
        return mapContainer.keySet().stream().sorted((k1, k2) -> ((Comparable<K>) k2).compareTo(k1)).findFirst();
    }

    public Optional<List<V>> getAllObjects() {
        return Optional.of(mapContainer.values().stream().toList());
    }

}
