package cn.edu.hit.service;

import cn.edu.hit.entity.Result;
import cn.edu.hit.entity.User;

import java.util.Map;

public interface UserService {
    //根据用户名查询用户
    User findByUsername(String username);
    //插入用户
    Result<String> register(String username, String password);
    Result<String> login(String username,String password);

    //更新用户基本信息
    void update(User user);

    void updateAvatar(String avatarUrl);

    Result<String> updatePassword(Map<String,String> params);


}
