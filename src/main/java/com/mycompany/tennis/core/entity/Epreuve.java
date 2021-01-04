package com.mycompany.tennis.core.entity;

public class Epreuve {
	private Long      id;
	private short     annee;
	private Tournoi   tournoi;
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
