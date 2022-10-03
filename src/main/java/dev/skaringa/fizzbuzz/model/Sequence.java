package dev.skaringa.fizzbuzz.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@ApiModel
public class Sequence<T extends SequenceDataEntry> {
    @NonNull
    @ApiModelProperty(value = "Sequence Type", example = "FIZZ_BUZZ", required = true)
    SequenceType type;

    @NonNull
    @ApiModelProperty(value = "List of Sequence Data Entries", required = true)
    List<T> data;
}
