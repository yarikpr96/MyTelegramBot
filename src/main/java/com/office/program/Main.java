package com.office.program;

import java.util.List;

import com.office.model.Question;
import org.hibernate.Session;

public class Main {
    static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void main(String[] args) {

        // add tasks.......


        session.close();
    }

    public static void create(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
    }

    public static Question findById(long id) {
        session.beginTransaction();
        Question question = (Question) session.get(Question.class, id);
        session.getTransaction().commit();
        return question;
    }

    public static List<Question> findAll() {
        session.beginTransaction();
        List<Question> questions = (List<Question>) session.createQuery("from Question ").list();
        session.getTransaction().commit();
        return questions;
    }


    public static Question findByName(String name) {
        Question question = (Question) session
                .createSQLQuery(
                        "Select * from Question as u where u.question = :question")
                .addEntity(Question.class).setParameter("question", name)
                .uniqueResult();
        return question;
    }

}
