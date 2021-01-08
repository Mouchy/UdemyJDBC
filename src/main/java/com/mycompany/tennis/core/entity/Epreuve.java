package com.mycompany.tennis.core.entity;

import javax.persistence.Column;

import org.hibernate.annotations.Type;

public class Epreuve {
	private Long      id;
	@Type(type="short")
	private short     annee;
	private Tournoi   tournoi;
	@Column(name="TYPE_EPREUVE", nullable = true, length=2000)
	private Character typeEpreuve;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public short getAnnee() {
		return annee;
	}
	public void setAnnee(short annee) {
		this.annee = annee;
	}
	public Tournoi getTournoi() {
		return tournoi;
	}
	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
	public Character getTypeEpreuve() {
		return typeEpreuve;
	}
	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}
	
	
}
