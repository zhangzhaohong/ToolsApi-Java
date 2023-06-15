package com.koala.service.data.handler;

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
        setData(metaObject, "userToken", null);
        setData(metaObject, "created", System.currentTimeMillis());
        setData(metaObject, "updated", 0L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新数据时，直接更新字段
        setFieldValByName("updated", System.currentTimeMillis(), metaObject);
    }

    private void setData(MetaObject metaObject, String fieldName, Object obj) {
        Object field = getFieldValByName(fieldName, metaObject);
        if (Objects.isNull(field)) {
            //字段为空，可以进行填充
            setFieldValByName(fieldName, obj, metaObject);
        }
    }
}
