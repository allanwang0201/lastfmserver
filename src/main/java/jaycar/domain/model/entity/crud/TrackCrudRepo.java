package jaycar.domain.model.entity.crud;

import jaycar.domain.model.entity.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackCrudRepo extends CrudRepository<Track, Long> {
}
