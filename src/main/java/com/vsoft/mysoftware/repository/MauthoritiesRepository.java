package com.vsoft.mysoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mauthority;

@Repository
public interface MauthoritiesRepository extends JpaRepository<Mauthority, String> {

}
