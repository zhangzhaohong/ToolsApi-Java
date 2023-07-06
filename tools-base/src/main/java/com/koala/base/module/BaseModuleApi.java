package com.koala.base.module;

public interface BaseModuleApi extends BaseModule {

    @Override
    default String getType(){
        return "api";
    }

    @Override
    default String getOptionsUrl() {
        return null;
    }
}
