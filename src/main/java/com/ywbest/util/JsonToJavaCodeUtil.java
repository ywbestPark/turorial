package com.ywbest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.util.*;

public class JsonToJavaCodeUtil {

    public LinkedHashMap convertJsonStringToJsonNode(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        LinkedHashMap<String, List<String>> objectMapList = new LinkedHashMap<>();
        List<String> lineList = new ArrayList<>();
        objectMapList.put("root",lineList);

        LinkedHashMap<String, List<String>> resultMap = traverseJsonNode(jsonNode, objectMapList, lineList);

        System.out.println(resultMap.toString());
        return resultMap;
    }

    private LinkedHashMap<String, List<String>> traverseJsonNode(JsonNode jsonNode, LinkedHashMap<String, List<String>> objectMapList, List<String> lineList) {
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

        while (fields.hasNext()){
            Map.Entry<String, JsonNode> entry = fields.next();

            if(entry.getValue().getNodeType() == JsonNodeType.OBJECT){
                lineList.add("private "+entry.getKey()+" "+entry.getKey()+"; \n");

                List<String> lineList1 = new ArrayList<>();
                objectMapList.put(entry.getKey(),lineList1);
                traverseJsonNode(entry.getValue(), objectMapList, lineList1);
            }else if(entry.getValue().getNodeType() == JsonNodeType.ARRAY){
                Iterator<JsonNode> jsonNodeIterator = entry.getValue().iterator();
                while (jsonNodeIterator.hasNext()){
                    JsonNode currentNode = jsonNodeIterator.next();
                    if(currentNode.isObject()){
                        lineList.add("private List<"+entry.getKey()+"> "+entry.getKey()+"; \n");
                        List<String> lineList1 = new ArrayList<>();
                        objectMapList.put(entry.getKey(),lineList1);
                        traverseJsonNode(currentNode, objectMapList, lineList1);
                    }else if(currentNode.isArray()){
                        throw new RuntimeException("Not Arrow Array in Array");
                    }else{
                        lineList.add("private List<"+getRealType(currentNode)+"> "+entry.getKey()+"; \n");
                    }
                    break;  // 만약 모든 필드의 값을 알고 싶다면 주석 해제
                }
            }else{
                lineList.add("private "+getRealType(entry.getValue())+" "+entry.getKey()+"; \n");
            }
        }

        return objectMapList;
    }

    private String getRealType(JsonNode value) {

        if(value.isInt()){
            return "int";
        }
        if(value.isLong()){
            return "long";
        }
        if(value.isFloat()){
            return "float";
        }
        if(value.isFloatingPointNumber()){
            return "double";
        }
        if(value.isNumber()){
            return "long";
        }
        if(value.isTextual()){
            return "String";
        }
        if(value.isBoolean()){
            return "boolean";
        }
        /*
        if(value.isArray()){
            return "List<"+entry.getKey()+">";
        }
        if(value.isObject()){
            return entry.getKey();
        }
        */
        if(value.isBinary()){
            return "binary";
        }
        return  "UnknownType";
    }
}
