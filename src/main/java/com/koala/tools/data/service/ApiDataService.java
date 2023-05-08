package com.koala.tools.data.service;

import com.koala.tools.data.dataModel.apiData.ApiDataTable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/8 10:09
 * @description
 */
public interface ApiDataService {
    Long insertData(ApiDataTable entity);
}
