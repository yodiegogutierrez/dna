/**
 * Mercadolibre's Exam 2021
 * Developed by @yodiegogutierrez
 */

package com.project.starter.service;

import com.project.starter.model.Stats;
import com.project.starter.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    private StatsRepository statsRepository;

    public List<Stats> getAllStats() {
        List<Stats> stats = new ArrayList<>();
        statsRepository.findAll().forEach(stat -> stats.add(stat));
        return stats;
    }

    public void saveOrUpdate(Stats stats) {
        statsRepository.save(stats);
    }

    public long getMutants() {
        return getAllStats().stream().filter(stat -> stat.getCondition().equals("mutant")).count();
    }

    public double getAverageMutants() {
        System.out.println(getMutants() + "  " + getAllStats().size());
        return  100.0 * getMutants() / getAllStats().size();
    }
}
