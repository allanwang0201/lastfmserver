package jaycar.web;
import jaycar.domain.model.entity.*;
import jaycar.domain.remote.lastfm.entity.LfmArtistSearchResult;
import jaycar.domain.services.api.RemoteApiClient;
import jaycar.security.entities.user.entity.UserPublicInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.json.XML;
import org.json.JSONException;
@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class PublicRestController {

	@Autowired
	RemoteApiClient remoteApiClient;

	@Autowired
	@Qualifier("jsonMapper")
	ObjectMapper jsonMapper;

	@Autowired
    EntityManagerFactory entityManagerFactory;

    @RequestMapping
    public String all(@RequestParam Map<String,String> allRequestParams) {

        String method = allRequestParams.get("method");
        String artist = allRequestParams.get("artist");

        try {
            if(method.equals("artist.search")){
                String limit = allRequestParams.get("limit");
                return remoteApiClient.getArtistSearch(artist, limit);
            }
            else if(method.equals("artist.getinfo")){
                return remoteApiClient.getArtistInfo(artist);
            }
            else if(method.equals("artist.gettopalbums")){
                return remoteApiClient.getArtistTopAlbums(artist);
            }
            else if(method.equals("album.getinfo")){
                String album = allRequestParams.get("album");
                return remoteApiClient.getAlbumInfo(artist, album);

            }
            else if(method.equals("artist.search")){

            }

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }

        return null;
    }


	@RequestMapping(value = "/getAlbumSearch")
    @Cacheable("albumSearchCache")
    public String getAlbumSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

	    try {

            AlbumSearchResult searchResult = remoteApiClient.getAlbumSearch(query);
            String json = jsonMapper.writeValueAsString(searchResult);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getTrackSearch")
    @Cacheable("trackSearchCache")
    public String getTrackSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            TrackSearchResult searchResult = remoteApiClient.getTrackSearch(query);
            String json = jsonMapper.writeValueAsString(searchResult);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }


    @RequestMapping(value = "/getTrackInfo")
    @Cacheable("trackInfoCache")
    public String getTrackInfo(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            Track track = remoteApiClient.getTrackInfo(query);
            String json = jsonMapper.writeValueAsString(track);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getSimilarArtists")
    @Cacheable("similarArtistsCache")
    public String getSimilarArtists(@RequestParam(value = "query", required = true) String query) throws Exception {

	    try {

	        SimilarArtists similarArtists = remoteApiClient.getSimilarArtists(query);
	        String json = jsonMapper.writeValueAsString(similarArtists);
	        return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getArtistAlbums")
    @Cacheable("artistAlbumsCache")
    public String getArtistAlbums(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            ArtistAlbums artistAlbums = remoteApiClient.getArtistAlbums(query);
            String json = jsonMapper.writeValueAsString(artistAlbums);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @GetMapping(value = "/searchUsername")
    public String searchUsername(@RequestParam(value = "username", required = true) String username) throws Exception {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(UserPublicInfo.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(3)
                .onField("username")
                .matching(username)
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, UserPublicInfo.class);

        List<UserPublicInfo> resultList = jpaQuery.getResultList();

        String json = jsonMapper.writeValueAsString(resultList);

        return json;
    }

    @GetMapping(value="/getUserSearch")
    public String getUserSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

	    return searchUsername(query);
    }
}