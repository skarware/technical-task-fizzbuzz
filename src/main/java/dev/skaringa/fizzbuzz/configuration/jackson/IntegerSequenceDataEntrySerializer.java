package dev.skaringa.fizzbuzz.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import dev.skaringa.fizzbuzz.model.IntegerSequenceDataEntry;
import lombok.SneakyThrows;

public class IntegerSequenceDataEntrySerializer extends StdSerializer<IntegerSequenceDataEntry> {
    protected IntegerSequenceDataEntrySerializer() {
        this(null);
    }

    protected IntegerSequenceDataEntrySerializer(Class<IntegerSequenceDataEntry> t) {
        super(t);
    }

    @Override
    @SneakyThrows
    public void serialize(IntegerSequenceDataEntry value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeNumber(value.getValue());
    }
}
