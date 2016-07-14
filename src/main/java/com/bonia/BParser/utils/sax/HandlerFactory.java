package com.bonia.BParser.utils.sax;

public class HandlerFactory<T> {

    private T t;

    public HandlerFactory() {
    }

    public T getInstance(Handler handler) {
        return (T)handler.getCompany();
    }
}
