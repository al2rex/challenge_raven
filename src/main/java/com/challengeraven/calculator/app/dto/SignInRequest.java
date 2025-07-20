package com.challengeraven.calculator.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInRequest {
	private String username;
	private String password;
}
