package com.koala.tools.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/29 11:54
 * @description
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object userToken = getFieldValByName("userToken", metaObject);
        if (Objects.isNull(userToken)) {
            //字段为空，可以进行填充
            setFieldValByName("userToken", null, metaObject);
        }
        Object created = getFieldValByName("created", metaObject);
        if (Objects.isNull(created)) {
            //字段为空，可以进行填充
            setFieldValByName("created", System.currentTimeMillis(), metaObject);
        }
        Object updated = getFieldValByName("updated", metaObject);
        if (Objects.isNull(updated)) {
            //字段为空，可以进行填充
            setFieldValByName("updated", 0L, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新数据时，直接更新字段
        setFieldValByName("updated", System.currentTimeMillis(), metaObject);
    }
}
