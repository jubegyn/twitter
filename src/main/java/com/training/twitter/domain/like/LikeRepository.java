package com.training.twitter.domain.like;

import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
	Like findByUserIdAndTwitterId(Long idUsuario, Long idTwitter);
	Iterable<Like> findByTwitterId(Long idTwitter);
	void deleteByTwitterId(Long id);
}
