package jaycar.security.entities.user.crud.api;

import jaycar.security.entities.user.entity.User;

import java.util.Optional;

public interface UserCrudService {

    public User save(User user);

    public Optional<User> find(Long id);

    public Optional<User> findByUsername(String username);

}
