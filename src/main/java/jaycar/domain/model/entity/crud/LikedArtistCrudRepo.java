package jaycar.domain.model.entity.crud;

import jaycar.domain.model.entity.Artist;
import jaycar.domain.model.entity.LikedArtist;
import jaycar.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikedArtistCrudRepo extends CrudRepository<LikedArtist, Long> {

    List<LikedArtist> findByUser(UserPublicInfo user);

    List<LikedArtist> findByUserAndArtist(UserPublicInfo user, Artist artist);
}
