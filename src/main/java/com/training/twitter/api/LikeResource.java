package com.training.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.twitter.application.LikeService;
import com.training.twitter.domain.like.Like;

@RestController
@RequestMapping(value = { "likes" })
public class LikeResource {
	
	@Autowired
	private LikeService service;

	@GetMapping
	public Iterable<Like> listar() {
		return service.listar();
	}

	@PatchMapping("/{idTwitter}")
	public Boolean like(@PathVariable Long idTwitter, @RequestHeader Long idUsuario) {
		return service.like(idTwitter, idUsuario);
	}
	
}
