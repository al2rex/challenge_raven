package com.challengeraven.calculator.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseSignUpDTO {
	private String username;
	private String email;
	private String createdAt;
}
