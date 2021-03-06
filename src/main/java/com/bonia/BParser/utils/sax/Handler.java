package com.bonia.BParser.utils.sax;

import com.bonia.BParser.models.*;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;
import static com.bonia.BParser.utils.sax.constants.HandlerConstantSource.*;

public class Handler extends DefaultHandler {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    private List<Employee> employeeList;
    private Employee employee;
    private List<Department> departmentList;
    private Department department;
    private List<Company> companyList;
    private Company company;
    private Position position;
    private List<Position> positionList;
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
        this.position = new Position();
        this.positionList = new ArrayList<>();
        this.bDepartmentAddress = true;
        this.element = "";
    }

    @Override
    public void startDocument() throws SAXException {
        LOG.debug("Reading document....");
    }

    @Override
    public void endDocument() throws SAXException {
        LOG.debug("Finish of reading....");
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
     * Interface IHandler use to init some data structures for SAX Handler.
     * <p>
     * @author rutkovba
     * @see Handler, {@link DefaultHandler}
     */
    private interface IHandler {
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
                case COMPANY:
                    company.setCompanyName(attributes.getValue(NAME));
                    company.setId(Integer.parseInt(attributes.getValue(ID)));
                    break;
                case DEPARTMENT:
                    department.setDepartmentName(attributes.getValue(NAME));
                    department.setId(Integer.parseInt(attributes.getValue(ID)));
                    break;
                case EMPLOYEE: employee.setId(Integer.parseInt(attributes.getValue(ID)));
                    break;
                case DEPARTMENT_ADDRESS:
                    department.getAddress().setId(Integer.parseInt(attributes.getValue(ID)));
                    bDepartmentAddress = true;
                    break;
                case ADDRESS:
                    employee.getAddress().setId(Integer.parseInt(attributes.getValue(ID)));
                    bDepartmentAddress = false;
                    break;
                case POSITION: position.setId(Integer.parseInt(attributes.getValue(ID)));
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
                    employee.setPositionList(positionList);
                    employeeList.add(employee);
                    employee = new Employee();
                    position = new Position();
                    positionList = new ArrayList<>();
                    break;
                case DEPARTMENT_ADDRESS: break;
                case ADDRESS: break;
                case POSITION:
                    position.setIdDepartment(department.getId());
                    position.setIdEmployee(employee.getId());
                    positionList.add(position);
                    break;
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
                case POSITION_NAME:
                    position.setPositionName(parameter);
                    break;
            }
        }
    }

    /**
     * Use to get {@link Handler#company}
     * @return {@link Handler#company}
     * <p>S
     * @see Company
     */
    public Company getCompany() {
        return company;
    }
}
