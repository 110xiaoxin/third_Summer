package cn.edu.hit.validation;

import cn.edu.hit.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


//给哪个注解提供校验规则     校验的数据类型是什么样的
public class StateValidation implements ConstraintValidator<State,String> {

    /**
     *
     * @param value  将来要校验的数据
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value==null)
            return false;
        if (value.equals("草稿")||value.equals("已发布"))
            return true;
        return false;
    }
}
