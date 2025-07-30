package cc.edu.unl.business;

import cc.edu.unl.domain.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;

@Stateless
public class UsuarioService implements Serializable {
    @PersistenceContext(unitName = "tuappPU")
    private EntityManager em;


    public boolean login(String username, String password) {
        return getUserByUsernameAndPassword(username,password)!=null;
    }

    private User getUserByUsernameAndPassword(String username, String password){
        Query query = em.createQuery("select  u from User u where u.username=:username and u.password=:password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User) query.getSingleResult();

    }


    public void crearUsuario(User user) {
        em.persist(user);
    }
}
