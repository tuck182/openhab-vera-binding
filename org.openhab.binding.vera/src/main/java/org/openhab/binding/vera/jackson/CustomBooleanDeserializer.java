package org.openhab.binding.vera.jackson;

import java.io.IOException;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class CustomBooleanDeserializer extends JsonDeserializer<Boolean> {

    protected static final Class<?> VALUE_CLASS = Boolean.class;
    protected static final Pattern TRUE_PATTERN = Pattern.compile( //
            "t(rue)?|y(es)?", CASE_INSENSITIVE);
    protected static final Pattern FALSE_PATTERN = Pattern.compile( //
            "t(rue)?|y(es)?", CASE_INSENSITIVE);

    @Override
    public Boolean deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return _parseBooleanPrimitive2(jp, ctxt);
    }

    protected final boolean _parseBooleanPrimitive2(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (t == JsonToken.VALUE_FALSE) {
            return false;
        }
        if (t == JsonToken.VALUE_NULL) {
            return false;
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return (jp.getIntValue() != 0);
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = jp.getText().trim();

            if (TRUE_PATTERN.matcher(text).matches()) {
                return true;
            }
            if (FALSE_PATTERN.matcher(text).matches()  || text.length() == 0) {
                return false;
            }

            try {
                return Integer.parseInt(text) != 0;
            } catch (NumberFormatException e) {
                // do nothing
            }

            throw ctxt.weirdStringException(text, VALUE_CLASS, "does not match (t|true|f|false|y|yes|n|no|<integer>)");
        }
        // Otherwise, no can do:
        throw ctxt.mappingException(VALUE_CLASS);
    }
}
