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
        assertEquals("{root=[private String city; \n" +
                ", private List<String> streets; \n" +
                "]}",resultMap.toString());
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
        assertEquals("{root=[private String id; \n" +
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
                "]}",resultMap.toString());
    }

    @Test
    public void convertJsonStringToJsonNode3() throws JsonProcessingException {
        String json = "[\n" +
                "\t{\n" +
                "\t\t\"id\": \"0001\",\n" +
                "\t\t\"type\": \"donut\",\n" +
                "\t\t\"name\": \"Cake\",\n" +
                "\t\t\"ppu\": 0.55,\n" +
                "\t\t\"batters\":\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"batter\":\n" +
                "\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n" +
                "\t\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n" +
                "\t\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n" +
                "\t\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil's Food\" }\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\"topping\":\n" +
                "\t\t\t[\n" +
                "\t\t\t\t{ \"id2\": \"5001\", \"type2\": \"None\" },\n" +
                "\t\t\t\t{ \"id2\": \"5002\", \"type2\": \"Glazed\" },\n" +
                "\t\t\t\t{ \"id2\": \"5005\", \"type2\": \"Sugar\" },\n" +
                "\t\t\t\t{ \"id2\": \"5007\", \"type2\": \"Powdered Sugar\" },\n" +
                "\t\t\t\t{ \"id2\": \"5006\", \"type2\": \"Chocolate with Sprinkles\" },\n" +
                "\t\t\t\t{ \"id2\": \"5003\", \"type2\": \"Chocolate\" },\n" +
                "\t\t\t\t{ \"id2\": \"5004\", \"type2\": \"Maple\" }\n" +
                "\t\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"0002\",\n" +
                "\t\t\"type\": \"donut\",\n" +
                "\t\t\"name\": \"Raised\",\n" +
                "\t\t\"ppu\": 0.55,\n" +
                "\t\t\"batters\":\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"batter\":\n" +
                "\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" }\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\"topping\":\n" +
                "\t\t\t[\n" +
                "\t\t\t\t{ \"id2\": \"5001\", \"type2\": \"None\" },\n" +
                "\t\t\t\t{ \"id2\": \"5002\", \"type2\": \"Glazed\" },\n" +
                "\t\t\t\t{ \"id2\": \"5005\", \"type2\": \"Sugar\" },\n" +
                "\t\t\t\t{ \"id2\": \"5003\", \"type2\": \"Chocolate\" },\n" +
                "\t\t\t\t{ \"id2\": \"5004\", \"type2\": \"Maple\" }\n" +
                "\t\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": \"0003\",\n" +
                "\t\t\"type\": \"donut\",\n" +
                "\t\t\"name\": \"Old Fashioned\",\n" +
                "\t\t\"ppu\": 0.55,\n" +
                "\t\t\"batters\":\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"batter\":\n" +
                "\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n" +
                "\t\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" }\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\"topping\":\n" +
                "\t\t\t[\n" +
                "\t\t\t\t{ \"id2\": \"5001\", \"type2\": \"None\" },\n" +
                "\t\t\t\t{ \"id2\": \"5002\", \"type2\": \"Glazed\" },\n" +
                "\t\t\t\t{ \"id2\": \"5003\", \"type2\": \"Chocolate\" },\n" +
                "\t\t\t\t{ \"id2\": \"5004\", \"type2\": \"Maple\" }\n" +
                "\t\t\t]\n" +
                "\t}\n" +
                "]";

        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        assertEquals("{root=[private String id; \n" +
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
                "]}",resultMap.toString());
    }

    @Test
    public void convertJsonStringToJsonNode4() throws JsonProcessingException {
        String json = "{\n" +
                "\t\"items\":\n" +
                "\t\t{\n" +
                "\t\t\t\"item\":\n" +
                "\t\t\t\t[\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"id\": \"0001\",\n" +
                "\t\t\t\t\t\t\"type\": \"donut\",\n" +
                "\t\t\t\t\t\t\"name\": \"Cake\",\n" +
                "\t\t\t\t\t\t\"ppu\": 0.55,\n" +
                "\t\t\t\t\t\t\"batters\":\n" +
                "\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\"batter\":\n" +
                "\t\t\t\t\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil's Food\" }\n" +
                "\t\t\t\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"topping\":\n" +
                "\t\t\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n" +
                "\t\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"id\": \"0001\",\n" +
                "\t\t\t\t\t\t\"type\": \"donut\",\n" +
                "\t\t\t\t\t\t\"name\": \"Cake\",\n" +
                "\t\t\t\t\t\t\"ppu\": 0.55,\n" +
                "\t\t\t\t\t\t\"batters\":\n" +
                "\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\"batter\":\n" +
                "\t\t\t\t\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n" +
                "\t\t\t\t\t\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil's Food\" }\n" +
                "\t\t\t\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"topping\":\n" +
                "\t\t\t\t\t\t\t[\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n" +
                "\t\t\t\t\t\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n" +
                "\t\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\t]\n" +
                "\t\t}\n" +
                "}";

        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        assertEquals("{root=[private items items; \n" +
                "], items=[private List<item> item; \n" +
                "], item=[private String id; \n" +
                ", private String type; \n" +
                ", private String name; \n" +
                ", private double ppu; \n" +
                ", private batters batters; \n" +
                ", private List<topping> topping; \n" +
                "], batters=[private List<batter> batter; \n" +
                "], batter=[private String id; \n" +
                ", private String type; \n" +
                "], topping=[private String id; \n" +
                ", private String type; \n" +
                "]}", resultMap.toString());
    }


}