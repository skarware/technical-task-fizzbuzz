package dev.skaringa.fizzbuzz.controller;

import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import dev.skaringa.fizzbuzz.service.FizzBuzzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Api(tags = "FizzBuzz Sequence Controller")
@RequiredArgsConstructor
@RequestMapping("api/sequences/fizz-buzz")
public class FizzBuzzController {
    private final FizzBuzzService fizzBuzzService;

    @GetMapping("{length}")
    public Sequence<SequenceDataEntry> getFizzBuzz(
            @ApiParam(value = "Length", example = "100") @PathVariable int length) {
        return fizzBuzzService.getSequence(length);
    }
}
