/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */

package com.project.starter.dna;

import com.project.starter.service.CheckDNA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DNATests {

    private static CheckDNA checkDNAService;

    @BeforeAll
    public static void init() {
        checkDNAService = new CheckDNA();
    }

    /**
     * This test include 3 coincidences, should be true
     */
    @Test
    public void shouldFindMutant(){
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertTrue(checkDNAService.isMutant(dna));
    }

    /**
     * This test include 1 coincidence, should be false
     */
    @Test
    public void shouldNotFindMutantOnlyOneCoincidence(){
        String[] dna = {"TTGCCA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertFalse(checkDNAService.isMutant(dna));
    }

    /**
     * This test doesn't include coincidences, should be false
     */
    @Test
    public void shouldNotFindMutantNoCoincidences(){
        String[] dna = {"TTGCCA","CAGTGC","TTATGT","AGAAGG","CCTCTA","TCACTG"};
        assertFalse(checkDNAService.isMutant(dna));
    }
}
