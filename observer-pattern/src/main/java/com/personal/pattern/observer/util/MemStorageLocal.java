package com.personal.pattern.observer.util;

import com.personal.simplememstorage.storage.annotations.IdObject;
import com.personal.simplememstorage.storage.data.DataSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.InvalidClassException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/***
 * Class to manage the DataSet instances in memory.
 * This class is a MonoState pattern implementation
 * {@link <a href="https://medium.com/fora-de-assunto/os-design-patterns-singleton-e-monostate-nas-palavras-de-uncle-bob-f2d1bec6a02c">MonoState Pattern</a>}
 */
@Slf4j
@Component
public class MemStorageLocal {

    /***
     * Map to store the DataSet instances
     * DataSet are managed by the entity name in library simple-mem-storage  (simple-mem-storage:0.0.1-SNAPSHOT)
     * Could be need download and build the library to use this class.
     * Reference GitHub: @link <a href="https://github.com/robinmorin/design-patterns-spring-boot/tree/simple-mem-storage">Simple Mem Storage Branch</a>
     */
    private static final Map<String, DataSet<?>> dataSets = new ConcurrentHashMap<>();

    /***
     * Get the DataSet for the given entity
     * @param entity
     * @return Optional<DataSet<T>>
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<DataSet<T>> getDataSet(Class<T> entity) {
        try {
            var dataset = dataSets.get(entity.getSimpleName().toUpperCase());
            if(Objects.isNull(dataset)) {
                dataset = createDataSetInstance(entity);
                dataSets.put(entity.getSimpleName().toUpperCase(), dataset);
            }
            return Optional.of((DataSet<T>)dataset);
        } catch (InvalidClassException e) {
            return Optional.empty();
        }
    }

    /***
     * Initialize the DataSet for the given entities
     * @param entityDataSets
     */
    public void initDataSet(Class<?>...entityDataSets) {
        List<String> datasetsError = new ArrayList<>();
        for (Class<?> entity : entityDataSets) {
            try {
                dataSets.put(entity.getSimpleName().toUpperCase(), createDataSetInstance(entity));
            } catch (InvalidClassException e) {
                datasetsError.add(String.format("Error building DataSet for Entity %s. Error: %s",entity.getSimpleName(),e.getMessage()));
            }
        }
        if(!datasetsError.isEmpty()){
            datasetsError.forEach(log::error);
        } else {
            Arrays.stream(entityDataSets).map(ds -> String.format("DataSet for Entity %s has been created", ds.getSimpleName()))
                                         .forEach(log::info);
        }
    }

    /***
     * Strategy for create a DataSet instance for a given type class and
     * check if the class has a field marked with @IdObject annotation
     * @param clazz
     * @return DataSet<T>
     * @param <T>
     * @throws InvalidClassException
     */
    private <T> DataSet<T> createDataSetInstance(Class<T> clazz) throws InvalidClassException {
        checkId(clazz);
        return new DataSet<>();
    }

    /***
     * Check if the class has a field marked with @IdObject annotation
     * @param id
     * @param <T>
     * @throws InvalidClassException
     */
    private <T> void checkId(Class<T> id) throws InvalidClassException {
        if(Arrays.stream(id.getDeclaredFields())
                .noneMatch(field -> field.isAnnotationPresent(IdObject.class))) {
            throw new InvalidClassException("Type Object for DataSet must have a field marked with @IdObject annotation");
        }
    }

    /***
     * Save/Replace an item in the DataSet
     * @param item
     * @return
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> T saveItem(T item) {
        getDataSet(item.getClass()).map(ds -> (DataSet<T>) ds).ifPresent(ds -> ds.saveItem().accept(item));
        return item;
    }

    /***
     * Get an item from the DataSet
     * @param idItem Id of the item to get
     * @param tClass Class Type of the item to get
     * @return Optional<T>
     * @param <T>
     * @param <S>
     */
    public <T,S> Optional<T> getItem(S idItem, Class<T> tClass) {
        var ds = getDataSet(tClass);
        return ds.map(objectDataSet -> objectDataSet.getItem(idItem))
                     .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Record not found"));
    }

}
