package io.github.burakpadr.turkeylocation4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the district
 * 
 * @author burakpadr
 */
public class District {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This function creates objects of districts within the city.
     * 
     * @param cityName
     * @return the district objects
     * @since 1.0.0
     */

    public static List<District> fromCityName(String cityName) {
        return DistrictParser.parse(cityName);
    }

    @Override
    public String toString() {
        return "{\n name: " + name + "\n}";
    }

    private static class DistrictParser {

        private static final String DISTRICTS_DIR_PATH = "data/districts";

        @SuppressWarnings("unchecked")
        public static List<District> parse(String cityName) {
            Map<String, Object> content = (Map<String, Object>) YamlUtils
                    .yamlToJson(DISTRICTS_DIR_PATH.concat("/")
                            .concat(StringUtils.clearTurkishChars(cityName).toLowerCase()).concat(".yaml"));

            List<District> districts = new ArrayList<>();

            content.entrySet().stream().forEach(entry -> {
                District district = new District();
                district.setName(entry.getKey());

                districts.add(district);
            });

            return districts;
        }
    }
}
