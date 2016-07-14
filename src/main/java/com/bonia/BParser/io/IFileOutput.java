package com.bonia.BParser.io;

import java.util.List;

public interface IFileOutput<T> {

    public void write(T t);
    public void writeList(List<T> tList);
}
