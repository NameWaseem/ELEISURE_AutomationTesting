package com.test;

import com.google.gson.Gson;
import com.qmetry.qaf.automation.ws.rest.RestTestBase;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class createMerchant_APITest extends RestTestBase {

    public void createAccessToken(){
        JSONObject obj=new JSONObject();
        obj.put("Username","integrationtestmerchant@test.com");
        obj.put("Password","I7cYDpqV");
        getWebResource("","").post(obj);
    }

    @Test()
    public void createMerchant() {
        //request data
        JSONObject obj=new JSONObject();
        obj.put("firstName","Agoda");
        obj.put("lastName", "International");
        obj.put("email", "agoda@test.com");
        obj.put("merchantCode","MAC001");
        obj.put("magentoUsername","integrationtestmerchant@test.com");
        obj.put("magentoPassword","I7cYDpqV");
        obj.put("enabled","true");
        System.out.println(obj);

        //create post request
        getWebResource("","").post(obj);

    }

    public void refreshToken(){
        JSONObject obj=new JSONObject();
        obj.put("refresh_token","");
        getWebResource("","").post(obj);
    }

    public void CreatedMerchantToken(){
        JSONObject obj=new JSONObject();
        obj.put("mk","");
        obj.put("ms","");
        getWebResource("","").post(obj);
    }
}
