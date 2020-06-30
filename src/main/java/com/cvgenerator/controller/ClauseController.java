package com.cvgenerator.controller;

import com.cvgenerator.domain.entity.Clause;
import com.cvgenerator.service.implementation.ClauseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@Api(tags = "Clause controller")
@RestController
@RequestMapping("/api/cv/clause")
public class ClauseController {

    private final ClauseServiceImpl clauseService;

    public ClauseController(ClauseServiceImpl clauseService) {
        this.clauseService = clauseService;
    }

    @ApiOperation(value = "Save new clause for cv with specific ID into database")
    @PostMapping("/{cvId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClause(@PathVariable Long cvId, @RequestBody Clause clause){
        clauseService.createClause(cvId, clause);
    }

    @ApiOperation(value = "Update cv clause")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateClause(@RequestBody Clause clause){
        clauseService.updateClause(clause);
    }

    @ApiOperation(value = "Delete cv clause from database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClause(@PathVariable Long id){
        clauseService.deleteClauseById(id);
    }

}
