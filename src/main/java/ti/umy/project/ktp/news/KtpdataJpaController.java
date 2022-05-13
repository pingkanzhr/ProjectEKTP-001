/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ti.umy.project.ktp.news;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ti.umy.project.ktp.news.exceptions.NonexistentEntityException;
import ti.umy.project.ktp.news.exceptions.PreexistingEntityException;

/**
 *
 * @author Asus
 */
public class KtpdataJpaController implements Serializable {

    public KtpdataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ti.umy_project.ktp.news_jar_0.0.1-SNAPSHOTPU");

    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public KtpdataJpaController() {
    }

    public void create(Ktpdata ktpdata) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ktpdata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKtpdata(ktpdata.getId()) != null) {
                throw new PreexistingEntityException("Ktpdata " + ktpdata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ktpdata ktpdata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ktpdata = em.merge(ktpdata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ktpdata.getId();
                if (findKtpdata(id) == null) {
                    throw new NonexistentEntityException("The ktpdata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ktpdata ktpdata;
            try {
                ktpdata = em.getReference(Ktpdata.class, id);
                ktpdata.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ktpdata with id " + id + " no longer exists.", enfe);
            }
            em.remove(ktpdata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ktpdata> findKtpdataEntities() {
        return findKtpdataEntities(true, -1, -1);
    }

    public List<Ktpdata> findKtpdataEntities(int maxResults, int firstResult) {
        return findKtpdataEntities(false, maxResults, firstResult);
    }

    private List<Ktpdata> findKtpdataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ktpdata.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ktpdata findKtpdata(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ktpdata.class, id);
        } finally {
            em.close();
        }
    }

    public int getKtpdataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ktpdata> rt = cq.from(Ktpdata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
