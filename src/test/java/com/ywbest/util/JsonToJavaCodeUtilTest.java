package com.ywbest.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
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
    public void convertJsonStringToJsonNode() throws IOException {
        String json = "{\n" +
                "  \"city\": \"Seoul\",\n" +
                "  \"streets\": [\n" +
                "    \"address1\",\n" +
                "    \"address2\"\n" +
                "  ]\n" +
                "}";

        JsonNode jsonNode = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonNodeToMap(jsonNode);
        assertEquals("{root=[private String city; \n" +
                ", private List<String> streets; \n" +
                "]}",resultMap.toString());
    }

    @Test
    public void convertJsonStringToJsonNode2() throws IOException {
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

        JsonNode jsonNode = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonNodeToMap(jsonNode);
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
    public void convertJsonStringToJsonNode3() throws IOException {
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

        JsonNode jsonNode = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonNodeToMap(jsonNode);
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
    public void convertJsonStringToJsonNode4() throws IOException {
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

        JsonNode jsonNode = jsonToJavaCodeUtil.convertJsonStringToJsonNode(json);
        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonNodeToMap(jsonNode);
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

    @Test
    public void convertXmlStringToMap1() throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<response>\n" +
                "    <header>\n" +
                "        <resultCode>00</resultCode>\n" +
                "        <resultMsg>NORMAL SERVICE.</resultMsg>\n" +
                "    </header>\n" +
                "    <body>\n" +
                "        <items>\n" +
                "            <item><????????????>    42,700\n" +
                "            </????????????><????????????>????????????\n" +
                "        </????????????><????????????>1995\n" +
                "    </????????????><???>2022\n" +
                "</???><?????????> ?????????\n" +
                "</?????????><?????????>?????????????????????\n" +
                "</?????????><???>1\n" +
                "</???><???>6\n" +
                "</???><????????????>113.18\n" +
                "</????????????><??????????????????>?????? ???????????????\n" +
                "</??????????????????><??????>1125-2\n" +
                "</??????><????????????>41115\n" +
                "</????????????><???>11\n" +
                "</???><?????????????????????> \n" +
                "</?????????????????????><????????????> \n" +
                "</????????????>\n" +
                "</item>\n" +
                "<item><????????????>    27,500\n" +
                "</????????????><????????????>????????????\n" +
                "</????????????><????????????>2004\n" +
                "</????????????><???>2022\n" +
                "</???><?????????> ?????????\n" +
                "</?????????><?????????>??????????????????\n" +
                "</?????????><???>1\n" +
                "</???><???>6\n" +
                "</???><????????????>58.3869\n" +
                "</????????????><??????????????????>?????? ???????????????\n" +
                "</??????????????????><??????>968-6\n" +
                "</??????><????????????>41115\n" +
                "</????????????><???>10\n" +
                "</???><?????????????????????> \n" +
                "</?????????????????????><????????????> \n" +
                "</????????????>\n" +
                "</item>\n" +
                "<item><????????????>     6,400\n" +
                "</????????????><????????????>????????????\n" +
                "</????????????><????????????>2014\n" +
                "</????????????><???>2022\n" +
                "</???><?????????> ?????????\n" +
                "</?????????><?????????>?????????????????????\n" +
                "</?????????><???>1\n" +
                "</???><???>10\n" +
                "</???><????????????>15.02\n" +
                "</????????????><??????????????????>?????? ???????????????\n" +
                "</??????????????????><??????>1114-5\n" +
                "</??????><????????????>41115\n" +
                "</????????????><???>10\n" +
                "</???><?????????????????????> \n" +
                "</?????????????????????><????????????> \n" +
                "</????????????>\n" +
                "</item>\n" +
                "</items>\n" +
                "<numOfRows>10</numOfRows>\n" +
                "<pageNo>1</pageNo>\n" +
                "<totalCount>3</totalCount>\n" +
                "</body>\n" +
                "</response>";

        JsonNode jsonNode = jsonToJavaCodeUtil.convertXmlStringToJsonNode(xml);
        LinkedHashMap<String, List<String>> resultMap = jsonToJavaCodeUtil.convertJsonNodeToMap(jsonNode);
        assertEquals("{root=[private header header; \n" +
                ", private body body; \n" +
                "], header=[private String resultCode; \n" +
                ", private String resultMsg; \n" +
                "], body=[private items items; \n" +
                ", private String numOfRows; \n" +
                ", private String pageNo; \n" +
                ", private String totalCount; \n" +
                "], items=[private List<item> item; \n" +
                "], item=[private String ????????????; \n" +
                ", private String ????????????; \n" +
                ", private String ????????????; \n" +
                ", private String ???; \n" +
                ", private String ?????????; \n" +
                ", private String ?????????; \n" +
                ", private String ???; \n" +
                ", private String ???; \n" +
                ", private String ????????????; \n" +
                ", private String ??????????????????; \n" +
                ", private String ??????; \n" +
                ", private String ????????????; \n" +
                ", private String ???; \n" +
                ", private String ?????????????????????; \n" +
                ", private String ????????????; \n" +
                "]}", resultMap.toString());
    }


}