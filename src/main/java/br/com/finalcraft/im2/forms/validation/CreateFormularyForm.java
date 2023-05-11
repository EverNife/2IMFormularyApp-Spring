package br.com.finalcraft.im2.forms.validation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFormularyForm { //The same as Formulary, but without ID

    private @NotNull String nome;
    private @NotNull String whatsapp;

    private @NotNull Integer front_end_experience_level;
    private @NotNull Integer front_end_experience_years;

    private @NotNull Integer back_end_experience_level;
    private @NotNull Integer back_end_experience_years;

    private @NotNull Integer database_experience_level;
    private @NotNull Integer database_experience_years;

    private @NotNull Boolean camunda_experiece;
    private @NotNull Boolean healthcare_experiece;

    private @NotNull String comment;
}
