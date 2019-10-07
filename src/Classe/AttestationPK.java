/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mst
 */
@Embeddable
public class AttestationPK implements Serializable{
    @Temporal(TemporalType.DATE)
    private Date dateEdition;
    private int idEtudiant;
    private int idEmploye;

    public AttestationPK() {
    }

    public AttestationPK(Date dateEdition, int idEtudiant, int idEmploye) {
        this.dateEdition = dateEdition;
        this.idEtudiant = idEtudiant;
        this.idEmploye = idEmploye;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    
}
