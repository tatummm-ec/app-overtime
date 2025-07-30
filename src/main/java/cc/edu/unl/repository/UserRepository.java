package cc.edu.unl.repository;

import cc.edu.unl.domain.User;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class UserRepository {

    @Inject
    CrudGenericService crudService;

    public UserRepository() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public User save(User user){
        if (user.getUsername() == null){
            return crudService.create(user);
        } else {
            return crudService.update(user);
        }
    }

    public User find(@NotNull Long userName) throws EntityNotFoundException {
        User user = crudService.find(User.class, userName);
        if (user == null){
            throw new EntityNotFoundException("User no encontrado con [" + userName + "]");
        }
        return user;
    }

    public User find(@NotNull String name) throws EntityNotFoundException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name.toLowerCase());
        User userFound = (User) crudService.findSingleResultOrNullWithNamedQuery("User.findLikeName", params);
        if (userFound == null){
            throw new EntityNotFoundException("User no encontrado con [" + name + "]");
        }
        return userFound;
    }
    public List<User> findWithLike(@NotNull String name) throws EntityNotFoundException{
        Map<String, Object> params = new HashMap<>();
        params.put("name", name.toLowerCase() + "%");
        return crudService.findWithNamedQuery("User.findLikeName", params);
    }
}
