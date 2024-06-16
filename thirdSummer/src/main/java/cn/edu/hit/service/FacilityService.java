package cn.edu.hit.service;

import cn.edu.hit.entity.Facility;
import cn.edu.hit.entity.PageBean;

public interface FacilityService {
    void add(Facility facility);

    void update(Facility facility);

    Facility findById(Integer id);

    void delete(Integer id);

    PageBean<Facility> list(Integer pageNum, Integer pageSize, Integer regionId, String state);
}
