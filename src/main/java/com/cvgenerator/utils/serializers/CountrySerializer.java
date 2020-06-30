package com.cvgenerator.utils.serializers;

import com.cvgenerator.domain.enums.Country;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 * This class is required for proper serialization Country enum to JSON
 */

public class CountrySerializer extends StdSerializer<Country> {

    public CountrySerializer() {
        super(Country.class);
    }

    public CountrySerializer(Class<Country> t) {
        super(t);
    }

    @Override
    public void serialize(Country country,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeString(country.getValue());
        jsonGenerator.writeFieldName("areaCode");
        jsonGenerator.writeString(country.getAreaCode());
        jsonGenerator.writeEndObject();
    }


}
