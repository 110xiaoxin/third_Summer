package cn.edu.hit.service.impl;

import cn.edu.hit.entity.Region;
import cn.edu.hit.mapper.RegionMapper;
import cn.edu.hit.service.RegionService;
import cn.edu.hit.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;
    @Override
    public List<Region> list() {
        return regionMapper.list();
    }

    @Override
    public void add(Region region) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        System.out.println(userId);
        System.out.println(userId);
        System.out.println(userId);


        region.setCreator(userId);
        regionMapper.add(region);
    }

    @Override
    public void update(Region region) {
        regionMapper.update(region);
    }

    @Override
    public Region findById(Integer id) {
        return regionMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        regionMapper.delete(id);
    }
}
