package com.fantasy.practice.service.cache;

import java.io.Serializable;
import java.util.List;

public interface ICacheService {
    void put(int namespace, Serializable key, Serializable value, int expireTime) throws Exception;

    Object get(int namespace, Serializable key) throws Exception;

    public <T> List<T> get(int namespace, List<Serializable> keys, Class<T> entityClass) throws Exception;

    boolean del(int namespace, Serializable key);

    boolean del(int namespace, List<? extends Serializable> key);

    int incr(int namespace, Serializable key, int value, int defaultValue, int expireTime);

    int decr(int namespace, Serializable key, int value, int defaultValue, int expireTime);

    boolean remove(int namespace, Serializable key);
}
