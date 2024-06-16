package cn.edu.hit.mapper;

import cn.edu.hit.entity.Facility;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FacilityMapper {
    @Insert("insert into facility(name,region_id,unit_price,state,creator,create_time,update_time)" +
            " values(#{name},#{regionId},#{unitPrice},#{state},#{creator},now(),now())")
    void add(Facility facility);

    @Update("update facility set name=#{name},region_id=#{regionId},unit_price=#{unitPrice},state=#{state},update_time=now() where id=#{id}")
    void update(Facility facility);

    @Select("select * from facility where id = #{id}")
    Facility findById(Integer id);

    @Delete("delete from facility where id = #{id}")
    void delete(Integer id);

    List<Facility> list(Integer regionId, String state);
}
