package me.lukasz.presistance.repos;

import me.lukasz.presistance.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
}
