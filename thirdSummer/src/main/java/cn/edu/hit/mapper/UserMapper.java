package cn.edu.hit.mapper;

import cn.edu.hit.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void add(String username, String password);

    @Update("UPDATE user SET nickname=#{nickname},email=#{email},update_time=now() WHERE id=#{id}")
    void update(User user);

    @Update("UPDATE user SET user_pic=#{avatarUrl},update_time=now() WHERE id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("UPDATE user SET password=#{newPwd},update_time=now() WHERE id=#{id}")
    void updatePassword(String newPwd,Integer id);
}
