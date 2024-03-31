package com.personal.pattern.observer.repository;

import com.personal.pattern.observer.model.Position;
import com.personal.pattern.observer.util.MemStorageLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * Repository class for Position
 * <p>
 *     This class is a simple repository class for Position
 *     It's an implementation of Strategy Pattern of interface SimpleMemStorageRepository
 *     This Strategy is selected in runtime by the generic type of the class
 */
@Repository
@RequiredArgsConstructor
public class PositionRepository implements SimpleMemStorageRepository<Position, Integer> {

    private final MemStorageLocal memStorageLocal;

    public Position save(Position newPosition) {
        return memStorageLocal.saveItem(newPosition);
    }

    public Optional<Position> findById(Integer id) {
        return memStorageLocal.getItem(id, getGenericType());
    }
}
