package com.vsoft.mysoftware.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mmenu;

@Repository
public interface MmenuRepository extends JpaRepository<Mmenu, String>{

	Page<Mmenu> findByKodeLike(Pageable pageable, String kode);
	Page<Mmenu> findByMenuLike(Pageable pageable, String uraian);
	
}
