package cn.edu.hit.mapper;

import cn.edu.hit.entity.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Select("select * from region")
    List<Region> list();

    @Insert("insert into region(region_name,creator,create_time,update_time)" +
            " values(#{regionName},#{creator},now(),now())")
    void add(Region region);

    @Update("update region set region_name=#{regionName},update_time=now() where id=#{id}")
    void update(Region region);

    @Select("select * from region where id = #{id}")
    Region findById(Integer id);

    @Delete("delete from region where id = #{id}")
    void delete(Integer id);
}
