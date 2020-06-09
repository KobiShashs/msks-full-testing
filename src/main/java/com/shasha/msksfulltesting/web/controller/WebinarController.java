package com.shasha.msksfulltesting.web.controller;

import com.shasha.msksfulltesting.service.WebinarService;
import com.shasha.msksfulltesting.web.model.WebinarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 07 Jun, 2020
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/webinar")
@RestController
public class WebinarController {
    private final WebinarService webinarService;

    @GetMapping({"/{webinarID}"})
    public ResponseEntity<WebinarDto> getItem(@NotNull @PathVariable("webinarID") String webinarID) {
        return new ResponseEntity<>(webinarService.getWebinarByID(webinarID), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addItem(@Valid @RequestBody WebinarDto webinarDto) {
        return new ResponseEntity<>(webinarService.saveNewWebinar(webinarDto), HttpStatus.CREATED);
    }

    @PutMapping({"/{webinarID}"})
    public ResponseEntity updateItem(@NotNull @PathVariable("webinarID") String webinarID, @Valid @RequestBody WebinarDto webinarDto) {

        return new ResponseEntity<>(webinarService.updateWebinar(webinarID, webinarDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{webinarID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@NotNull @PathVariable("webinarID") String webinarID) {
        webinarService.deleteById(webinarID);
    }

    @GetMapping({"/seats/{min}/{max}"})
    public ResponseEntity<List<WebinarDto>> getWebinarsBySeats(@NotNull @PathVariable("min") int min, @NotNull @PathVariable("max") int max) {
        return new ResponseEntity<>(webinarService.findBySeatsBetween(min, max), HttpStatus.OK);
    }

    @GetMapping({"/small-meetups"})
    public ResponseEntity<List<WebinarDto>> getSmallMeetups() {
        return new ResponseEntity<>(webinarService.findSmallMeetups(), HttpStatus.OK);
    }

    @GetMapping({"/ping"})
    public ResponseEntity<?> pingCheck() {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


}
