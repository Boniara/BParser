package com.bonia.BParser.parsers.impl.xml;

import com.bonia.BParser.models.Company;
import com.bonia.BParser.parsers.IParser;
import org.apache.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxBParser implements IParser<Company> {

    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private Company company;

    private static final Logger LOG = Logger.getLogger(JaxBParser.class);

    public JaxBParser() {
    }

    @Override
    public Company parse(String fileName) {
        LOG.info("JAXB PARSER IS STARTING.");

        try {
            context = JAXBContext.newInstance(Company.class);
            unmarshaller = context.createUnmarshaller();
            company = (Company)unmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException e) {
            LOG.error("JAXBException while context up.", e);
        }
        LOG.info("JAXB PARSER IS FINISHING.");
        LOG.debug(company.toString());
        return company;
    }

    /**
     * Methode use to init simple Company class structure for JaxbParser.
     * @deprecated, because don`company use in application logic.
     * @see Company
     */
    @Deprecated
    public static void defaultMarshalling(Company company) {

        try {
            JAXBContext jc = JAXBContext.newInstance(Company.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(company, System.out);
            marshaller.marshal(company, new File("new.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
