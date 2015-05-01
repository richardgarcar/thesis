package cz.muni.fi.cepv.web;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import cz.muni.fi.cepv.web.exception.CustomDateDeserializerException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xgarcar
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (JsonToken.VALUE_NUMBER_INT == jsonParser.getCurrentToken()) {
            return new Date(jsonParser.getLongValue());
        }

        final String date = jsonParser.getText();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new CustomDateDeserializerException(e);
        }
    }
}
