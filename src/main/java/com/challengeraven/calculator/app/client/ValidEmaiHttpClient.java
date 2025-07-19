package com.challengeraven.calculator.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.challengeraven.calculator.app.dto.ResponseMailBoxValidation;

@Repository
@FeignClient(value = "emailValidator", url = "${validemail.api.base-url}")
public interface ValidEmaiHttpClient {

	@GetMapping("${validemail.api.path}")
	ResponseMailBoxValidation validationEmailByExternalService(@RequestParam("access_key") String accessKey,
			@RequestParam("email") String email, @RequestParam("smtp") int smtp, @RequestParam("format") int format);

}
