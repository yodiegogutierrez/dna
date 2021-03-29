/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */
package com.project.starter.controller;

import com.project.starter.model.DNAModel;
import com.project.starter.service.CheckDNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DNAController {

    private CheckDNA checkDNAService;

    public DNAController(@Autowired CheckDNA checkDNAService) {
        this.checkDNAService = checkDNAService;
    }

    @PostMapping("/mutant")
    @ResponseStatus(HttpStatus.OK)
    void checkMutant(@RequestBody final DNAModel dna) {

        //Checks that matrix must be square
        if(dna.getDna().length != dna.getDna()[0].length()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The matrix must be NxN");
        }

        //Validates if it's mutant
        if(!checkDNAService.isMutant(dna.getDna())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not a mutant");
        }
    }

}
