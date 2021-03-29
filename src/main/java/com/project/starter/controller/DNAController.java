/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */

package com.project.starter.controller;

import com.project.starter.model.DNAModel;
import com.project.starter.model.Stats;
import com.project.starter.service.CheckDNA;
import com.project.starter.service.StatsService;
import com.project.starter.util.RenderStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DNAController {

    private CheckDNA checkDNAService;
    private StatsService statsService;

    public DNAController(@Autowired CheckDNA checkDNAService, @Autowired StatsService statsService) {
        this.checkDNAService = checkDNAService;
        this.statsService = statsService;
    }

    @PostMapping("/mutant")
    @ResponseStatus(HttpStatus.OK)
    public void checkMutant(@RequestBody final DNAModel dna) {

        //Checks that matrix must be square
        if(dna.getDna().length != dna.getDna()[0].length()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The matrix must be NxN");
        }

        //Validates if it's mutant
        if(!checkDNAService.isMutant(dna.getDna())) {
            statsService.saveOrUpdate(new Stats("not_mutant"));
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not a mutant");
        } else {
            statsService.saveOrUpdate(new Stats("mutant"));
        }
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public RenderStats getStats() {

        RenderStats renderStats = new RenderStats();

        if(statsService.getAllStats().size() == 0) {
            renderStats.setNoMutants(0);
            renderStats.setMutants(0);
            renderStats.setAverageMutants(0);
        }
        else {
            renderStats.setMutants(statsService.getMutants());
            renderStats.setNoMutants(statsService.getAllStats().size() - statsService.getMutants());
            renderStats.setAverageMutants(statsService.getAverageMutants());
        }

        return renderStats;
    }

}
