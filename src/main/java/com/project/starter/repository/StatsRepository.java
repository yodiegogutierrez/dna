/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */

package com.project.starter.repository;

import com.project.starter.model.Stats;
import org.springframework.data.repository.CrudRepository;

public interface StatsRepository extends CrudRepository<Stats, Integer> {}