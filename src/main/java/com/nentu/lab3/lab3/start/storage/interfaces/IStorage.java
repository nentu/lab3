package com.nentu.lab3.lab3.start.storage.interfaces;
import java.util.List;

public interface IStorage<T> {
    public void add(T newItem);

    public List<T> getList();

    public void clear();

    public List<T> getReversedList();
}
