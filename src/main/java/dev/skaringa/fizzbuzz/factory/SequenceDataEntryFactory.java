package dev.skaringa.fizzbuzz.factory;

import dev.skaringa.fizzbuzz.model.IntegerSequenceDataEntry;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import dev.skaringa.fizzbuzz.model.StringSequenceDataEntry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SequenceDataEntryFactory {
    public List<SequenceDataEntry> toModels(List<Serializable> source) {
        Assert.notNull(source, "Sequence data entries 'source' must not be null");
        return source.stream()
                .map(this::toModel)
                .collect(Collectors.toUnmodifiableList());
    }

    private SequenceDataEntry toModel(Serializable source) {
        Assert.notNull(source, "Sequence data entry 'source' must not be null");
        if (source instanceof Integer) {
            return new IntegerSequenceDataEntry((Integer) source);
        } else if (source instanceof String) {
            return new StringSequenceDataEntry((String) source);
        }

        throw new IllegalArgumentException("Unexpected type of sequence data entry");
    }
}
