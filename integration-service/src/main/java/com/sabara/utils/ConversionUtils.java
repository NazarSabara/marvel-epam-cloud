package com.sabara.utils;

import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static org.apache.commons.lang3.StringUtils.*;

public class ConversionUtils {

    private static String WEIGHT_PATTERN = "\\d+\\s\\b(kg|tons)\\b";
    private static String HEIGHT_PATTERN = "\\d+\\s\\b(cm|meters)\\b";
    private static Map<String, Integer> weightMap = Map.of("kg", 1,"tons", 1000);
    private static Map<String, Integer> heightMap = Map.of("cm", 1,"meters", 100);

    public static Double parseWeight(List<String> weight) {
        return weight.stream()
                .filter(w -> w.matches(WEIGHT_PATTERN))
                .findFirst()
                .map(s -> parseUnits(s, weightMap)).orElse(0d);
    }

    public static Double parseHeight(List<String> height){
        return height.stream()
                .filter(w -> w.matches(HEIGHT_PATTERN))
                .findFirst()
                .map(s -> parseUnits(s, heightMap)).orElse(0d);
    }

    private static Double parseUnits(String value, Map<String, Integer> map){
        if(isBlank(value)){
            return null;
        }

        return map.getOrDefault(substringAfter(value, " "), 0) * parseDouble(getDigits(value));
    }
}
