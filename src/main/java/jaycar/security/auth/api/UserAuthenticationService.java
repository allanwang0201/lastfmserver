package jaycar.security.auth.api;

import jaycar.security.entities.user.entity.User;

import java.util.Optional;

public interface UserAuthenticationService {

    public Optional<String> login(String username, String password);

    public Optional<User> findByToken(String token);

    void logout(User user);
}