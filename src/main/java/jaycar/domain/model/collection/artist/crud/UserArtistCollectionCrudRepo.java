package jaycar.domain.model.collection.artist.crud;

import jaycar.domain.model.collection.artist.UserArtistCollection;
import jaycar.security.entities.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserArtistCollectionCrudRepo extends CrudRepository<UserArtistCollection, Long> {

    public List<UserArtistCollection> findByUserAndCollectionName(User user, String collectionName);

    public List<UserArtistCollection> findByUser(User user);
}