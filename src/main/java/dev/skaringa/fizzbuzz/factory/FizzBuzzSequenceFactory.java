package dev.skaringa.fizzbuzz.factory;

import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import dev.skaringa.fizzbuzz.model.SequenceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FizzBuzzSequenceFactory {
    private final SequenceDataEntryFactory sequenceDataEntryFactory;

    public Sequence<SequenceDataEntry> toModel(List<Serializable> source) {
        SequenceType type = SequenceType.FIZZ_BUZZ;
        Assert.notNull(source, "Sequence 'source' must not be null");
        return new Sequence<>(type, toSequenceDataEntryModels(source));
    }

    private List<SequenceDataEntry> toSequenceDataEntryModels(List<Serializable> source) {
        return sequenceDataEntryFactory.toModels(source);
    }
}
