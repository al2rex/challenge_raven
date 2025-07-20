package com.challengeraven.calculator.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDTO {
	private String username;
	private String email;
	private String password;
}
