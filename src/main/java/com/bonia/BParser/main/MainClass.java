package com.bonia.BParser.main;

import com.bonia.BParser.io.IFileOutput;
import com.bonia.BParser.io.impl.OutputToConsole;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.parsers.IParser;
import com.bonia.BParser.parsers.impl.json.JacksonParser;
import com.bonia.BParser.parsers.impl.xml.JaxBParser;
import com.bonia.BParser.parsers.impl.xml.SaxParser;
import org.apache.log4j.PropertyConfigurator;

public class MainClass {

    public static final String XML_FILE_NAME = "src/main/resources/com.bonia.BParser/companies/company.xml";
    public static final String JSON_FILE_NAME = "src/main/resources/com.bonia.BParser/companies/company.json";
    public static final String LOG_FILE_NAME = "src/main/resources/log4j.xml";

    public static void main(String[] args) {

        PropertyConfigurator.configure(LOG_FILE_NAME);

        IFileOutput<Company> output = new OutputToConsole<>();

        IParser<Company> saxParser = new SaxParser<>();
        Company company = saxParser.parse(XML_FILE_NAME);
        output.write(company);

        IParser<Company> jaxBParser = new JaxBParser<>();
        Company company1 = jaxBParser.parse(XML_FILE_NAME);
        output.write(company1);

        IParser<Company> jacksonParser = new JacksonParser<>();
        Company company2 = jacksonParser.parse(JSON_FILE_NAME);
        output.write(company2);
    }
}
