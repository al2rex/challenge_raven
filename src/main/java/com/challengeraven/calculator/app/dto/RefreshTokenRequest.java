package com.challengeraven.calculator.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RefreshTokenRequest {
	private String token;
}
