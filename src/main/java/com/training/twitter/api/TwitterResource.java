package com.training.twitter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.twitter.application.TwitterService;
import com.training.twitter.domain.twitter.Twitter;

@RestController
@RequestMapping(value = { "twitter", "schedules" })
public class TwitterResource {
	
	@Autowired
	private TwitterService service;

	@GetMapping
	public Iterable<Twitter> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Twitter obter(@PathVariable Long id) {
		return service.obter(id);
	}

	@PostMapping
	public Twitter inserir(@RequestBody Twitter twitter) {
		return service.inserir(twitter);
	}

	@PutMapping
	public Twitter alterar(@RequestBody Twitter twitter) {
		return service.alterar(twitter);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id, @RequestHeader Long idUsuario) {
		service.deletar(id, idUsuario);
	}
	
}
