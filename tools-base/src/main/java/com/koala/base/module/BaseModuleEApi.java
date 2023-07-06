package com.koala.base.module;

public interface BaseModuleEApi extends BaseModule {

    @Override
    default String getType(){
        return "eapi";
    }
}
