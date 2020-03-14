package com.sabara.utils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConversionUtils {

    private static String WEIGHT_PATTERN = "[0-9]*\\s\\b(kg|tons)\\b";
    private static String HEIGHT_PATTERN = "[0-9]*\\s\\b(cm|meters)\\b";
    private static Map<String, Integer> weightMap = Map.of("kg", 1,"tons", 1000);
    private static Map<String, Integer> heightMap = Map.of("cm", 1,"meters", 100);

    public static Double parseWeight(List<String> weight) {
        Optional<String> result = weight.stream()
                .filter(w -> w.matches(WEIGHT_PATTERN))
                .findFirst();

        return parseUnits(result.orElse("0 kg"), weightMap);
    }

    public static Double parseHeight(List<String> height){
        Optional<String> result = height.stream()
                .filter(w -> w.matches(HEIGHT_PATTERN))
                .findFirst();

        return parseUnits(result.orElse("0 kg"), heightMap);
    }

    private static Double parseUnits(String value, Map<String, Integer> map){
        String[] units = value.split("\\s");
        return map.getOrDefault(units[1], 0) * Double.parseDouble(units[0]);
    }
}
