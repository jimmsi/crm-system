package com.yrgo.dataaccess;


import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class CustomerDaoJpaImp implements CustomerDao {

    @PersistenceContext
    private EntityManager em;
    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        return em.createQuery("SELECT customer from Customer as customer" +
                " where customer.customerId=:customerId", Customer.class).setParameter("customerId", customerId).getSingleResult();
    }

    @Override
    public List<Customer> getByName(String name) {
        return em.createQuery("SELECT customer from Customer as customer " +
                " where customer.companyName=:companyName", Customer.class).setParameter("companyName", name).getResultList();
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {
        Customer existingCustomer = em.find(Customer.class, customerToUpdate.getCustomerId());
        existingCustomer.setCompanyName(customerToUpdate.getCompanyName());
        existingCustomer.setEmail(customerToUpdate.getEmail());
        existingCustomer.setTelephone(existingCustomer.getTelephone());
        existingCustomer.setNotes(existingCustomer.getNotes());
        existingCustomer.setCalls(existingCustomer.getCalls());
    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, oldCustomer.getCustomerId());
        em.remove(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        String jpql = "SELECT customer from Customer as customer";
        return em.createQuery(jpql, Customer.class).getResultList();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        return em.createQuery(
                "SELECT customer FROM Customer customer LEFT JOIN FETCH customer.calls WHERE customer.customerId = :customerId",
                Customer.class
        ).setParameter("customerId", customerId).getSingleResult();
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        Customer existingCustomer = em.find(Customer.class, customerId);
        existingCustomer.addCall(newCall);
    }
}
