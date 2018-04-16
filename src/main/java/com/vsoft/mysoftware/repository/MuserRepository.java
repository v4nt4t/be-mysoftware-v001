package com.vsoft.mysoftware.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsoft.mysoftware.domain.Muser;

@Repository
public interface MuserRepository extends JpaRepository<Muser, String>{

	 Optional<Muser> findOneByLogin(String login);
}
