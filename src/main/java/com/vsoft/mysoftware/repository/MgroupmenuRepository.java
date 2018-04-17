package com.vsoft.mysoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mgroupmenu;

@Repository
public interface MgroupmenuRepository extends JpaRepository<Mgroupmenu, String> {

}
