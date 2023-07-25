package com.example.DemoCRUD_SpringBoot.repository;



import com.example.DemoCRUD_SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
