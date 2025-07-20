package com.challengeraven.calculator.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMailBoxValidationDTO {
	private String email;
	private String did_you_mean;
	private String user;
	private String domain;
	private Boolean format_valid;
	private Boolean mx_found;
	private Boolean smtp_check;
    private Object catch_all;
    private Boolean role;
    private Boolean disposable;
    private Boolean free;
    private Double score;
}
