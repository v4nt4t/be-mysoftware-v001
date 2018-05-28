package com.vsoft.mysoftware.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.service.projection.MmenuProj;

@Repository
public interface MmenuRepository extends JpaRepository<Mmenu, String>{

	Page<Mmenu> findByKodeLike(Pageable pageable, String kode);
	Page<Mmenu> findByMenuLike(Pageable pageable, String uraian);
	Optional<MmenuProj> findOneById(String id);
	
	<T> Collection<T> findAllProjectedBy(Class<T> type);
	<T> Page<T> findPagedProjectedBy(Pageable pageable, Class<T> type);
	<T> Page<T> findByKodeLike(Pageable pageable, String kode, Class<T> type);
	<T> Page<T> findByMenuLike(Pageable pageable, String uraian, Class<T> type);
	
	
}
