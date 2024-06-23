package upc.edu.NerdyNestAPI.comments.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.comments.dto.CommentRequest;
import upc.edu.NerdyNestAPI.comments.dto.CommentResponse;
import upc.edu.NerdyNestAPI.comments.dto.dto.CommentMapper;
import upc.edu.NerdyNestAPI.comments.service.CommentService;

import java.util.List;

@Tag(name = "Gestión de Comentarios", description = "Controlador para operaciones relacionadas con comentarios")
@RestController
@RequestMapping("/api/v4")
@CrossOrigin(origins = "*")
public class CommentController{

    @Autowired
    private CommentService commentService;

    @Operation(summary = "Crea un nuevo comentario", description = "Crea un nuevo comentario y devuelve el comentario creado")
    @PostMapping(value = "/clients/{client_id}/products/{product_id}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable String client_id , @PathVariable String product_id, @RequestBody CommentRequest commentRequest){
        var commentCreated = commentService.createComment(client_id, product_id, CommentMapper.INSTANCE.commentRequestToComment(commentRequest));
        var commentResponse = CommentMapper.INSTANCE.commentToCommentResponse(commentCreated);
        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un comentario", description = "Actualiza un comentario y devuelve el comentario actualizado")
    @PutMapping(value = "/clients/{client_id}/products/{product_id}/comments")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable String client_id, @PathVariable String product_id, @RequestBody CommentRequest commentRequest){
        var commentUpdated = commentService.updateComment(client_id, product_id, CommentMapper.INSTANCE.commentRequestToComment(commentRequest));
        var commentResponse = CommentMapper.INSTANCE.commentToCommentResponse(commentUpdated);
        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un comentario", description = "Obtiene un comentario por su ID")
    @GetMapping(value = "/clients/{client_id}/products/{product_id}/comments")
    public ResponseEntity<CommentResponse> getComment(@PathVariable String client_id, @PathVariable String product_id){
        var comment = commentService.getComment(client_id, product_id);
        var commentResponse = CommentMapper.INSTANCE.commentToCommentResponse(comment);
        return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los comentarios", description = "Obtiene todos los comentarios")
    @GetMapping(value = "/clients/{client_id}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByClient(@PathVariable String client_id){
        var comments = commentService.getCommentsByClient(client_id);
        var commentsResponse = CommentMapper.INSTANCE.commentsToCommentResponses(comments);
        return new ResponseEntity<List<CommentResponse>>(commentsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los comentarios de un producto", description = "Obtiene todos los comentarios de un producto")
    @GetMapping(value = "/products/{product_id}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByProduct( @PathVariable String product_id){
        var comments = commentService.getCommentsByProduct(product_id);
        var commentsResponse = CommentMapper.INSTANCE.commentsToCommentResponses(comments);
        return new ResponseEntity<List<CommentResponse>>(commentsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un comentario", description = "Elimina un comentario por su ID")
    @DeleteMapping(value = "/clients/{client_id}/products/{product_id}/comments")
    public ResponseEntity<Boolean> deleteComment(@PathVariable String client_id, @PathVariable String product_id){
        commentService.deleteComment(client_id, product_id);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }

}
