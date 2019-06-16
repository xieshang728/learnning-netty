package org.test8;

import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface{

    @Override
    public Person getPersonByUsername(String userName) throws DataException, TException {
        System.out.println("param: "+userName);
        Person person = new Person();
        person.setUsername("jack");
        person.setAge(21);
        person.setMarried(false);
        System.out.println(person.toString());
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson: "+person);
    }
}
