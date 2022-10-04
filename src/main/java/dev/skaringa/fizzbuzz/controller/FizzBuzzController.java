package dev.skaringa.fizzbuzz.controller;

import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import dev.skaringa.fizzbuzz.service.FizzBuzzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Validated
@RestController
@CrossOrigin("*")
@Api(tags = "FizzBuzz Sequence Controller")
@RequiredArgsConstructor
@RequestMapping("api/sequences/fizz-buzz")
public class FizzBuzzController {
    private final FizzBuzzService fizzBuzzService;

    @GetMapping("{length}")
    public Sequence<SequenceDataEntry> getFizzBuzz(
            @ApiParam(value = "Length", example = "100") @PathVariable @Min(1) @Max(10000) int length) {
        return fizzBuzzService.getSequence(length);
    }
}
