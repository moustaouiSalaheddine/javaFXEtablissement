/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author mst
 */
@Entity
public class Etudiant {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String codeNational;
    private String niveauEtude; 
    private String decisionConseil;
    private int numeroDossier;
    private String lieuNaissane;
    private String numeroInscription;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    @ManyToOne
    private Etablissement etablissement;

    public Etudiant() {
    }

    public Etudiant(String nom, Date dateNaissance, String codeNational, String niveauEtude, String decisionConseil, int numeroDossier, String lieuNaissane, Date dateFin, String numeroInscription, Etablissement etablissement) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.codeNational = codeNational;
        this.niveauEtude = niveauEtude;
        this.decisionConseil = decisionConseil;
        this.numeroDossier = numeroDossier;
        this.lieuNaissane = lieuNaissane;
        this.dateFin = dateFin;
        this.etablissement = etablissement;
        this.numeroInscription = numeroInscription;
    }


    public Etudiant(String nom, Date dateNaissance, String codeNational, String niveauEtude, Etablissement etablissement) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.codeNational = codeNational;
        this.niveauEtude = niveauEtude;
        this.etablissement = etablissement;
    }

//    public Etudiant(int id, String nom, Date dateNaissance, String codeNational, String niveauEtude, String decisionConseil) {
//        this.id = id;
//        this.nom = nom;
//        this.dateNaissance = dateNaissance;
//        this.codeNational = codeNational;
//        this.niveauEtude = niveauEtude;
//        this.decisionConseil = decisionConseil;
//    }

    public Etudiant(int id, String nom, Date dateNaissance, String codeNational, String niveauEtude, String decisionConseil, int numeroDossier, String lieuNaissane, Date dateFin, String numeroInscription, Etablissement etablissement) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.codeNational = codeNational;
        this.niveauEtude = niveauEtude;
        this.decisionConseil = decisionConseil;
        this.numeroDossier = numeroDossier;
        this.lieuNaissane = lieuNaissane;
        this.dateFin = dateFin;
        this.etablissement = etablissement;
        this.numeroInscription = numeroInscription;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodeNational() {
        return codeNational;
    }

    public void setCodeNational(String codeNational) {
        this.codeNational = codeNational;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public String getDecisionConseil() {
        return decisionConseil;
    }

    public void setDecisionConseil(String decisionConseil) {
        this.decisionConseil = decisionConseil;
    }

    public int getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(int numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getLieuNaissane() {
        return lieuNaissane;
    }

    public void setLieuNaissane(String lieuNaissane) {
        this.lieuNaissane = lieuNaissane;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNumeroInscription() {
        return numeroInscription;
    }

    public void setNumeroInscription(String numeroInscription) {
        this.numeroInscription = numeroInscription;
    }
    
}
