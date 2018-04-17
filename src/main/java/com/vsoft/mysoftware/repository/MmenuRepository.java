package com.vsoft.mysoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mmenu;

@Repository
public interface MmenuRepository extends JpaRepository<Mmenu, String>{

}
