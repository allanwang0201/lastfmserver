package jaycar.domain.model.entity.crud;

import jaycar.domain.model.entity.Artist;
import jaycar.domain.model.entity.ArtistRecommendation;
import jaycar.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecommendationCrudRepo extends CrudRepository<ArtistRecommendation, Long> {

    List<ArtistRecommendation> findByUser(UserPublicInfo user);

    List<ArtistRecommendation> findByRecommenderAndArtist(UserPublicInfo recommender, Artist artist);

    List<ArtistRecommendation> findByUserAndRecommenderAndArtist(UserPublicInfo user, UserPublicInfo recommender, Artist artist);
}
