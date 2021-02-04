package jaycar.domain.services.api;

import jaycar.domain.model.entity.*;
import jaycar.domain.remote.lastfm.entity.LfmArtistSearchResult;

public interface RemoteApiClient {

    public String getArtistSearch(String query, String limit) throws Exception;

    public AlbumSearchResult getAlbumSearch(String query) throws Exception;

    public TrackSearchResult getTrackSearch(String query) throws Exception;

    public String getArtistInfo(String query) throws Exception;

    public String getArtistTopAlbums(String query) throws Exception;

    public String getAlbumInfo(String query, String other) throws Exception;

    public Track getTrackInfo(String query) throws Exception;

    public SimilarArtists getSimilarArtists(String query) throws Exception;

    public ArtistAlbums getArtistAlbums(String query) throws Exception;
}
