package com.ywbest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonToJavaCodeUtilTest {

    private JsonToJavaCodeUtil jsonToJavaCodeUtil;

    @Before
    public void setUp() throws Exception {
        jsonToJavaCodeUtil = new JsonToJavaCodeUtil();
    }

    @Test
    public void convertJsonStringToJsonNode() throws JsonProcessingException {
        String json = "{\n" +
                "  \"city\": \"Seoul\",\n" +
                "  \"streets\": [\n" +
                "    \"address1\",\n" +
                "    \"address2\"\n" +
                "  ]\n" +
                "}";

        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        assertEquals(resultMap.toString(),"{root=[private String city; \n" +
                ", private List<String> streets; \n" +
                "]}");
    }

    @Test
    public void convertJsonStringToJsonNode2() throws JsonProcessingException {
        String json = "{\n" +
                "\t\"id\": \"0001\",\n" +
                "\t\"type\": \"donut\",\n" +
                "\t\"name\": \"Cake\",\n" +
                "\t\"ppu\": 0.55,\n" +
                "\t\"batters\":\n" +
                "\t\t{\n" +
                "\t\t\t\"batter\":\n" +
                "\t\t\t\t[\n" +
                "\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n" +
                "\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil's Food\" }\n" +
                "\t\t\t\t]\n" +
                "\t\t},\n" +
                "\t\"topping\":\n" +
                "\t\t[\n" +
                "\t\t\t{ \"id2\": \"5001\", \"type2\": \"None\" },\n" +
                "\t\t\t{ \"id2\": \"5002\", \"type2\": \"Glazed\" },\n" +
                "\t\t\t{ \"id2\": \"5005\", \"type2\": \"Sugar\" },\n" +
                "\t\t\t{ \"id2\": \"5007\", \"type2\": \"Powdered Sugar\" },\n" +
                "\t\t\t{ \"id2\": \"5006\", \"type2\": \"Chocolate with Sprinkles\" },\n" +
                "\t\t\t{ \"id2\": \"5003\", \"type2\": \"Chocolate\" },\n" +
                "\t\t\t{ \"id2\": \"5004\", \"type2\": \"Maple\" }\n" +
                "\t\t]\n" +
                "}";

        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        assertEquals(resultMap.toString(),"{root=[private String id; \n" +
                ", private String type; \n" +
                ", private String name; \n" +
                ", private double ppu; \n" +
                ", private batters batters; \n" +
                ", private List<topping> topping; \n" +
                "], batters=[private List<batter> batter; \n" +
                "], batter=[private String id; \n" +
                ", private String type; \n" +
                "], topping=[private String id2; \n" +
                ", private String type2; \n" +
                "]}");
    }
}