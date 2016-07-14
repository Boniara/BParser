package com.bonia.BParser.io.impl;

import com.bonia.BParser.io.IFileInput;

import java.net.URI;

public class FileInput implements IFileInput {

    private String fileName;

    public FileInput(String fileName) {
        this.fileName = fileName;
    }

    /**This method convert String to URI
     *
     * @return URI
     * @see URI
     */
    public URI toURI() {
        return toURI(fileName);
    }
}
