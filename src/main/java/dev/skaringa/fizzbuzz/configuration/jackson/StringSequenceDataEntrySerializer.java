package dev.skaringa.fizzbuzz.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.skaringa.fizzbuzz.model.StringSequenceDataEntry;
import lombok.SneakyThrows;

public class StringSequenceDataEntrySerializer extends StdSerializer<StringSequenceDataEntry> {
    protected StringSequenceDataEntrySerializer() {
        this(null);
    }

    protected StringSequenceDataEntrySerializer(Class<StringSequenceDataEntry> t) {
        super(t);
    }

    @Override
    @SneakyThrows
    public void serialize(StringSequenceDataEntry value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeString(value.getValue());
    }
}
