package com.yrgo.client;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleClient {

    public static void main(String[] args) {
        ApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        CustomerManagementService service = (CustomerManagementService) container.getBean("customerManagementService");
        var customers = service.getAllCustomers();

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

}
