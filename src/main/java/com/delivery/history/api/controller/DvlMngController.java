package com.delivery.history.api.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dvl-mng/")
public class DvlMngController {

    @GetMapping("/select-dvl-list")
    public String selectDvlList(@RequestParam String param) {
        return new String();
    }

}
