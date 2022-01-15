package com.ywbest.util;

public class StringUtil {
    public static String convertInputXss(Object input) {
        String inputString = (String) input;
        inputString = inputString.replaceAll("&", "&amp;");
        inputString = inputString.replaceAll("<", "&lt;");
        inputString = inputString.replaceAll(">", "&gt;");
        inputString = inputString.replaceAll("\"", "&quot;");
        inputString = inputString.replaceAll("'", "&apos;");
        inputString = inputString.replaceAll(" ", "&nbsp;");
        inputString = inputString.replaceAll("\n", "<br/>");
        return inputString;
    }

    public static String convertOutputXss(Object output) {
        String outputString = (String) output;
        outputString = outputString.replaceAll("&amp;", "&");
        outputString = outputString.replaceAll("&lt;", "<");
        outputString = outputString.replaceAll("&gt;", ">");
        outputString = outputString.replaceAll("&quot;", "\"");
        outputString = outputString.replaceAll("&apos;", "'");
        outputString = outputString.replaceAll("&nbsp;", " ");
        outputString = outputString.replaceAll("<br/>", "\n");
        return outputString;
    }

    public static String addLineWithTabWithEnter(String inputString, int tabCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<tabCount; i++){
            stringBuilder.append("\u00A0\u00A0\u00A0");
        }
        stringBuilder.append(inputString);
        stringBuilder.append("<br/>");
        return stringBuilder.toString();
    }
}
