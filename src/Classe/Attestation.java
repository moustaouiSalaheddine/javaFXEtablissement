/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author mst
 */
@Entity
public class Attestation {
    @EmbeddedId
    private AttestationPK idAttestation;
    private int numero;
    @JoinColumn(name = "idEmploye",updatable = false,insertable = false)
    @ManyToOne
    private Employe employe;
    @JoinColumn(name = "idEtudiant",updatable = false,insertable = false)
    @ManyToOne
    private Etudiant etudiant;

    public Attestation() {
    }

    
    public Attestation(AttestationPK idAttestation, int numero, Employe employe, Etudiant etudiant) {
        this.idAttestation = idAttestation;
        this.numero = numero;
        this.employe = employe;
        this.etudiant = etudiant;
    }

    public Attestation(int numero, Employe employe, Etudiant etudiant) {
        this.numero = numero;
        this.employe = employe;
        this.etudiant = etudiant;
    }

    
    public AttestationPK getIdAttestation() {
        return idAttestation;
    }

    public void setIdAttestation(AttestationPK idAttestation) {
        this.idAttestation = idAttestation;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
        
}
