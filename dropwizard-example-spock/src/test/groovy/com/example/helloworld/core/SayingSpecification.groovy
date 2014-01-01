package com.example.helloworld.core
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class SayingSpecification extends Specification {

    def "serializes Saying to JSON"() {
        given:
        def mapper = new ObjectMapper(new JsonFactory())

        expect:
        expectedJson == mapper.writeValueAsString(new Saying(index, geeting))

        where:
        index   | geeting | expectedJson
        1       | 'Hello Jakub!'    | '{"id":1,"content":"Hello Jakub!"}'
        2       | 'Guten Tag'    | '{"id":2,"content":"Guten Tag"}'
    }

    def "deserializes Saying from JSON"() {
        given:
        def mapper = new ObjectMapper(new JsonFactory())

        expect:
        mapper.readValue(jsonString, Saying.class) == saying

        where:
        jsonString | saying
        '{"id":1,"content":"Hello Jakub!"}' | new Saying(id: 1, content: 'Hello Jakub!')
        '{"id":2,"content":"Guten Tag"}' | new Saying(id: 2, content: 'Guten Tag')
    }

}
