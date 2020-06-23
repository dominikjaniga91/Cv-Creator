package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.PersonalData;
import com.cvgenerator.domain.entity.Summary;
import com.cvgenerator.service.implementation.SummaryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@Api(tags = "Summary controller")
@RestController
@RequestMapping("/api/cv/summary")
public class SummaryController {

    private final SummaryServiceImpl summaryService;

    public SummaryController(SummaryServiceImpl summaryService) {
        this.summaryService = summaryService;
    }

    @ApiOperation(value = "Save new summary for cv with specific ID into database")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSummary(@PathVariable Long cvId, @RequestBody Summary summary){
        summaryService.createSummary(cvId, summary);
    }

    @ApiOperation(value = "Update cv summary")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSummary(@RequestBody Summary summary){
        summaryService.updateSummary(summary);
    }
}
