package com.company;

import java.io.*;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class CustomSaveLoad implements IBaseSaveLoad {
    public void save(Object obj, String path) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(obj);
        FileUtils.write(new File(path), jsonString);
    }

    public  <T extends Object> T load(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = FileUtils.readFileToString(new File(path));

        //немного чёрной магии =)
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return mapper.readValue(jsonString, tClass);
    }
}
