package cn.edu.hit.service.impl;

import cn.edu.hit.entity.Facility;
import cn.edu.hit.entity.PageBean;
import cn.edu.hit.mapper.FacilityMapper;
import cn.edu.hit.service.FacilityService;
import cn.edu.hit.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityMapper facilityMapper;
    @Override
    public void add(Facility facility) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        facility.setCreator(userId);
        facility.setState("可用");
        facilityMapper.add(facility);
    }

    @Override
    public void update(Facility facility) {
        facilityMapper.update(facility);
    }

    @Override
    public Facility findById(Integer id) {
        return facilityMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        facilityMapper.delete(id);
    }

    @Override
    public PageBean<Facility> list(Integer pageNum, Integer pageSize, Integer regionId, String state) {
        //创建PageBean对象
        PageBean<Facility> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        List<Facility> facilities = facilityMapper.list(regionId,state);
        //分页查询返回的是Page对象
        //Page是List的实现类
        //Page中提供了方法，可以获取PageHelper分页查询后 得到的总记录条和当前页数据
        Page<Facility> p = (Page<Facility>) facilities;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
