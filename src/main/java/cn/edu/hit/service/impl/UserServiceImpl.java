package cn.edu.hit.service.impl;

import cn.edu.hit.entity.Result;
import cn.edu.hit.entity.User;
import cn.edu.hit.mapper.UserMapper;
import cn.edu.hit.service.UserService;
import cn.edu.hit.utils.JwtUtil;
import cn.edu.hit.utils.Md5Util;
import cn.edu.hit.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static cn.edu.hit.utils.Md5Util.getMD5String;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Result<String> register(String username, String password) {//根据用户名查询用户是否已存在,
        if(findByUsername(username)==null){
            //未被占用则注册
            String md5String = getMD5String(password);

            userMapper.add(username,md5String);
            return Result.success();
        }else {
            return Result.error("用户名已被占用");
        }
    }
    @Override
    public Result<String> login(String username, String password) {
        User loginUser = findByUsername(username);
        if (loginUser != null && checkPassword(password, loginUser.getPassword())) {
            //生成token令牌
            String token = generateToken(loginUser);
            return Result.success(token);
        } else {
            return Result.error("用户名或密码错误。");
        }
    }
    private boolean checkPassword(String rawPassword, String encryptedPassword) {
        return Md5Util.getMD5String(rawPassword).equals(encryptedPassword);
    }

    private String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        return JwtUtil.genToken(claims);
    }


    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        //获取用户id
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public Result<String> updatePassword(Map<String,String> params) {
        // 1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要参数");
        }
        //2.校验原密码
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = findByUsername(username);
        if (!checkPassword(oldPwd, user.getPassword())) {
            return Result.error("原密码错误");
        }
        //3.校验新密码
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次输入的新密码不一致");
        }
        //4.更新密码
        userMapper.updatePassword(Md5Util.getMD5String(newPwd), user.getId());
        return Result.success();
    }


}
