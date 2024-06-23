package upc.edu.NerdyNestAPI.comments.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import upc.edu.NerdyNestAPI.comments.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    //@Query("{ 'client_id' : ?0, 'product_id' : ?1 }")
    Comment findByClientIdAndProductId(String client_id, String product_id);
    //@Query("{ 'client_id' : ?0, 'product_id' : ?1 }")
    Boolean existsByClientIdAndProductId(String client_id, String product_id);
    //@Query("{ 'product_id' : ?0 }")
    Boolean existsByProductId(String product_id);
    //@Query("{ 'product_id' : ?0 }")
    List<Comment> findAllByProductId(String product_id);
    //@Query("{ 'client_id' : ?0 }")
    Boolean existsByClientId(String client_id);
    //@Query("{ 'client_id' : ?0 }")
    List<Comment> findAllByClientId(String client_id);

    //@Query("{ 'client_id' : ?0, 'product_id' : ?1 }")
    void deleteByClientIdAndProductId(String client_id, String product_id);
    Integer countByProductId(String product_id);
}
