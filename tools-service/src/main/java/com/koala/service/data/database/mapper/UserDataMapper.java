package com.koala.service.data.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.koala.data.database.models.userData.UserDataTable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/28 21:19
 * @description
 */
public interface UserDataMapper extends BaseMapper<UserDataTable> {
    @Update("update `user_data` set user_id = #{userId} where unique_id = #{uniqueId} limit 1")
    void updateUserId(@Param("userId") Long userId, @Param("uniqueId") Long uniqueId);

    int insert(UserDataTable entity);
}
