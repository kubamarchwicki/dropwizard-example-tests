package com.example.helloworld.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static io.dropwizard.testing.FixtureHelpers.*;

public class SayingMappingTest {

    private static ObjectMapper MAPPER;

    @BeforeClass
    public static void setup() {
        MAPPER = new ObjectMapper(new JsonFactory());
    }

    @Test
    public void serializesToJSON() throws Exception {
        final Saying person = new Saying(1, "Hello Jakub!");
        assertThat("a Person can be serialized to JSON",
                MAPPER.writeValueAsString(person),
                is(equalTo(jsonFixture("fixtures/jakubSaying.json"))));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Saying person = new Saying(2, "Guten Tag");
        assertThat("a Person can be deserialized from JSON",
                MAPPER.readValue(jsonFixture("fixtures/germanSaying.json"), Saying.class),
                is(person));
    }

    private String jsonFixture(String filename) throws IOException {
        return MAPPER.writeValueAsString(MAPPER.readValue(fixture(filename), JsonNode.class));
    }

}
