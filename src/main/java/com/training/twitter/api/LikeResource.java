package com.training.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.twitter.application.LikeService;
import com.training.twitter.domain.like.Like;

@RestController
@RequestMapping(value = { "like", "schedules" })
public class LikeResource {
	
	@Autowired
	private LikeService service;

	@GetMapping
	public Iterable<Like> listar() {
		return service.listar();
	}

	@PatchMapping
	public Boolean like(@RequestBody Like like, @RequestHeader Long idUsuario) {
		return service.like(like, idUsuario);
	}
	
}
