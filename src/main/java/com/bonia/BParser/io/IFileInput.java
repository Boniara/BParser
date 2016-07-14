package com.bonia.BParser.io;

import java.net.URI;
import java.net.URISyntaxException;

public interface IFileInput {

    default URI toURI(String filename) {
        URI path = null;
        try {
            path = this.getClass().getResource(filename).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}
