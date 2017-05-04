package com.office.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String answer;

	@OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
	private List<Question> questions;

	public Answer() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer{" +
				"answer='" + answer + '\'' +
				'}';
	}
}
