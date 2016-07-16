package com.bonia.BParser.parsers;

import com.bonia.BParser.models.AbstractModel;

public interface IParser<T extends AbstractModel> {

    public T parse(String fileName);
}
