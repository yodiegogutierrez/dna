/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */

package com.project.starter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String condition;

    public Stats(String condition) {
        this.condition = condition;
    }
}
