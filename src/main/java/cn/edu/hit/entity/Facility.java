package cn.edu.hit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Facility {
    @NotNull(groups = Update.class)
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Integer regionId;
    @NotNull
    private Double unitPrice;
    private String state;
    @JsonIgnore
    private Integer creator;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public interface Add extends Default {}
    public interface Update extends Default {}

}

