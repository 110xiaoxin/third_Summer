package cn.edu.hit.service;

import cn.edu.hit.entity.Region;

import java.util.List;

public interface RegionService {
    List<Region> list();

    void add(Region region);

    void update(Region region);

    Region findById(Integer id);

    void delete(Integer id);
}
