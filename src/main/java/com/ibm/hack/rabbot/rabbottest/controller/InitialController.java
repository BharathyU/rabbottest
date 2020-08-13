package com.ibm.hack.rabbot.rabbottest.controller;


import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.hack.rabbot.rabbottest.Service.StorageGateWay;
import com.ibm.watson.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.personality_insights.v3.model.Profile;
import com.ibm.watson.personality_insights.v3.model.ProfileOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/backend")
public class InitialController {

    @Autowired
    private StorageGateWay storageGateWay;

    @GetMapping(path = "/ping")
    public String pingTest(){
        return "OK";
    }

@PostMapping
    public String verifyFile(@RequestBody String text ,@RequestParam String par){

        System.out.println("param"+par +"text"+text);


//    String result=    storageGateWay.verifyFileInStorage(text);


   // System.out.println("result"+result);
        //Connect to Ibm storage and verify file is available
        return "Ok";

    }





    @PostMapping(path = "/personality")
    public Profile personalityTest(@RequestBody String text){


        IamAuthenticator authenticator = new IamAuthenticator("O4bix_w7_6v2sBgFm5MSNgkbrKLzQVpK3A1NSq2-spR4");
        PersonalityInsights personalityInsights = new PersonalityInsights("2017-10-13", authenticator);
        personalityInsights.setServiceUrl("https://api.eu-gb.personality-insights.watson.cloud.ibm.com/instances/6f887956-f3a3-48f2-ac89-6f6b38f28065");

        try {



            ProfileOptions profileOptions = new ProfileOptions.Builder()
                    .text(text)
                    .consumptionPreferences(true)
                    .rawScores(true)
                    .build();

            Profile profile =
                    personalityInsights.profile(profileOptions).execute().getResult();

            return profile;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
