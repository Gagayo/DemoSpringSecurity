package sn.htg.demosecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.htg.demosecurity.entities.Utilisateurs;


public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long>{

	public Utilisateurs findByUsername(String username);
}
