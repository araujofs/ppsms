package com.dah.repository;

import com.dah.domain.Area;
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
