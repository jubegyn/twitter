package com.training.twitter.application.impl;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.training.twitter.application.LikeService;
import com.training.twitter.domain.like.Like;
import com.training.twitter.domain.like.LikeRepository;
import com.training.twitter.domain.twitter.Twitter;
import com.training.twitter.domain.twitter.TwitterRepository;
import com.training.twitter.domain.user.User;
import com.training.twitter.domain.user.UserRepository;
import com.training.twitter.infra.Logger;

@Service
public class LikeServiceImpl implements Logger, LikeService {

	private final LikeRepository likeRepostory;
	private final TwitterRepository twitterRepostory;
	private final UserRepository userRepository;

	@Autowired
	public LikeServiceImpl(LikeRepository likeRepostory, TwitterRepository twitterRepostory,
			UserRepository userRepository) {
		this.likeRepostory = likeRepostory;
		this.twitterRepostory = twitterRepostory;
		this.userRepository = userRepository;
	}

	@Override
	public Boolean like(@NonNull Long idTwitter, @NonNull Long idUsuario) {
		final Like likeCadastrado = likeRepostory.findByUserIdAndTwitterId(idUsuario, idTwitter);

		if (likeCadastrado == null) {
			log("Like ");
			
			Optional<Twitter> twitter = twitterRepostory.findById(idTwitter);
			Optional<User> user = userRepository.findById(idUsuario);
			
			Like like = new Like();
			like.setTwitter(twitter.get());
			like.setUser(user.get());
			
			validate(like);
			likeRepostory.save(like);
			return true;
		} else {
			log("UnLike: " + likeCadastrado.getId());
			validate(likeCadastrado);
			Validate.isTrue(likeCadastrado.getUser().getId().equals(idUsuario), "Somente o próprio usuário pode fazer o Des-like");
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
