package com.training.twitter.domain.like;

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
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Twitter twitter;

	@Column(nullable = false)
	@UpdateTimestamp	
	private LocalDateTime time;

	@OneToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
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
}