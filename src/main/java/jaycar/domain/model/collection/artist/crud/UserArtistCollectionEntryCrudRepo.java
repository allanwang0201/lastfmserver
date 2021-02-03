package jaycar.domain.model.collection.artist.crud;

import jaycar.domain.model.collection.artist.UserArtistCollection;
import jaycar.domain.model.collection.artist.UserArtistCollectionEntry;
import jaycar.domain.model.entity.Artist;
import jaycar.security.entities.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserArtistCollectionEntryCrudRepo extends CrudRepository<UserArtistCollectionEntry, Long> {

    public List<UserArtistCollectionEntry> findByUserArtistCollection(UserArtistCollection collection);

    public List<UserArtistCollectionEntry> findByUserArtistCollectionAndArtist(UserArtistCollection collection, Artist artist);
}
