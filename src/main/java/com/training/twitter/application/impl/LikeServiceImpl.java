package com.training.twitter.application.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.training.twitter.application.LikeService;
import com.training.twitter.domain.like.Like;
import com.training.twitter.domain.like.LikeRepository;
import com.training.twitter.infra.Logger;

@Service
public class LikeServiceImpl implements Logger, LikeService {

	private final LikeRepository likeRepostory;

	@Autowired
	public LikeServiceImpl(LikeRepository likeRepostory) {
		this.likeRepostory = likeRepostory;
	}

	@Override
	public Like like(@NonNull Like like) {
		final Like likeCadastrado = likeRepostory.findbyUserAndTwitter(like.getTwitter().getId(),
				like.getUser().getId());

		if (likeCadastrado == null) {
			log("Like: " + like);
			validate(like);
			return likeRepostory.save(like);
		} else {
			log("UnLike: " + like);
			validate(like);
			likeRepostory.delete(like);
			return null;
		}
	}

	@Override
	public Iterable<Like> listar() {
		log("listar todos");
		return likeRepostory.findAll();
	}

	private static void validate(Like obj) {
		Validate.notNull(obj, "Dados do Like não informado");
		Validate.notNull(obj.getTime(), "Data do Like não informado");
		Validate.notNull(obj.getUser(), "User do Like não informado");
		Validate.notNull(obj.getTwitter(), "Twitter não informado");
	}
}
