package com.personal.simplememstorage.storage.data;

import com.personal.simplememstorage.storage.annotations.IdObject;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * DataSet is a class that represents the data set.
 * - Is used to store the data in memory.
 * - Manages the objects in memory and provides the methods to save and get the objects.
 * - Would be used for the storage any kind of objects in the memory.
 * - Any type object that will be stored in the memory must have a field marked with @IdObject annotation.
 *
 * This class is a light implementation of the design patterns Repository, because it is a class that manages the data entities with the layer of data access.
 * Has a contract inside for the storage strategy of the data.
 * @link <a href="https://renicius-pagotto.medium.com/entendendo-o-repository-pattern-fcdd0c36b63b">Repository</a>
 *
 */
@SuppressWarnings({"java:S3011","java:S112"})
public class DataSet<V> {

    private final Storage<Object,V> data;

    public DataSet() {
        this.data = new StorageLocal<>();
    }

    public Optional<V> getItem(Object id) {
        return data.getObject(id);
    }

    public Consumer<V> saveItem() {
        return data::save;
    }

    /**
     * Definition contract for the storage strategy as inner interface
     * @param <K>
     * @param <V>
     */
    public interface Storage<K,V> {
        V save(V object);
        V update(K id, V newObject);
        Optional<V> getObject(K id);
    }

    /***
     * Class is a local implementation of the Storage interface.
     *
     * This inner class implements the following design pattern:
     *  - Strategy: Implementing the Storage interface, this class is one implementation of strategy type Storage.
     *    @link <a href="https://refactoring.guru/design-patterns/strategy">Strategy</a>
     *  - Decorator: This class extends the MemStorage class and adds functionality to save method,
     *               for getting field @IdObject as key the item to save.
     *    @link <a href="https://refactoring.guru/design-patterns/decorator">Decorator</a>
     *
     * @param <K>
     * @param <V>
     */
    private static class StorageLocal<K,V> extends MemStorage<K,V> implements Storage<K,V> {
        public StorageLocal() {
                super();
            }

        /***
         * Save an object in the memory by getting the field marked with @IdObject annotation as key.
         * @param object
         * @return
         */
        public V save(V object) {
            return super.save(getIdFieldValue(object), object);
        }

        /***
         * Get the value of the field marked with @IdObject annotation by reflection.
         * @param object It's the object to get the value of the field marked with @IdObject annotation.
         * @return
         */
        @SuppressWarnings("unchecked")
        private K getIdFieldValue(V object) {
            Class<?> clazz = object.getClass();
            return Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(IdObject.class))
                    .findFirst()
                    .map(field -> {
                        try {
                            field.setAccessible(true);
                            return (K) field.get(object);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .orElseThrow(() -> new IllegalArgumentException(String.format("The object %s doesn't have field mark as @IdObject", clazz.getName())));
        }
    }

}
