package com.whalespottingjava.services;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class PostCodeService {
    private final String URL_POST_CODE_API = "https://api.postcodes.io/postcodes";
    private final String RESULT_KEY_WORD = "result";
    private final String POST_CODE_KEY_WORD = "postcode";

    public double getLatitude(String postCode) {
        JSONObject result = handlePostCodeApiResponse(postCode);

        return result.getBigDecimal("latitude").doubleValue();
    }

    public double getLongitude(String postCode) {
        JSONObject result = handlePostCodeApiResponse(postCode);

        return result.getBigDecimal("longitude").doubleValue();
    }

    public String getPostCode(double longitude, double latitude) {
        String url = getUrlForPostCodeRequest(longitude, latitude);
        String response;
        String postcode;
        JSONObject result;

        response = requestHttpPostCodeApi(url);
        JSONObject json = new JSONObject(response);
        result = json.getJSONArray(RESULT_KEY_WORD).getJSONObject(0);
        postcode = result.getString(POST_CODE_KEY_WORD);

        return postcode;
    }

    private String requestHttpPostCodeApi(String url) {
        String responseBody;
        HttpRequest request;

        try {
           request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        } 

        return responseBody;
    }

    private JSONObject handlePostCodeApiResponse(String postCode){
        String url = getUrlForLonLatRequest(postCode);
        String response;
        JSONObject result;

        response = requestHttpPostCodeApi(url);
        JSONObject json = new JSONObject(response);
        result = json.getJSONObject(RESULT_KEY_WORD );

        return result;
    }

    private String getUrlForPostCodeRequest(double longitude, double latitude) {
        String longitudeInput = "lon=";
        String latitudeInput = "lat=";

        return URL_POST_CODE_API +  "?" + longitudeInput + longitude + "&" + latitudeInput + latitude;
    }
    
    private String getUrlForLonLatRequest(String postCode){
        postCode =  postCode.replaceAll("\\s", "");
        
        return URL_POST_CODE_API +  "/" + postCode;
    }
}
