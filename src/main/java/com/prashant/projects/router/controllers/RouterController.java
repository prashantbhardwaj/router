package com.prashant.projects.router.controllers;

import org.jboss.security.vault.SecurityVaultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterController {

	/** The constant VAULT_AUTH_KEY **/
	private static final String VAULT_AUTH_KEY = "VAULT::ROUTER::REST_AUTH_KEY::1";

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = { "xmlEnqueue", "XMLEnqueue",
			"xmlenqueue" }, method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public ResponseEntity enqueueXML(@RequestBody String xmlMessage,
			@RequestParam(value = "key", required = true) final String authKey, @RequestHeader String host) {

		try {
			// xmlValidator.validate(xmlMessage);
			if ("local".equalsIgnoreCase(host)) {
				System.out.println("RouterController.enqueueXML() - Local");
			} else {
				String savedauthKey = SecurityVaultUtil.getValueAsString(VAULT_AUTH_KEY);
				if (StringUtils.isEmpty(authKey)) {
					System.out.println("RouterController.enqueueXML() - Empty Auth key");
				} else {
					System.out.println("RouterController.enqueueXML() - Auth Key present");
					if (authKey.equalsIgnoreCase(savedauthKey)) {
						System.out.println("RouterController.enqueueXML() - Matching Auth Key present");
					} else {
						System.out.println("RouterController.enqueueXML() - Auth Key Mis match");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("RouterController.enqueueXML() - Exception");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Received");
	}
}
