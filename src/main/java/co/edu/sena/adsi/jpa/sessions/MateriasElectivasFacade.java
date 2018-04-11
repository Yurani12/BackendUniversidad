/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.MateriasElectivas;
import co.edu.sena.adsi.jpa.entities.MateriasElectivas_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author yurani
 */
@Stateless
public class MateriasElectivasFacade extends AbstractFacade<MateriasElectivas> {

    @PersistenceContext(unitName = "co.edu.sena.adsi.jpa.entities_Universidad_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriasElectivasFacade() {
        super(MateriasElectivas.class);
    }
    
    /**
     * Busca materia por nombre
     *
     * @param nombre
     * @return MateriasElectivas
     */
    public MateriasElectivas findUsuarioByNombre (String Nombre) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MateriasElectivas> cq = cb.createQuery(MateriasElectivas.class);
        Root<MateriasElectivas> usuario = cq.from(MateriasElectivas.class);
        cq.where(cb.equal(usuario.get(MateriasElectivas_.nombre), Nombre));
        TypedQuery<MateriasElectivas> q = getEntityManager().createQuery(cq);
        try {
            return (MateriasElectivas) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
