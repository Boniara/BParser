package com.bonia.BParser.parsers.impl.json;

import com.bonia.BParser.models.Company;
import com.bonia.BParser.parsers.IParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser implements IParser<Company> {

    private static final Logger LOG = Logger.getLogger(JacksonParser.class);

    private Company company;
    private ObjectMapper mapper;

    public JacksonParser() {
        company = new Company();
        mapper = new ObjectMapper();
    }

    @Override
    public Company parse(String fileName) {
        LOG.info("JACKSON PARSER IS STARTING.");

        try {
            company = mapper.readValue(new File(fileName), Company.class);
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
        LOG.info("JACKSON PARSER IS FINISHING.");
        LOG.debug(company.toString());
        return company;
    }


    /**
     * Methode use to init simple Company class structure for JacksonParser.
     * @deprecated, because don`t use in application logic.
     * @see Company
     */
    @Deprecated
    public static void defaultMarshalling(Company defaultCompany) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("company.json"), defaultCompany);
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
}
