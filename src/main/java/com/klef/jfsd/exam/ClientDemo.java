package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Load configuration
        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();

        // Insert operation
        insertDepartment(factory);

        // Delete operation
        deleteDepartment(factory, 1); // Example: Delete department with ID 1

        factory.close();
    }

    private static void insertDepartment(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Department dept = new Department("Computer Science", "Building A", "Dr. Smith");
        session.save(dept);

        tx.commit();
        session.close();
        System.out.println("Department inserted successfully.");
    }

    private static void deleteDepartment(SessionFactory factory, int deptId) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("delete from Department where id = :deptId");
        query.setParameter("deptId", deptId);

        int result = query.executeUpdate();
        tx.commit();
        session.close();

        if (result > 0) {
            System.out.println("Department with ID " + deptId + " deleted successfully.");
        } else {
            System.out.println("No Department found with ID " + deptId);
        }
    }
}
