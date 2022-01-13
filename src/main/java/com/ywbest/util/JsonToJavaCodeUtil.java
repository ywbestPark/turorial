package com.ywbest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonToJavaCodeUtil {

    public JsonNode convertXmlStringToJsonNode(String xmlString) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(xmlString.getBytes());
        return node;
    }

    public JsonNode convertJsonStringToJsonNode(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(jsonString.getBytes());
        return node;
    }

    public LinkedHashMap convertJsonNodeToMap(JsonNode jsonNode) {

        LinkedHashMap<String, List<String>> objectMapList = new LinkedHashMap<>();
        List<String> lineList = new ArrayList<>();
        objectMapList.put("root",lineList);

        LinkedHashMap<String, List<String>> resultMap = traverseJsonNode(jsonNode, objectMapList, lineList);

        System.out.println(resultMap.toString());
        return resultMap;
    }

    private LinkedHashMap<String, List<String>> traverseJsonNode(JsonNode jsonNode, LinkedHashMap<String, List<String>> objectMapList, List<String> lineList) {

        if(jsonNode.isObject()){//오브젝트 일 경우 오브젝트를 구성하는 필드들을 추출
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()){
                Map.Entry<String, JsonNode> entry = fields.next();//오브젝트를 구성하는 1개 필드 추출

                /*
                * 추출한 필드의 Value를 이용하여 필드 타입 구분
                * 1. 필드 Value가 오브젝트일 경우 :
                *    1-1. 오브젝트 필드 추가(private A a;)
                *    1-2. 재귀 함수를 이용하여 계속 순회
                * 2. 필드 Value가 배열일 경우 :
                *   2-1. 배열을 1개 추출
                *   2-1-1. 추출한 배열이 오브젝트일 경우 필드 추가(private List<A> a)
                *   2-1-2. 추출한 배열이 또 배열일 경우는 문법적으로 오류라고 인식하고 오류 리턴
                *   2-1-3. 추출한 배열이 Primary일 경우 필드 추가(private List<Long> a)
                *   2-3. 재귀 함수를 이용하여 계속 순회
                * 3. 필드 Value가 Primary일 경우 :
                *   3-1. Primary 필드 추가(private Long a)
                * */
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
        }else if(jsonNode.isArray()){// 제일 바깥쪽에 배열이 오는 경우 때문에...
            Iterator<JsonNode> jsonNodeIterator = jsonNode.iterator();
            while (jsonNodeIterator.hasNext()){
                JsonNode currentNode = jsonNodeIterator.next();
                traverseJsonNode(currentNode, objectMapList, lineList);
                break;  // 만약 모든 필드의 값을 알고 싶다면 주석 해제
            }
        }else{

        }
        return objectMapList;
    }

    public String convertLinkedHashMapToJavaCode(LinkedHashMap<String, List<String>> resultMap, String filePath, boolean doAppend){
        StringBuilder sb = new StringBuilder();

        //아래와 같이 BufferedWriter를 선언하면 finally에서 close를 안해줘도 자동으로 close 됨
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, doAppend))){

            Iterator<List<String>> iterator = resultMap.values().iterator();
            Set<String> keys = resultMap.keySet();
            for (String key : keys){
                String className = key.substring(0, 1).toUpperCase() + key.substring(1);
//                bw.append("public class "+className+"{"+"\n");
                bw.append(addLineWithTabWithEnter("public class "+className+"{", 0));
                sb.append(addLineWithTabWithEnter("public class "+className+"{", 0));
                List<String> lineList = resultMap.get(key);
                for (String line : lineList){
                    bw.append("\t"+line);
                    sb.append("\t"+line);
                }
                //bw.append("}"+"\n");
                bw.append(addLineWithTabWithEnter("}", 0));
                bw.append(addLineWithTabWithEnter("", 0));
                sb.append(addLineWithTabWithEnter("}", 0));
                sb.append(addLineWithTabWithEnter("", 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private String addLineWithTabWithEnter(String inputString, int tabCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<tabCount; i++){
            stringBuilder.append("\t");
        }
        stringBuilder.append(inputString);
        stringBuilder.append("\n");
        return stringBuilder.toString();
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
        if(value.isBinary()){
            return "binary";
        }
        return  "UnknownType";
    }
}
