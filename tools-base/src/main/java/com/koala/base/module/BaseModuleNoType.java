package com.koala.base.module;

public interface BaseModuleNoType extends BaseModule {

    @Override
    default String getType(){
        return null;
    }

    @Override
    default String getOptionsUrl(){
        return null;
    }
}
