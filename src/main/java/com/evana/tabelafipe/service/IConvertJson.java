package com.evana.tabelafipe.service;

public interface IConvertJson {
    <T> T convertJson(String json, Class<T> tClass);
}
