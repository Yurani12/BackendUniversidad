/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.Roles;
import co.edu.sena.adsi.jpa.entities.Roles_;
import co.edu.sena.adsi.jpa.entities.Usuarios;
import co.edu.sena.adsi.jpa.entities.Usuarios_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;

/**
 *
 * @author yurani
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "co.edu.sena.adsi.jpa.entities_Universidad_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    /**
     * Busca usuario por numDocumento
     *
     * @param numDocumento
     * @return Usuario
     */
    public Usuarios findUsuarioByDocumento(String Documento) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.where(cb.equal(usuario.get(Usuarios_.documento), Documento));
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    /**
     * Busca usuario por email
     *
     * @param email
     * @return Usuario
     */
    public Usuarios findUsuarioByEmail(String email) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.where(cb.equal(usuario.get(Usuarios_.email), email));
        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    /**
     * Busca usuario por rol
     *
     * @param rol
     * @return Usuarios
      */
    public List<Usuarios> findAllUsuariosByRol(String roles) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Roles> rootRoles = cq.from(Roles.class);
        cq.where(cb.equal(rootRoles.get(Roles_.id), roles));
        ListJoin<Roles, Usuarios> joinRol = rootRoles.join(Roles_.usuariosList);
        CriteriaQuery<Usuarios> cqq = cq.select(joinRol);

        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return q.getResultList();

        } catch (NoResultException ex) {
            return null;
        }
    }
    
        public List<Usuarios> findAllUsuariosByProfesor(String roles) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Roles> rootRoles = cq.from(Roles.class);
        cq.where(cb.equal(rootRoles.get(Roles_.id), roles));
        ListJoin<Roles, Usuarios> joinRol = rootRoles.join(Roles_.usuariosList);
        CriteriaQuery<Usuarios> cqq = cq.select(joinRol);

        TypedQuery<Usuarios> q = getEntityManager().createQuery(cq);
        try {
            return q.getResultList();

        } catch (NoResultException ex) {
            return null;
        }
    }
}
