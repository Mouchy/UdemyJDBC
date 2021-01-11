package com.mycompany.tennis.core.dto;

public class EpreuveFullDto {
	
	private Long      	id;
	private short     	annee;
	private TournoiDto  tournoi;
	private Character 	typeEpreuve;
	
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
	
	public Character getTypeEpreuve() {
		return typeEpreuve;
	}
	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}
	public TournoiDto getTournoi() {
		return tournoi;
	}
	public void setTournoi(TournoiDto tournoi) {
		this.tournoi = tournoi;
	}
}
