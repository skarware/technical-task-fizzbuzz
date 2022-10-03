package dev.skaringa.fizzbuzz.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.skaringa.fizzbuzz.configuration.jackson.StringSequenceDataEntrySerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
import lombok.Value;

@Value
@ApiModel("String Sequence Data Entry")
@JsonSerialize(using = StringSequenceDataEntrySerializer.class)
public class StringSequenceDataEntry implements SequenceDataEntry {
    @NonNull
    @ApiModelProperty(value = "String", example = "ABC", required = true)
    String value;
}
