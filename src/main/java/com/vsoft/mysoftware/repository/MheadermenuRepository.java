package com.vsoft.mysoftware.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vsoft.mysoftware.domain.Mheadermenu;


@Repository
public interface MheadermenuRepository extends JpaRepository<Mheadermenu, String>{
	
	Page<Mheadermenu> findByKodeLike(Pageable pageable, String kode);
	Page<Mheadermenu> findByHeadermenuLike(Pageable pageable, String uraian);
	
//	Dynamic projections
//	<T> Page<T> findByKodeLike(Pageable pageable, String kode, Class<T> type);
//	<T> Page<T> findByHeadermenuLike(Pageable pageable, String uraian, Class<T> type);
//	<T> Collection<T> findAllProjectedBy(Class<T> type);
	
//	@Query("select h.id as id, h.kode as kode, h.headermenu as headermenu from Mheadermenu h")
//	List<MheadermenuProj> findAllOnlyIdKodeAndUraian();

	
}
