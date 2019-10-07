/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Classe.Etudiant;
import dao.IDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author mst
 */
public class EtudiantService implements IDao<Etudiant> {

    @Override
    public boolean create(Etudiant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Etudiant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(Etudiant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }

    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            etudiants = session.createQuery("from Etudiant").list();
            tx.commit();
            session.close();
            return etudiants;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return etudiants;
        }

    }

    @Override
    public Etudiant findById(int id) {
        Etudiant m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Etudiant) session.get(Etudiant.class, id);
            tx.commit();
            session.close();
            return m;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return m;
        }
    }
    public List<Etudiant> findByLastName(String nom) {
        List<Etudiant> etudiants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            etudiants = session.createQuery("SELECT E FROM Etudiant E WHERE E.nom LIKE concat('%',?,'%')").setParameter(0, nom).list();
            tx.commit();
            session.close();
            return etudiants;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return etudiants;
        }

    }
    public List<Etudiant> findByFirstName(String prenom) {
        List<Etudiant> etudiants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            etudiants = session.createQuery("SELECT E FROM Etudiant E WHERE E.prenom LIKE concat('%',?,'%')").setParameter(0,prenom).list();
            tx.commit();
            session.close();
            return etudiants;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return etudiants;
        }

    }
    public List<Etudiant> findByName(String nom,String prenom) {
        List<Etudiant> etudiants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            etudiants = session.createQuery("SELECT E FROM Etudiant E WHERE E.nom LIKE ? AND E.prenom LIKE concat('%',?,'%')").setParameter(0, nom).setParameter(1, prenom).list();
            tx.commit();
            session.close();
            return etudiants;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return etudiants;
        }

    }
     public int getEtudiantsCountByNiveau(String niveau) {
        int count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            count = ((Long) session.createQuery("SELECT COUNT(E.niveauEtude) FROM Etudiant E WHERE E.niveauEtude =?").setParameter(0, niveau).uniqueResult()).intValue();
            tx.commit();
            session.close();
            return count;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return count;
        }
    }
    public List<String> getEtudiantsNiveau() {
        List<String>  libelles = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            libelles = session.createQuery("SELECT DISTINCT E.niveauEtude FROM Etudiant E").list();
            tx.commit();
            session.close();
            return libelles;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return libelles;
        }
    }
}
