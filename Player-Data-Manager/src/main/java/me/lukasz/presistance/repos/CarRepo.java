package me.lukasz.presistance.repos;

import me.lukasz.presistance.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer>
{
}
