package com.vsoft.mysoftware.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Muser;

@Repository
public interface MuserRepository extends JpaRepository<Muser, String>{

	 Optional<Muser> findOneByLogin(String login);
	
	 <T> Optional<T> findOneById(String id, Class<T> type);
	 <T> Optional<T> findOneByLogin(String login, Class<T> type);
	 <T> Page<T> findPagedProjectedBy(Pageable pageable, Class<T> type);
	 <T> Page<T> findByLoginLike(Pageable pageable, String kode, Class<T> type);
	 <T> Page<T> findByFirstNameLike(Pageable pageable, String fn, Class<T> type);
}
