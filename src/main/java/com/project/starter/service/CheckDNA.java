/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */

package com.project.starter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckDNA {

    // Possible values to travel for each character
    enum Position {
        RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_MIDDLE,
        BOTTOM_LEFT
    }

    // As far as a recursive method takes back a value from a certain instance, I will decide
    // to use class variables.
    private static Integer totalOfCoincidences;
    private static Integer counter;

    public boolean isMutant(String[] dna) {
        counter = 0;
        totalOfCoincidences = 0;

        // Validate each character of the array and its neighbours
        for( int x=0; x<dna.length; x++ ) {
            for( int y=0; y < dna[x].length(); y++ ) {
                comparing(x, y, dna, Position.RIGHT);
            }
        }

        return totalOfCoincidences > 1;
    }

    /**
     * Recursive method to travel around the DNA map and checks if there's a mutation.
     * 4 characters in a row will be content as a coincidence (Oblique, Horizontal, Vertical)
     * @param x
     * @param y
     * @param dna
     * @param currentPosition
     */
    private void comparing(
            Integer x,
            Integer y,
            String[] dna,
            Position currentPosition) {

        // Validate array bounds and take valid values
        Character current = dna[x].charAt(y);
        Character right =  x >= 0 && y + 1 >= 0 && x < dna.length && y+1 < dna.length ? dna[x].charAt(y + 1) : 'X';
        Character bottomRight = x + 1 >= 0 && y + 1 >= 0 && x + 1 < dna.length && y + 1 < dna.length ? dna[x + 1].charAt(y + 1) : 'X';
        Character bottomMiddle =  x + 1 >= 0 && y >= 0 && x + 1 < dna.length && y < dna.length ? dna[x + 1].charAt(y) : 'X';
        Character bottomLeft = x + 1 >= 0 && y - 1 >= 0 && x + 1 < dna.length && y - 1 < dna.length ? dna[x + 1].charAt(y - 1) : 'X';

        if(currentPosition.equals(Position.RIGHT)) {
            checkNeighbour(x, y+1, current, right, dna, Position.RIGHT); //Check right first
            checkNeighbour(x+1, y+1, current, bottomRight, dna, Position.BOTTOM_RIGHT); //Check bottomRight
            checkNeighbour(x+1, y, current, bottomMiddle, dna, Position.BOTTOM_MIDDLE); //Check bottomMiddle
            checkNeighbour(x+1, y-1, current, bottomLeft, dna, Position.BOTTOM_LEFT); //Check right first
        }

        if(currentPosition.equals(Position.BOTTOM_RIGHT)) {
            checkNeighbour(x+1, y+1, current, bottomRight, dna, Position.BOTTOM_RIGHT); //Check bottomRight first
            checkNeighbour(x, y+1, current, right, dna, Position.RIGHT); //Check right
            checkNeighbour(x+1, y, current, bottomMiddle, dna, Position.BOTTOM_MIDDLE); //Check bottomMiddle
            checkNeighbour(x+1, y-1, current, bottomLeft, dna, Position.BOTTOM_LEFT); //Check right first
        }

        if(currentPosition.equals(Position.BOTTOM_MIDDLE)) {
            checkNeighbour(x+1, y, current, bottomMiddle, dna, Position.BOTTOM_MIDDLE); //Check bottomMiddle first
            checkNeighbour(x+1, y+1, current, bottomRight, dna, Position.BOTTOM_RIGHT); //Check bottomRight
            checkNeighbour(x, y+1, current, right, dna, Position.RIGHT); //Check right
            checkNeighbour(x+1, y-1, current, bottomLeft, dna, Position.BOTTOM_LEFT); //Check right first
        }

        if(currentPosition.equals(Position.BOTTOM_LEFT)) {
            checkNeighbour(x+1, y-1, current, bottomLeft, dna, Position.BOTTOM_LEFT); //Check right first
            checkNeighbour(x+1, y, current, bottomMiddle, dna, Position.BOTTOM_MIDDLE); //Check bottomMiddle first
            checkNeighbour(x+1, y+1, current, bottomRight, dna, Position.BOTTOM_RIGHT); //Check bottomRight
            checkNeighbour(x, y+1, current, right, dna, Position.RIGHT); //Check right
        }

    }

    /**
     * Checks if neighbour's value is equals than current.
     * Increments the counter of characters in a row
     * Increments the coincidences of mutant DNA according to counter's value
     * @param x
     * @param y
     * @param current
     * @param neighbour
     * @param dna
     * @param currentPosition
     */
    private void checkNeighbour(
            int x,
            int y,
            Character current,
            Character neighbour,
            String[] dna,
            Position currentPosition) {

        if(current == neighbour) {
            counter++;
            totalOfCoincidences = counter >= 3 ? totalOfCoincidences + 1 : totalOfCoincidences;
            comparing(x, y, dna, currentPosition);
        } else {
            counter = 0; // If there's not a coincidence, the counter will be restarted.
        }
    }

}
