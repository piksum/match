package com.stackroute.favouriteservice.HttpUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.Model.MatchResponse;
import com.stackroute.favouriteservice.Model.MatchDetailResponse;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpClient {

    public MatchResponse getCurrentMatchList() {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    "http://cricapi.com/api/matches");
            getRequest.addHeader("accept", "application/json");
            getRequest.addHeader("apikey", "72NKZK1h06eBefDO15CKQPDKHez1");

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope("proxy.cognizant.com", 6050),
            new UsernamePasswordCredentials("298898", "Dhanvika@143"));



            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            MatchResponse matchData = objectMapper.readValue(response.getEntity().getContent(), MatchResponse.class);

            return matchData;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public MatchDetailResponse getMatchDetails(String id)  {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    "http://cricapi.com/api/cricketScore?unique_id="+id);
            getRequest.addHeader("accept", "application/json");
            getRequest.addHeader("apikey", "72NKZK1h06eBefDO15CKQPDKHez1");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            MatchDetailResponse matchDetailData = objectMapper.readValue(response.getEntity().getContent(), MatchDetailResponse.class);

            return matchDetailData;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
