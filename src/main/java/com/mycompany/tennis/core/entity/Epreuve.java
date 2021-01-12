package com.mycompany.tennis.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "EPREUVE")
public class Epreuve {
	@Id
	private Long      id;
	@Type(type="short")
	private short     annee;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TOURNOI")
	private Tournoi   tournoi;
	@Column(name="TYPE_EPREUVE", nullable = true, length=2000)
	private Character typeEpreuve;
	
	@ManyToMany
	@JoinTable(
			name="PARTICIPANTS",
			joinColumns = {@JoinColumn(name="ID_EPREUVE")},
			inverseJoinColumns = {@JoinColumn(name="ID_JOUEUR")}
		)
	private Set<Joueur> participants;
	
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
	public Set<Joueur> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<Joueur> participants) {
		this.participants = participants;
	}
}
