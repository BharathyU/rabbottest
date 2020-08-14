package com.ibm.hack.rabbot.rabbottest.controller;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.hack.rabbot.rabbottest.model.RabbotRequest;
import com.ibm.hack.rabbot.rabbottest.service.InterviewDetailsService;
import com.ibm.hack.rabbot.rabbottest.service.StorageGateWay;
import com.ibm.watson.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.personality_insights.v3.model.Profile;
import com.ibm.watson.personality_insights.v3.model.ProfileOptions;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backend")
public class InitialController {

	@Autowired
	private StorageGateWay storageGateWay;

	@Autowired
	private InterviewDetailsService interviewDetailsService;

	@GetMapping(path = "/ping")
	public String pingTest() {
		return "OK";
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> verifyFile(@RequestBody RabbotRequest rabbotRequest) {
		// System.out.println("param"+par +"text"+text);
//    String result=    storageGateWay.verifyFileInStorage(text);
		// System.out.println("result"+result);
		// Connect to Ibm storage and verify file is available

		String response = "Unkown operation";

		HashMap<String, String> responseMap = new HashMap<>();

		switch (rabbotRequest.getOperation()) {
		case "verifyInterviewCode":
			response = interviewDetailsService.checkInterviewCode(rabbotRequest.getId());
			break;
		case "verifyFile":
			response = "File is verified";
			break;
		case "verifyPhoto":
			response = "Photo is verfied successfully";
			break;
		case "saveInterviewAnswer":
			response = interviewDetailsService.saveAnswer(rabbotRequest.getId(), rabbotRequest.getSubject(),
					rabbotRequest.getAnswer(),rabbotRequest.getData());
			break;		
		case "consolidatedScore":
			response = interviewDetailsService.getScore(rabbotRequest.getId(),rabbotRequest.getSubject());
			break;
		case "checkPersonality":
			Profile profile = personalityTest(rabbotRequest.getData());
			response = "He has personality as " +profile.getPersonality().get(0).getName() + 
					" and "+profile.getPersonality().get(0).getChildren().get(0).getName();
		}
		responseMap.put("result", response);
		return responseMap;

	}

	public Profile personalityTest( String text) {

		IamAuthenticator authenticator = new IamAuthenticator("O4bix_w7_6v2sBgFm5MSNgkbrKLzQVpK3A1NSq2-spR4");
		PersonalityInsights personalityInsights = new PersonalityInsights("2017-10-13", authenticator);
		personalityInsights.setServiceUrl(
				"https://api.eu-gb.personality-insights.watson.cloud.ibm.com/instances/6f887956-f3a3-48f2-ac89-6f6b38f28065");

		try {

			ProfileOptions profileOptions = new ProfileOptions.Builder().text(text).consumptionPreferences(true)
					.rawScores(true).build();

			Profile profile = personalityInsights.profile(profileOptions).execute().getResult();

			return profile;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
