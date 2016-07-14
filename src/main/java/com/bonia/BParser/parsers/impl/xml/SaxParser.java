package com.bonia.BParser.parsers.impl.xml;

import com.bonia.BParser.io.impl.FileInput;
import com.bonia.BParser.main.MainClass;
import com.bonia.BParser.parsers.IParser;
import com.bonia.BParser.utils.sax.Handler;
import com.bonia.BParser.utils.sax.HandlerFactory;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParser<T> implements IParser<T> {

    private static final Logger LOG = Logger.getLogger(MainClass.class);

    private SAXParserFactory saxParserFactory;
    private Handler handler;
    private HandlerFactory<T> factory;

    public SaxParser() {
        this.saxParserFactory = SAXParserFactory.newInstance();
        this.handler = new Handler();
        this.factory = new HandlerFactory<>();
    }

    @Override
    public T parse(String fileName) {
        FileInput input = new FileInput(fileName);
        LOG.info("SAX PARSER IS STARTING.");

        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(new File(input.toURI()), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("ParserConf | SAX | IO Exception.", e);
        }
        LOG.info("SAX PARSER IS FINISHING.");
        return factory.getInstance(handler);
    }
}
