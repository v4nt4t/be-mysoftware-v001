package com.vsoft.mysoftware.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mmenu;

@Repository
public interface MmenuRepository extends JpaRepository<Mmenu, String>{

	Page<Mmenu> findByKodeLike(Pageable pageable, String kode);
	Page<Mmenu> findByMenuLike(Pageable pageable, String uraian);
	
	<T> Collection<T> findAllProjectedBy(Class<T> type);
	<T> Page<T> findPagedProjectedBy(Pageable pageable, Class<T> type);
	<T> Page<T> findByKodeLike(Pageable pageable, String kode, Class<T> type);
	<T> Page<T> findByMenuLike(Pageable pageable, String uraian, Class<T> type);

	
}
