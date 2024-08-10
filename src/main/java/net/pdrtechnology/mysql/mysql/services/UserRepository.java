package net.pdrtechnology.mysql.mysql.services;

import org.springframework.data.jpa.repository.JpaRepository;
import net.pdrtechnology.mysql.mysql.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}
