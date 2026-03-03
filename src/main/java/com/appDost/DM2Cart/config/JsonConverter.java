//package com.appDost.DM2Cart.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//import java.util.Map;
//
//@Converter(autoApply = false)
//public class JsonConverter implements AttributeConverter<Map<String, Object>, String> {
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public String convertToDatabaseColumn(Map<String, Object> attribute) {
//        try {
//            if (attribute == null) return null;
//            return objectMapper.writeValueAsString(attribute);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Error converting Map to JSON", e);
//        }
//    }
//
//    @Override
//    public Map<String, Object> convertToEntityAttribute(String dbData) {
//        try {
//            if (dbData == null) return null;
//            return objectMapper.readValue(dbData, Map.class);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Error converting JSON to Map", e);
//        }
//    }
//}
