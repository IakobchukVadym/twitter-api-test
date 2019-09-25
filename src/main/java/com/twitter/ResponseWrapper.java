package com.twitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.model.exception.TwitterError;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.util.Map;

@Log4j2
public class ResponseWrapper {
    private ResponseWrapper() {
    }

    public static Status createStatus(Map<Object, Object> jsonMap) {
        Status status = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(jsonMap);
            status = TwitterObjectFactory.createStatus(json);
        } catch (TwitterException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return status;
    }

    public static Status createStatus(String json) {
        Status status = null;
        try {
            status = TwitterObjectFactory.createStatus(json);
        } catch (TwitterException e) {
            log.error(e.getMessage());
        }
        return status;
    }

    public static TwitterError createTwitterError(Response response) {
        return response.as(TwitterError.class);
    }
}
