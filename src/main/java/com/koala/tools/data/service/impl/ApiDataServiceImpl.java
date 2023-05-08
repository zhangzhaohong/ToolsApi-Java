package com.koala.tools.data.service.impl;

import com.koala.tools.data.dataModel.apiData.ApiDataTable;
import com.koala.tools.data.mapper.ApiDataMapper;
import com.koala.tools.data.service.ApiDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/8 10:12
 * @description
 */
@Service("ApiDataService")
public class ApiDataServiceImpl implements ApiDataService {
    @Resource
    private ApiDataMapper apiDataMapper;

    @Override
    public Long insertData(ApiDataTable entity) {
        apiDataMapper.insert(entity);
        return entity.getUniqueId();
    }
}
