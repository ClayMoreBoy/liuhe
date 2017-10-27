package com.lin.liuhe.service;


public interface Function<E,T> {
    public T callback(E e);
}