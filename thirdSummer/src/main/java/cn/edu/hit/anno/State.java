package cn.edu.hit.anno;

import cn.edu.hit.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented//元注解
@Constraint(
        validatedBy = {StateValidation.class}   //提供校验规则的类
)
@Target(ElementType.FIELD)//元注解  注解可以在哪里使用
@Retention(RetentionPolicy.RUNTIME)//元注解  注解何时生效

public @interface State {
    String message() default "{state的值只能是'已发布'或'草稿'}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
