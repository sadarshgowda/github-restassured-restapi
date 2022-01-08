package org.example.branches;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.Branch;
import org.example.util.ApiClient;
import org.example.util.Constants;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class ListBranches {
    @Test
    public void checkIfGivenBranchPresentInResponse() throws Exception {

        String baseUrl = Constants.BASE_URL + String.format(Constants.LIST_BRANCHES_ENDPOINT,
                Constants.properties.getProperty("USER_NAME"),Constants.properties.getProperty("REPOSITORY_1"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Response response = ApiClient.get(baseUrl);

        Branch[] branchResponse = mapper.readValue(response.getBody().asString(),Branch[].class);

        List<Branch> res = Arrays.asList(mapper.readValue(response.getBody().asString(),Branch[].class));

        System.out.println(res.get(0).getCommit().getUrl());

    }
}
