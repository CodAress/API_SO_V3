package upc.edu.NerdyNestAPI.comments.dto.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.comments.dto.CommentRequest;
import upc.edu.NerdyNestAPI.comments.dto.CommentResponse;
import upc.edu.NerdyNestAPI.comments.model.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment commentRequestToComment (CommentRequest commentRequest);
    CommentResponse commentToCommentResponse (Comment comment);
    List<CommentResponse> commentsToCommentResponses (List<Comment> comments);

}
