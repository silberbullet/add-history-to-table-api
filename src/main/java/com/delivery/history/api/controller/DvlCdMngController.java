package com.delivery.history.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.history.api.service.DvlCdMngService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dvl-cd-mng")
public class DvlCdMngController {

    private final DvlCdMngService dvlCdMngService;

    @GetMapping("/select-list")
    public String selectList() {

        return "string";
    }
}
