package dev.skaringa.fizzbuzz.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.skaringa.fizzbuzz.configuration.jackson.IntegerSequenceDataEntrySerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
import lombok.Value;

@Value
@ApiModel("Integer Sequence Data Entry")
@JsonSerialize(using = IntegerSequenceDataEntrySerializer.class)
public class IntegerSequenceDataEntry implements SequenceDataEntry {
    @NonNull
    @ApiModelProperty(value = "Integer", example = "123", required = true)
    Integer value;
}
