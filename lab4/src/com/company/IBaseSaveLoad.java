package com.company;

public interface IBaseSaveLoad {
    void save(Object obj, String path) throws Exception;
    <T extends Object> T load(String path) throws Exception;
}
