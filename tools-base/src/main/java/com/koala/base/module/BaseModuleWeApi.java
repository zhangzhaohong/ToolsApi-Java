package com.koala.base.module;

public interface BaseModuleWeApi extends BaseModule {

    @Override
    default String getType(){
        return "weapi";
    }

    @Override
    default String getOptionsUrl() {
        return null;
    }
}
