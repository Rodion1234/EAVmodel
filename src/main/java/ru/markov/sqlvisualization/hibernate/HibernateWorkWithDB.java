/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.markov.sqlvisualization.hibernate;

import java.util.List;
import org.hibernate.Query;
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

    public void setEntity(Entity entity) {
        Session session = getSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void setAttribute(Attribute attribute) {
        Session session = getSession();
        session.beginTransaction();
        session.save(attribute);
        session.getTransaction().commit();
        session.close();
    }

    public void setValue(Value value) {
        Session session = getSession();
        session.beginTransaction();
        session.save(value);
        session.getTransaction().commit();
        session.close();
    }

//    public List getDBOneTable() {
//        String str = "FROM Entity  en,\n"
//                + "Attribute att ,\n"
//                + "Value val\n"
//                + "where att.id_entity=en.id_entity\n"
//                + "and val.id_attribute=att.id_attribute";
//        Query query = getSession().createQuery(str);
//        return query.list();
//    }

}
