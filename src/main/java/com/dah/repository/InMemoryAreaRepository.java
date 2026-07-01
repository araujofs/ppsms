package com.dah.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryAreaRepository implements AreaRepository {
    private List<Area> areas = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Area> findAll() {
        return new ArrayList<>(areas);
    }

    @Override
    public Optional<Area> findById(Integer id) {
        return areas.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst();
    }

    @Override
    public List<Area> findByIds(List<Integer> ids) {
        return areas.stream()
                .filter(a -> ids.contains(a.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Area save(Area area) {
        if (area.getId() == null) {
            area.setId(nextId++);
            areas.add(area);
        } else {
            areas.removeIf(a -> Objects.equals(a.getId(), area.getId()));
            areas.add(area);
        }
        return area;
    }

    @Override
    public void deleteAll() {
        areas.clear();
        nextId = 1;
    }
}