package cn.edu.hit.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Region {
    @NotNull(groups = Update.class)
    private Integer id;
    @NotEmpty
    private String regionName;
    @JsonIgnore
    private Integer creator;//创建人ID
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //如果某个校验项没有指定分组，默认属于Default分组
    //分组之间可以继承，A extends B,则A中有B所有的校验项
    public interface Add extends Default {}
    public interface Update extends Default {}
}
