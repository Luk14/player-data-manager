package me.lukasz.presistance.repos;

import me.lukasz.presistance.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepo extends JpaRepository<Statistics, Integer>
{
}
