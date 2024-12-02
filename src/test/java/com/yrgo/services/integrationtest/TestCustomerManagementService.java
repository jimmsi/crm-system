package com.yrgo.services.integrationtest;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class TestCustomerManagementService {

    @Autowired
    private CustomerManagementService customerService;

    @Test
    public void testCreateCustomers() {

        customerService.newCustomer(new Customer("199", "Ikea",
                "ikea@ikea.se", "0707817888", "A big note"));
        customerService.newCustomer(new Customer("200", "Spotify",
                "spotify@spotify.se", "040123456", "A small note"));

        int customersInDb = customerService.getAllCustomers().size();
        assertEquals(2, customersInDb, "There should be two customers in the database!");

    }

    @Test
    public void testFindCustomerById() {
        String id = "200";
        Customer testCustomer = new Customer("200", "Ikea",
                "ikea@ikea.se", "0707817888", "A big note");
        customerService.newCustomer(testCustomer);
        Customer foundCustomer;

        try {
            foundCustomer = customerService.findCustomerById(id);
            assertEquals(testCustomer, foundCustomer, "The customer returned is the wrong one!");
        } catch (CustomerNotFoundException e) {
            fail("No customer was found when one should have been!");
        }

    }

}
