package com.yrgo.dataaccess;

import com.yrgo.domain.Action;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActionDaoJpaImp implements ActionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Action newAction) {
        em.persist(newAction);
    }

    @Override
    public List<Action> getIncompleteActions(String userId) {
        String jpql = "SELECT a from Action a WHERE a.owningUser = :userId AND a.complete = :complete";
        return em.createQuery(jpql, Action.class)
                .setParameter("userId", userId)
                .setParameter("complete", false)
                .getResultList();
    }

    @Override
    public void update(Action actionToUpdate) throws RecordNotFoundException {
        Action existingAction = em.find(Action.class, actionToUpdate.getActionId());
        existingAction.setComplete(actionToUpdate.isComplete());
        existingAction.setDetails(actionToUpdate.getDetails());
        existingAction.setOwningUser(existingAction.getOwningUser());
        existingAction.setRequiredBy(existingAction.getRequiredBy());
    }

    @Override
    public void delete(Action oldAction) throws RecordNotFoundException {
        Action action = em.find(Action.class, oldAction.getActionId());
        em.remove(action);
    }
}
