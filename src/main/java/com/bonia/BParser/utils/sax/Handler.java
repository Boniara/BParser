package com.bonia.BParser.utils.sax;

import com.bonia.BParser.models.Address;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.models.Department;
import com.bonia.BParser.models.Employee;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    private static final String DEPARTMENT = "department";
    private static final String EMPLOYEE = "employee";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String AGE = "age";
    private static final String POSITION = "position";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String COMPANY = "company";
    private static final String DEPARTMENT_ADDRESS = "department_address";
    private static final String ADDRESS = "address";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String HOUSE = "house";

    private List<Employee> employeeList;
    private Employee employee;
    private List<Department> departmentList;
    private Department department;
    private List<Company> companyList;
    private Company company;
    private boolean bDepartmentAddress;
    private String element;
    IHandler handler = new CompanyHandler();

    public Handler() {
        this.employeeList = new ArrayList<>();
        this.departmentList = new ArrayList<>();
        this.companyList = new ArrayList<>();
        this.employee = new Employee();
        this.department = new Department();
        this.company = new Company();
        this.bDepartmentAddress = true;
        this.element = "";
    }

    /**
     * @author rutkovba
     * @see Enum
     * @deprecated don`t use in programm logic
     */
    @Deprecated
    public enum Tag {

        DEPARTMENT("department"), EMPLOYEE("employee"),
        FIRSTNAME("firstName"), LASTNAME("lastName"), AGE("age"), POSITION("position");

        private String name = "";

        Tag(String name) {
            this.name = name;
        }

        public String getValue() {
            return name;
        }
    };

    @Override
    public void startDocument() throws SAXException {
        LOG.info("Reading document....");
    }

    @Override
    public void endDocument() throws SAXException {
        LOG.info("Finish of reading....");
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
        handler.initStartElement(element, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        handler.initEndElement(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String parameter = new String(ch, start, length);
        handler.initCharacters(parameter);
    }

    /**
     * Interface use to init some data structures for SAX Handler
     * <p>
     * @author rutkovba
     * @see Handler
     */
    private interface IHandler {
        /**
         *
         * @param element
         * @param attributes
         */
        public void initStartElement(String element, Attributes attributes);
        public void initEndElement(String qName);
        public void initCharacters(String parameter);
    }

    /**
     * @author rutkovba
     * Class is designed only for {@link Company} class
     * @see Company
     * {@inheritDoc}
     */
    private class CompanyHandler implements IHandler {

        @Override
        public void initStartElement(String element, Attributes attributes) {
            switch(element) {
                case COMPANY: company.setCompanyName(attributes.getValue(NAME));
                    break;
                case DEPARTMENT: department.setDepartmentName(attributes.getValue(NAME));
                    break;
                case EMPLOYEE: employee.setId_employee(Integer.parseInt(attributes.getValue(ID)));
                    break;
                case DEPARTMENT_ADDRESS: bDepartmentAddress = true;
                    break;
                case ADDRESS: bDepartmentAddress = false;
                    break;
                default: break;
            }
        }

        @Override
        public void initEndElement(String qName) {
            element = "";
            switch(qName) {
                case COMPANY:
                    company.setDepartmentList(departmentList);
                    companyList.add(company);
                    departmentList = new ArrayList<>();
                    break;
                case DEPARTMENT:
                    department.setEmployeeList(employeeList);
                    departmentList.add(department);
                    employeeList = new ArrayList<>();
                    department = new Department();
                    break;
                case EMPLOYEE:
                    employeeList.add(employee);
                    employee = new Employee();
                    break;
                case DEPARTMENT_ADDRESS: break;
                case ADDRESS: break;
                default: break;
            }
        }

        @Override
        public void initCharacters(String parameter) {
            Address localAddress;
            switch(element) {
                case FIRST_NAME:
                    employee.setFirstName(parameter);
                    break;
                case LAST_NAME:
                    employee.setLastName(parameter);
                    break;
                case AGE:
                    employee.setAge(Integer.parseInt(parameter));
                    break;
                case POSITION:
                    employee.setPosition(parameter);
                    break;
                case COUNTRY:
                    localAddress = (bDepartmentAddress == true) ? department.getAddress() : employee.getAddress();
                    localAddress.setCountryName(parameter);
                    break;
                case CITY:
                    localAddress = (bDepartmentAddress == true) ? department.getAddress() : employee.getAddress();
                    localAddress.setCityName(parameter);
                    break;
                case STREET:
                    localAddress = (bDepartmentAddress == true) ? department.getAddress() : employee.getAddress();
                    localAddress.setStreetName(parameter);
                    break;
                case HOUSE:
                    localAddress = (bDepartmentAddress == true) ? department.getAddress() : employee.getAddress();
                    localAddress.setHouseNumber(Integer.parseInt(parameter));
                    break;
            }
        }
    }

    /**
     * Use to get {@link Handler#company}
     * @return {@link Handler#company}
     * @see Company
     */
    public Company getCompany() {
        return company;
    }
}
