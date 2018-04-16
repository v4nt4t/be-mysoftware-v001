package com.vsoft.mysoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mheadermenu;


@Repository
public interface MheadermenuRepository extends JpaRepository<Mheadermenu, String>{
	
}
