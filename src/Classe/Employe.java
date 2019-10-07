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
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Sinponzakra
 */
@Entity
@Table(
        name="EMPLOYE", 
        uniqueConstraints=
            @UniqueConstraint(columnNames = {"username"})
    )
public class Employe {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String telephone;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String cin;
    @ManyToOne
    private Profil profil;
    private String username;
    private String password;
    @ManyToOne
    private Etablissement etablissement;

    public Employe() {
    }

    public Employe(int id, String nom, String prenom, String sexe, String telephone, Date dateNaissance, String cin, Profil profil, String username, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.profil = profil;
        this.username = username;
        this.password = password;
    }

    public Employe(String nom, String prenom, String sexe, String telephone, Date dateNaissance, String cin, Profil profil, String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.profil = profil;
        this.username = username;
        this.password = password;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
    
}
