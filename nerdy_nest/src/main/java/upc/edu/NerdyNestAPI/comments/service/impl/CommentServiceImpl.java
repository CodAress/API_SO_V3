package upc.edu.NerdyNestAPI.comments.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.NerdyNestAPI.comments.model.Comment;
import upc.edu.NerdyNestAPI.comments.repository.CommentRepository;
import upc.edu.NerdyNestAPI.comments.service.CommentService;
import upc.edu.NerdyNestAPI.inventario.model.Product;
import upc.edu.NerdyNestAPI.inventario.repository.ProductRepository;
import upc.edu.NerdyNestAPI.user.repository.ClientRepository;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public Comment createComment(String client_id, String product_id, Comment comment) {
        existClientById(client_id);
        existProductById(product_id);
        validateComment(comment);
        existCommentByUserIdAndProductId(client_id, product_id);
        comment.setClientId(client_id);
        comment.setProductId(product_id);
        comment.setDate(LocalDateTime.now());
        updateRatingToCreateComment(product_id, comment.getRating().doubleValue());
        return commentRepository.save(comment);
    }
    @Transactional
    @Override
    public Comment updateComment(String client_id, String product_id, Comment comment) {
        existClientById(client_id);
        existProductById(product_id);
        validateComment(comment);
        existCommentByUserIdAndProductId(client_id, product_id);
        Comment commentToUpdate = commentRepository.findByClientIdAndProductId(client_id, product_id);
        commentToUpdate.setDescription(comment.getDescription());
        commentToUpdate.setRating(comment.getRating());
        updateRatingToCreateComment(product_id, comment.getRating().doubleValue());
        return commentRepository.save(commentToUpdate);
    }

    @Transactional
    @Override
    public Comment getComment(String client_id, String product_id) {
        existClientById(client_id);
        existProductById(product_id);
        notExistCommentByUserIdAndProductId(client_id, product_id);
        return commentRepository.findByClientIdAndProductId(client_id, product_id);
    }

    @Transactional
    @Override
    public List<Comment> getCommentsByProduct(String product_id) {
        existProductById(product_id);
        notExistCommentsByProductId(product_id);
        return commentRepository.findAllByProductId(product_id);
    }

    @Transactional
    @Override
    public List<Comment> getCommentsByClient(String client_id) {
        existClientById(client_id);
        notExistCommentsByClientId(client_id);
        return commentRepository.findAllByClientId(client_id);
    }

    @Transactional
    @Override
    public void deleteComment(String client_id, String product_id) {
        existClientById(client_id);
        existProductById(product_id);
        notExistCommentByUserIdAndProductId(client_id, product_id);
        Comment commentToDelete = commentRepository.findByClientIdAndProductId(client_id, product_id);
        updateRatingToDeleteComment(product_id, commentToDelete.getRating().doubleValue());
        commentRepository.deleteByClientIdAndProductId(client_id, product_id);
    }
    private void existClientById(String client_id) {
        if (!clientRepository.existsById(client_id)) {
            throw new ResourceNotFoundException(String.format("Client with id %s not found", client_id));
        }
    }
    private void existProductById(String product_id) {
        if (!productRepository.existsById(product_id)) {
            throw new ResourceNotFoundException(String.format("Product with id %s not found", product_id));
        }
    }

    private void existCommentByUserIdAndProductId(String client_id, String product_id) {
        if (commentRepository.existsByClientIdAndProductId(client_id, product_id)) {
            throw new ResourceNotFoundException(String.format("Comment with user_id %s and product_id %s already exists", client_id, product_id));
        }
    }
    private void notExistCommentByUserIdAndProductId(String client_id, String product_id) {
        if (!commentRepository.existsByClientIdAndProductId(client_id, product_id)) {
            throw new ResourceNotFoundException(String.format("Comment with user_id %s and product_id %s not found", client_id, product_id));
        }
    }
    private void notExistCommentsByProductId(String product_id) {
        if (!commentRepository.existsByProductId(product_id)) {
            throw new ResourceNotFoundException(String.format("Comments with product_id %s not found", product_id));
        }
    }
    private void notExistCommentsByClientId(String client_id) {
        if (!commentRepository.existsByClientId(client_id)) {
            throw new ResourceNotFoundException(String.format("Comments with client_id %s not found", client_id));
        }
    }
    private void validateComment(Comment comment) {
        if (comment.getDescription() == null || comment.getDescription().isEmpty()) {
            throw new ResourceNotFoundException("Description is required");
        }
        if (comment.getRating() == null) {
            throw new ResourceNotFoundException("Rating is required");
        }
        if(comment.getRating() < 1 || comment.getRating() > 5){
            throw new ResourceNotFoundException("Rating must be between 1 and 5");
        }
    }

    private void updateRatingToCreateComment(String product_id, Double newPunctuation){
        Product product = productRepository.findById(product_id).get();
        int totalComments = commentRepository.countByProductId(product_id);
        double rating = product.getRating();
        double newRating = (rating * totalComments + newPunctuation) / (totalComments + 1);
        product.setRating(newRating);
        productRepository.save(product);
    }

    private void updateRatingToDeleteComment(String product_id, Double newPunctuation){
        Product product = productRepository.findById(product_id).get();
        int totalComments = commentRepository.countByProductId(product_id);
        if(totalComments == 1){
            product.setRating(0.0);
            productRepository.save(product);
            return;
        }
        double rating = product.getRating();
        double newRating = (rating * totalComments - newPunctuation) / (totalComments - 1);
        product.setRating(newRating);
        productRepository.save(product);
    }
}
