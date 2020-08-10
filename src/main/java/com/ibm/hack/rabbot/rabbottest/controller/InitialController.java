package com.ibm.hack.rabbot.rabbottest.controller;


import com.google.gson.stream.JsonReader;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.util.GsonSingleton;
import com.ibm.watson.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.personality_insights.v3.model.Content;
import com.ibm.watson.personality_insights.v3.model.Profile;
import com.ibm.watson.personality_insights.v3.model.ProfileOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
public class InitialController {

    @GetMapping(path = "/ping")
    public String pingTest(){
        return "OK";
    }


    @GetMapping(path = "/personality")
    public Profile personalityTest(){


        IamAuthenticator authenticator = new IamAuthenticator("O4bix_w7_6v2sBgFm5MSNgkbrKLzQVpK3A1NSq2-spR4");
        PersonalityInsights personalityInsights = new PersonalityInsights("2017-10-13", authenticator);
        personalityInsights.setServiceUrl("https://api.eu-gb.personality-insights.watson.cloud.ibm.com/instances/6f887956-f3a3-48f2-ac89-6f6b38f28065");

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            JsonReader jsonReader =
                    new JsonReader(new FileReader(classLoader.getResource("profile.json").getFile()));
            Content content =
                    GsonSingleton.getGson().fromJson(jsonReader, Content.class);

            ProfileOptions profileOptions = new ProfileOptions.Builder()
                    .content(content)
                    .consumptionPreferences(true)
                    .rawScores(true)
                    .build();

            Profile profile =
                    personalityInsights.profile(profileOptions).execute().getResult();
            System.out.println(profile);

            return profile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

}
