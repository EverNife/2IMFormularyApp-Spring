package br.com.finalcraft.im2.datamodel.managers;

import br.com.finalcraft.im2.datamodel.data.Formulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormularyRepository extends JpaRepository<Formulary, String> {

}