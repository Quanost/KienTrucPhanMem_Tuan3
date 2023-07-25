package com.example.DemoCRUD_SpringBoot.repository;



import com.example.DemoCRUD_SpringBoot.model.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRepository extends JpaRepository<KhoaHoc,Integer> {
    List<KhoaHoc> findByName(String name);
}
