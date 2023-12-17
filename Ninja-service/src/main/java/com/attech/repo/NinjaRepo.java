package com.attech.repo;


import com.attech.entity.Ninja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NinjaRepo extends JpaRepository<Ninja, Long> {
}
