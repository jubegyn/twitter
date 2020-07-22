package com.training.twitter.domain.comments;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.training.twitter.domain.twitter.Twitter;
import com.training.twitter.domain.user.User;

@Entity
@Table(name = "comments")
public class Comments {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String text;
	
	@UpdateTimestamp
    private LocalDateTime time;

	@OneToOne
	private User user;

	@OneToOne
	private Twitter twitter;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}
	
}
