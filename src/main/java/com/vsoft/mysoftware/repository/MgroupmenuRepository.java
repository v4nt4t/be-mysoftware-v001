package com.vsoft.mysoftware.repository;



import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Mgroupmenu;

@Repository
public interface MgroupmenuRepository extends JpaRepository<Mgroupmenu, String> {
	
	Page<Mgroupmenu> findByKodeLike(Pageable pageable, String kode);
	Page<Mgroupmenu> findByGroupmenuLike(Pageable pageable, String uraian);
	
//	Dynamic projections
	<T> Collection<T> findAllProjectedBy(Class<T> type);
	<T> Page<T> findPagedProjectedBy(Pageable pageable, Class<T> type);
	<T> Page<T> findByKodeLike(Pageable pageable, String kode, Class<T> type);
	<T> Page<T> findByGroupmenuLike(Pageable pageable, String uraian, Class<T> type);
	<T> Optional<T> findOneById(String id, Class<T> type);

}
