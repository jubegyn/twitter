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

import com.training.twitter.application.CommentsService;
import com.training.twitter.domain.comments.Comments;

@RestController
@RequestMapping(value = { "comments", "schedules" })
public class CommentsResource {
	
	@Autowired
	private CommentsService service;

	@GetMapping
	public Iterable<Comments> listar() {
		return service.listar();
	}

	@GetMapping("/twitters/{id}")
    public Iterable<Comments> listByTwitter(@PathVariable Long id) {
        return service.findByTwitter(id);
    }

	@PostMapping
	public Comments inserir(@RequestBody Comments comments) {
		return service.inserir(comments);
	}	

	@PutMapping
	public Comments alterar(@RequestBody Comments comments) {
		return service.alterar(comments);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id, @RequestHeader("user_id") Long userId) {
		service.deletar(id, userId);
	}
}
