package com.example.demo.Converters;

import jakarta.persistence.AttributeConverter;

import jakarta.persistence.Converter;
import org.postgresql.util.PGobject;

@Converter(autoApply = false)
public class VectorConverter  implements AttributeConverter<float[], Object> {

    @Override
    public Object convertToDatabaseColumn(float[] attribute) {
        if (attribute == null) return null;

        try {
            PGobject pgObject = new PGobject();
            pgObject.setType("vector");

            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < attribute.length; i++) {
                sb.append(attribute[i]);
                if (i < attribute.length - 1) sb.append(",");
            }
            sb.append("]");

            pgObject.setValue(sb.toString());
            return pgObject;

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert vector", e);
        }
    }

    @Override
    public float[] convertToEntityAttribute(Object dbData) {
        if (dbData == null) return null;

        String s = dbData.toString()
                .replace("[", "")
                .replace("]", "");

        String[] parts = s.split(",");
        float[] vec = new float[parts.length];

        for (int i = 0; i < parts.length; i++) {
            vec[i] = Float.parseFloat(parts[i]);
        }
        return vec;
    }
}