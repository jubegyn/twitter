package com.training.twitter.application.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.training.twitter.application.CommentsService;
import com.training.twitter.application.UserService;
import com.training.twitter.domain.comments.Comments;
import com.training.twitter.domain.comments.CommentsRepository;
import com.training.twitter.domain.user.User;
import com.training.twitter.infra.Logger;
import com.training.twitter.infra.exception.BusinessException;
import com.training.twitter.infra.exception.NotFoundException;

@Service
public class CommentsServiceImpl implements Logger, CommentsService{

	private final CommentsRepository commentsRepostory;
	private final UserService userService;
	
	@Autowired
	public CommentsServiceImpl(CommentsRepository commentsRepostory, UserService userService) {
		this.commentsRepostory = commentsRepostory;
		this.userService = userService;
	}	
	
	@Override
	public Comments obter(@NonNull Long id) {
		log("obter: " + id);
		return commentsRepostory.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Like não encontrado com ID %s", id)));
	}

	@Override
	public Comments inserir(@NonNull Comments comments) {
		log("Inserir: " + comments);
		validate(comments);
		return commentsRepostory.save(comments);
	}

	@Override
	public Comments alterar(@NonNull Comments comments) {
		log("Alterar: " + comments);
		validate(comments);
		Validate.notNull(comments.getId(), "Dados do like incompleto");
		return commentsRepostory.save(comments);
	}

	@Override
	public void deletar(@NonNull Long idComments, @NonNull Long idUser) {
        final Comments comments = commentsRepostory.findById(idComments)
                .orElseThrow(() -> new BusinessException("Comentário não encontrado"));
        final User owner = userService.obter(idUser);

        if (owner.getId().equals(comments.getUser().getId())) {
        	commentsRepostory.delete(comments);
        } else {
            throw new BusinessException("Somente o proprietário pode deletar o comentário");
        }
	}

	@Override
	public Iterable<Comments> listar() {
		log("listar todos" );
		return commentsRepostory.findAll();
	}
	
	@Override
	public Iterable<Comments> findByTwitter(@NonNull Long idTwitter) {
		log("listar todos Comments de um Twitter" );
		return commentsRepostory.findByTwitterId(idTwitter);
	}
	
	private static void validate(Comments obj) {
		Validate.notNull(obj, "Dados do Comments não informado");
		Validate.notNull(obj.getText(), "Text do Comments não informado");
		Validate.notNull(obj.getUser(), "User do Comments não informado");
		Validate.notNull(obj.getTwitter(), "Twitter não informado");
	}
}
