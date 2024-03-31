package com.personal.pattern.observer.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/**
 * SimpleMemStorageRepository is a generic interface that defines the basic operations for a repository that stores data in memory.
 * It's the contract of the strategy that will be used to store the entities.
 * Every implementation of this interface will be responsible for storing the entities in the strategy that it defines.
 *  - Strategy: Implementing the Storage interface, this class is one implementation of strategy type Repository.
 *    @link <a href="https://refactoring.guru/design-patterns/strategy">Strategy</a>
 *  - Repository: This class is a light implementation of the design patterns Repository, because it is a class that manages the data entities with the layer of data access.
 *    @link <a href="https://renicius-pagotto.medium.com/entendendo-o-repository-pattern-fcdd0c36b63b">Repository</a>
 *
 * @param <T> the type of the entity to be stored.
 * @param <S> the type of the entity's identifier.
 */
public interface SimpleMemStorageRepository<T,S> {
    T save(T value);
    Optional<T> findById(S id);

    @SuppressWarnings("unchecked")
    default Class<T> getGenericType() {
        Type[] genericTypes = getClass().getGenericInterfaces();
        ParameterizedType superType = (ParameterizedType) genericTypes[0];
        return (Class<T>) superType.getActualTypeArguments()[0];
    }
}
