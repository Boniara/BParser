package com.bonia.BParser.parsers.impl.xml;

import com.bonia.BParser.main.MainClass;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.parsers.IParser;
import com.bonia.BParser.utils.sax.Handler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParser implements IParser<Company> {

    private static final Logger LOG = Logger.getLogger(MainClass.class);

    private SAXParserFactory saxParserFactory;
    private Handler handler;

    public SaxParser() {
        this.saxParserFactory = SAXParserFactory.newInstance();
        this.handler = new Handler();
    }

    @Override
    public Company parse(String fileName) {
        LOG.info("SAX PARSER IS STARTING.");

        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(new File(fileName), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("ParserConf | SAX | IO Exception.", e);
        }
        LOG.info("SAX PARSER IS FINISHING.");
        LOG.debug("Company object " + handler.getCompany().toString());
        return handler.getCompany();
    }

    public Handler getHandler() {
        return handler;
    }
}
