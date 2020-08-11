package com.ingendevelopment;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Brian Smith on 8/10/20.
 * Description: ConsumerPactTest
 */
@ExtendWith(PactConsumerTestExt.class)
public class ConsumerPactTest {

    @Pact(provider = "UUIDValidate", consumer = "UUIDClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("request validated UUID")
                .uponReceiving("Respond with random UUID")
                .path("/api/v0/uuid")
                .method("GET")
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(
                        new PactDslJsonBody()
                                .stringMatcher("uuid", "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}", "89529867-d7e3-4628-a6dd-46010e295fa1")
                                .booleanValue("validated", true)
                ).toPact();
    }

    @Test
    @PactTestFor(providerName = "UUIDValidate", port = "8221", pactVersion = PactSpecVersion.V2)
    void test(MockServer mockServer) throws UnirestException {
        JsonNode responseBody = Unirest.get(mockServer.getUrl() + "/api/v0/uuid").asJson().getBody();

        assertThat(responseBody.getObject().get("uuid").toString(), matchesPattern("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}"));
        assertEquals(responseBody.getObject().get("validated").toString(), "true");
    }
}
