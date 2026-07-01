package com.dah.repository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends ResettableRepository {

    List<Area> findAll();

    Optional<Area> findById(Integer id);

    List<Area> findByIds(List<Integer> ids);

    Area save(Area area);
    
    @Override
    void deleteAll();
}
