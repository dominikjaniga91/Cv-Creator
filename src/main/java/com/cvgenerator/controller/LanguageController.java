package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Language;
import com.cvgenerator.service.implementation.LanguageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Language controller")
@RestController
@RequestMapping("/api/cv/language")
public class LanguageController {

    private final LanguageServiceImpl languageService;

    @Autowired
    public LanguageController(LanguageServiceImpl languageService) {
        this.languageService = languageService;
    }

    @ApiOperation(value = "Save new language for cv with specific ID into database ")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLanguage(@PathVariable Long cvId, @RequestBody Language language){
        languageService.createLanguage(cvId, language);
    }
}