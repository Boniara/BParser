package com.bonia.BParser.main;

import com.bonia.BParser.io.IFileOutput;
import com.bonia.BParser.io.impl.OutputToConsole;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.parsers.IParser;
import com.bonia.BParser.parsers.impl.json.JacksonParser;
import com.bonia.BParser.parsers.impl.xml.JaxBParser;
import com.bonia.BParser.parsers.impl.xml.SaxParser;

public class MainClass {

    public static final String XML_FILE_NAME = "/com.bonia.BParser.companies/company.xml";
    public static final String JSON_FILE_NAME = "/com.bonia.BParser.companies/company.json";
    public static final String LOG_FILE_NAME = "log4j.xml";

    public static void main(String[] args) {

        //PropertyConfigurator.configure(LOG_FILE_NAME);

        IFileOutput<Company> output = new OutputToConsole<>();

        IParser<Company> parser = new SaxParser<>();
        Company company = parser.parse(XML_FILE_NAME);
        output.write(company);

        IParser<Company> parser1 = new JaxBParser<>();
        Company company1 = parser1.parse(XML_FILE_NAME);
        output.write(company1);

        IParser<Company> parser2 = new JacksonParser<>();
        Company company2 = parser2.parse(JSON_FILE_NAME);
        output.write(company2);
    }
}
