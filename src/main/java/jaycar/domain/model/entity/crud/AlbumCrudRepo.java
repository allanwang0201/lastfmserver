package jaycar.domain.model.entity.crud;

import jaycar.domain.model.entity.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumCrudRepo extends CrudRepository<Album, Long> {
}
