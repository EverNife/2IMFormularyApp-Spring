package br.com.finalcraft.im2.datamodel.data;

import br.com.finalcraft.im2.forms.validation.CreateFormularyForm;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "formularies")
public class Formulary implements Serializable {

    @Id
    private String id;

    private String nome;

    @Size(max = 15)
    private String whatsapp;

    private int front_end_experience_level;
    private int front_end_experience_years;

    private int back_end_experience_level;
    private int back_end_experience_years;

    private int database_experience_level;
    private int database_experience_years;

    private boolean camunda_experiece;
    private boolean healthcare_experiece;

    @Size(max = 1000)
    private String comment;

    public Formulary(CreateFormularyForm formularyForm) {
        this.id = UUID.randomUUID().toString();
        this.nome = formularyForm.getNome();
        this.whatsapp = formularyForm.getWhatsapp();
        this.front_end_experience_level = formularyForm.getFront_end_experience_level();
        this.front_end_experience_years = formularyForm.getFront_end_experience_years();
        this.back_end_experience_level = formularyForm.getBack_end_experience_level();
        this.back_end_experience_years = formularyForm.getBack_end_experience_years();
        this.database_experience_level = formularyForm.getDatabase_experience_level();
        this.database_experience_years = formularyForm.getDatabase_experience_years();
        this.camunda_experiece = formularyForm.getCamunda_experiece();
        this.healthcare_experiece = formularyForm.getHealthcare_experiece();
        this.comment = formularyForm.getComment();
    }

}
