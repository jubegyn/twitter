package com.training.twitter.application.impl;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.training.twitter.application.TwitterService;
import com.training.twitter.application.UserService;
import com.training.twitter.domain.comments.CommentsRepository;
import com.training.twitter.domain.like.Like;
import com.training.twitter.domain.like.LikeRepository;
import com.training.twitter.domain.twitter.Twitter;
import com.training.twitter.domain.twitter.TwitterRepository;
import com.training.twitter.domain.user.User;
import com.training.twitter.infra.Logger;
import com.training.twitter.infra.exception.BusinessException;
import com.training.twitter.infra.exception.NotFoundException;

@Service
public class TwitterServiceImpl implements Logger, TwitterService {

	private final TwitterRepository twitterRepostory;
	private final LikeRepository likeRepostory;
	private final UserService userService;
	private final CommentsRepository commentsRepostory;
	
	@Autowired
	public TwitterServiceImpl(TwitterRepository twitterRepostory, UserService userService, 
			LikeRepository likeRepostory, CommentsRepository commentsRepostory) {
		this.twitterRepostory = twitterRepostory;
		this.userService = userService;
		this.likeRepostory = likeRepostory;
		this.commentsRepostory = commentsRepostory;
	}
	
	@Override
	public Twitter obter(@NonNull Long id) {
		log("obter: " + id);
		Twitter twitter = twitterRepostory.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Twitter não encontrado com ID %s", id)));
		
		twitter.setComments(commentsRepostory.countByTwitterId(id));
		twitter.setLikes(likeRepostory.countByTwitterId(id));
		
		return twitter;
	}

	@Override
	public Twitter inserir(@NonNull Twitter twitter) {
		log("Inserir: " + twitter);
		validate(twitter);
		return twitterRepostory.save(twitter);
	}

	@Override
	public Twitter alterar(@NonNull Twitter twitter) {
		log("Alterar: " + twitter);
		validate(twitter);
		Validate.notNull(twitter.getId(), "Dados do Twitter incompleto");		
		return twitterRepostory.save(twitter);
	}

	@Override
	@Transactional
	public void deletar(@NonNull Long idTwitter, @NonNull Long idUser) {
		final Twitter twitter = twitterRepostory.findById(idTwitter)
				.orElseThrow(() -> new BusinessException("Twitter não encontrado"));
		
		final User owner = userService.obter(idUser);
		
		if (owner.getId().equals(twitter.getUser().getId())) {
			log("Deletar: " + idTwitter);
			commentsRepostory.deleteByTwitterId(twitter.getId());
			likeRepostory.deleteByTwitterId(twitter.getId());
			twitterRepostory.delete(twitter);
		}else {
			throw new BusinessException("Somente o proprietário pode deletar a postagem");
		}
	}

	@Override
	public Iterable<Twitter> listar() {
		log("listar todos" );
		return twitterRepostory.findAll();
	}	

	@Override
	public Iterable<Like> findByTwitterId(@NonNull Long id) {
		log("obterLikesByTwitter: " + id);
		return likeRepostory.findByTwitterId(id);
	}
	
	private static void validate(Twitter obj) {
		Validate.notNull(obj, "Dados do Twitter não informado");
		Validate.notNull(obj.getMessage(), "Messagem do Twitter não informado");
		Validate.notNull(obj.getUser(), "User do Twitter não informado");
		Validate.notNull(obj.getCountry(), "Country não informado");
	}
}
