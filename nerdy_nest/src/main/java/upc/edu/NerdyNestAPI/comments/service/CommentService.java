package upc.edu.NerdyNestAPI.comments.service;

import upc.edu.NerdyNestAPI.comments.model.Comment;

import java.util.List;

public interface CommentService  {

    Comment createComment(String client_id, String product_id, Comment comment);
    Comment updateComment(String client_id, String product_id, Comment comment);
    Comment getComment(String client_id, String product_id);
    List<Comment> getCommentsByProduct(String product_id);
    List<Comment> getCommentsByClient(String client_id);
    void deleteComment(String client_id, String product_id);

}
