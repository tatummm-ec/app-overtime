package cc.edu.unl.business;

import cc.edu.unl.domain.User;
import cc.edu.unl.exception.CredentialInvalidException;
import cc.edu.unl.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
@Stateless
public class SecurityFacade {

    @Inject
    private UserRepository userRepository;

    public User create(User user) throws Exception {
        try {
            userRepository.find(user.getUsername());
        } catch (EntityNotFoundException e){
            User userPersisted = userRepository.save(user);
            return userPersisted;
        }
        throw new Exception("Ya existe un usuario con ese nombre");
    }

    public User find(Long id) throws EntityNotFoundException {
        return userRepository.find(id);
    }

    public User authenticate(String name, String password) throws CredentialInvalidException {
        try{
            User userFound = userRepository.find(name);
            if (password.equals(userFound.getPassword())){
                return userFound;
            }
            throw new CredentialInvalidException("Credenciales inválidas para el usuario: " + name);
        } catch (EntityNotFoundException e){
            throw new CredentialInvalidException("Credenciales inválidas para el usuario: " + name);
        }
    }

}
