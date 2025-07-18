package com.challengeraven.calculator.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
	private String username;
	private String email;
	private String password;
}
