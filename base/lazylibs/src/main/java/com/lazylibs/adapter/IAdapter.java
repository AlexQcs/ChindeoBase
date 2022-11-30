package com.lazylibs.adapter;

import androidx.annotation.NonNull;

import java.util.List;

public interface IAdapter<T> {

    void add(T t);

    void add(int position, T t);

    void remove(T t);

    void remove(int position);

    void addAll(List<T> data);

    void addAll(int position, List<T> data);

    void setData(@NonNull List<T> data);

}
