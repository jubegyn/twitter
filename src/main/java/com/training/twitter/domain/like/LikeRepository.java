package com.training.twitter.domain.like;

import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
	Like findbyUserAndTwitter(Long idTwitter, Long idUsuario);
}
