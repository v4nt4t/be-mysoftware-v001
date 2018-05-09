package com.vsoft.mysoftware.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.service.projection.MmenuListProj;

@Repository
public interface MmenuRepository extends JpaRepository<Mmenu, String>{

	Page<Mmenu> findByKodeLike(Pageable pageable, String kode);
	Page<Mmenu> findByMenuLike(Pageable pageable, String uraian);
	
	<T> Collection<T> findAllProjectedBy(Class<T> type);
	<T> Page<T> findPagedProjectedBy(Pageable pageable, Class<T> type);
	
	@Query(value = "SELECT m.id as id, m.kode as kode, m.menu as menu, m.allowedf as allowedf, m.urutan as urutan, m.mgroupmenu as groupmenu  FROM Mmenu m ")
	Collection<MmenuListProj> findAllEager();
}
