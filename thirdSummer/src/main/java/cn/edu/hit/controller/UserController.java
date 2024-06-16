package cn.edu.hit.controller;

import cn.edu.hit.entity.Result;
import cn.edu.hit.entity.User;
import cn.edu.hit.service.UserService;
import cn.edu.hit.utils.JwtUtil;
import cn.edu.hit.utils.Md5Util;
import cn.edu.hit.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        return userService.register(username,password);
    }

    //登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        return userService.login(username,password);
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        //获取用户名
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        System.out.println(user);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePassword(@RequestBody Map<String,String> params){
        return userService.updatePassword(params);
    }
}
