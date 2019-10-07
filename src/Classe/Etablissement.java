/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author mst
 */
@Entity
public class Etablissement {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String type;
    private String region;
    private String poursuite;
    private String codeEtablissement;
    private String telephone;
    // Etablissement
    public Etablissement() {
    }

    public Etablissement(String nom, String type, String region, String poursuite, String codeEtablissement, String telephone) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.poursuite = poursuite;
        this.codeEtablissement = codeEtablissement;
        this.telephone = telephone;
    }

    public Etablissement(int id, String nom, String type, String region, String poursuite, String village, String codeEtablissement, String telephone) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.poursuite = poursuite;
        this.codeEtablissement = codeEtablissement;
        this.telephone = telephone;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPoursuite() {
        return poursuite;
    }

    public void setPoursuite(String poursuite) {
        this.poursuite = poursuite;
    }

    @Override
    public String toString() {
        return  nom ;
    }
    
    public String getCodeEtablissement() {
        return codeEtablissement;
    }

    public void setCodeEtablissement(String codeEtablissement) {
        this.codeEtablissement = codeEtablissement;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
