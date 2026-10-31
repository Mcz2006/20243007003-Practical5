package com.example.practicalfive;

import java.util.Locale;

/**
 * Plain Java class that validates and normalises municipality names.
 * It does not extend Activity and has no associated layout, so all
 * validation logic lives in one reusable place.
 */
public class TextProcessor {

    private static final String[] MUNICIPALITIES = {
            "Beijing", "Shanghai", "Chongqing", "Tianjin"
    };

    /**
     * Validates and normalises a municipality name.
     *
     * @param municipality the raw text entered by the user
     * @return the correctly capitalised municipality name, or
     *         {@code null} when the input is null, blank, or unsupported
     */
    public String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String normalised = municipality.trim().toLowerCase(Locale.US);
        for (String valid : MUNICIPALITIES) {
            if (valid.toLowerCase(Locale.US).equals(normalised)) {
                return valid;
            }
        }
        return null;
    }
}
