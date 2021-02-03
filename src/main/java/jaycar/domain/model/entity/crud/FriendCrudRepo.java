package jaycar.domain.model.entity.crud;

import jaycar.domain.model.entity.Friend;
import jaycar.security.entities.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendCrudRepo extends CrudRepository<Friend, Long> {

    public List<Friend> findByUser(User user);

    public List<Friend> findByUserAndFriend(User user, User friend);
}