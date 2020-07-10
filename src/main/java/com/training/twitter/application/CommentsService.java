package com.training.twitter.application;

import org.springframework.lang.NonNull;

import com.training.twitter.domain.comments.Comments;

public interface CommentsService {
	Comments obter(@NonNull final Long id);
	Comments inserir(@NonNull final Comments comments);
	Comments alterar(@NonNull final Comments comments);
    void deletar(@NonNull final Long idComments, @NonNull Long idUser);
    Iterable<Comments> listar();
}
