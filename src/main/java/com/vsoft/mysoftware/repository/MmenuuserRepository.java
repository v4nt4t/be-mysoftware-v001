package com.vsoft.mysoftware.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vsoft.mysoftware.domain.Mmenuuser;
import com.vsoft.mysoftware.service.projection.MmenuuserForSideBar;

public interface MmenuuserRepository extends JpaRepository<Mmenuuser, String> {

	Page<Mmenuuser> findByMuserId(Pageable page, String id);
	Page<Mmenuuser> findByMuserLogin(Pageable page, String login);
	
//	Collection<MmenuuserForSideBar> findByMuserId(String id);
	
	<T> Page<T> findByMuserId(Pageable page, String id, Class<T> type);
	<T> Optional<T> findOneById(String id, Class<T> type);
	
	@Query(value="SELECT m.id as mmenuId,"+ 
					   "m.menu as mmenuMenu,"+
					   "m.objek as mmenuObjek,"+
					   "m.urutan as mmenuUrutan,"+
					   "g.id as mgroupmenuId,"+
					   "g.groupmenu as mgroupmenuGroupmenu,"+
					   "g.urutan as mgroupmenuUrutan,"+
					   "h.id as mheadermenuId,"+
					   "h.headermenu as mheadermenuHeadermenu,"+
					   "h.urutan as mheadermenuUrutan "+
				"FROM mmenuuser mu "+
				"JOIN muser u ON mu.muser_id = u.id "+
				"JOIN mmenu m ON mu.mmenu_id = m.id "+
				"JOIN mgroupmenu g ON m.mgroupmenu_id = g.id "+
				"JOIN mheadermenu h ON g.mheadermenu_id = h.id "+
				"WHERE u.id = ?1 "+
				"ORDER BY h.id, g.id, m.id"
			, nativeQuery = true)
	Collection<MmenuuserForSideBar> FindForSideBarByMuserId(String id);
}
