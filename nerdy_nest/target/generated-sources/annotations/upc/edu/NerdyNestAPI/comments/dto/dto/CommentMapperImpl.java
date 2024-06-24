package upc.edu.NerdyNestAPI.comments.dto.dto;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.comments.dto.CommentRequest;
import upc.edu.NerdyNestAPI.comments.dto.CommentResponse;
import upc.edu.NerdyNestAPI.comments.model.Comment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T17:45:11-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentRequestToComment(CommentRequest commentRequest) {
        if ( commentRequest == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setDescription( commentRequest.getDescription() );
        comment.setRating( commentRequest.getRating() );

        return comment;
    }

    @Override
    public CommentResponse commentToCommentResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId( comment.getId() );
        commentResponse.setDescription( comment.getDescription() );
        commentResponse.setRating( comment.getRating() );
        commentResponse.setClientId( comment.getClientId() );
        commentResponse.setProductId( comment.getProductId() );
        commentResponse.setDate( comment.getDate() );

        return commentResponse;
    }

    @Override
    public List<CommentResponse> commentsToCommentResponses(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponse> list = new ArrayList<CommentResponse>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentResponse( comment ) );
        }

        return list;
    }
}
