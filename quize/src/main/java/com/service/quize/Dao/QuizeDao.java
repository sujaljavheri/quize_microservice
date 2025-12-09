package com.service.quize.Dao;

import com.service.quize.Model.Quize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizeDao extends JpaRepository<Quize,Integer> {
    Quize findByTitle(String title);
}
