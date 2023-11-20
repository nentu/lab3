package com.nentu.lab3.lab3.start.storage.list;

import com.nentu.lab3.lab3.start.storage.interfaces.IStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage<T> implements IStorage<T> {
    private List<T> list = new ArrayList<>();

    public void add(T newItem) {
        list.add(newItem);
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public void clear() {
        list.clear();
    }


    public List<T> getReversedList() {
        var size = list.size();
        var tList = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            tList.add(list.get(size - i - 1));
        }
        return tList;
    }

}
