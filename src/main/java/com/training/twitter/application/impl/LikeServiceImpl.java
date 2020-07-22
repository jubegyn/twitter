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
	public Boolean like(@NonNull Like like, @NonNull Long idUsuario) {
		final Like likeCadastrado = likeRepostory.findByUserIdAndTwitterId(like.getUser().getId(), like.getTwitter().getId());

		if (likeCadastrado == null) {
			log("Like: " + like);
			validate(like);
			likeRepostory.save(like);
			return true;
		} else {
			log("UnLike: " + like);
			validate(like);
			Validate.isTrue(!like.getUser().getId().equals(idUsuario), "Somente o próprio usuário pode fazer o Des-like");
			likeRepostory.delete(likeCadastrado);
			return false;
		}
	}

	@Override
	public Iterable<Like> listar() {
		log("listar todos");
		return likeRepostory.findAll();
	}

	private static void validate(Like obj) {
		Validate.notNull(obj, "Dados do Like não informado");
		Validate.notNull(obj.getUser(), "User do Like não informado");
		Validate.notNull(obj.getTwitter(), "Twitter não informado");
	}
}
