package com.bonia.BParser.parsers;

import com.bonia.BParser.models.Model;

public interface IParser<T extends Model> {

    public T parse(String fileName);
}
