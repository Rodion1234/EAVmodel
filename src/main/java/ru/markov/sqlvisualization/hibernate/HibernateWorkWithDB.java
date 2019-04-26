/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.markov.sqlvisualization.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.markov.sqlvisualization.table.Attribute;
import ru.markov.sqlvisualization.table.Entity;
import ru.markov.sqlvisualization.table.Value;

/**
 *
 * @author rodion
 */
public class HibernateWorkWithDB {

    SessionFactory sessionFactory;

    public HibernateWorkWithDB(HibernateUtil nhu) {
        this.sessionFactory = nhu.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public List<Attribute> getListAttribute() {
        return getSession().createCriteria(Attribute.class).list();
    }

    public List<Entity> getListEntity() {
        return getSession().createCriteria(Entity.class).list();
    }
    
    public List<Value> getListValue() {
        return getSession().createCriteria(Value.class).list();
    }

}
