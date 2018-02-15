package com.ipiecoles.java.eval2x0.repository;

import com.ipiecoles.java.eval2x0.model.Employe;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaseEmployeRepository<T extends Employe> extends PagingAndSortingRepository<T, Long> {
    /**
     * Méthode qui cherche un employé selon son matricule
     * @param matricule
     * @return l'employé de matricule correspondant, null sinon
     */
    T findByMatricule(String matricule);

    /**
     * Méthode qui cherche les employés ayant comme nom ou prénom le paramètre sans prendre en compte la casse.
     * @param nomOuPrenom
     * @return les employés satisfaisant la condition de recherche, une liste vide sinon
     */
    @Query("select e from #{#entityName} e where lower(e.prenom) = lower(:nomOuPrenom) or lower(e.nom) = lower(:nomOuPrenom)")
    List<T> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOuPrenom);

    /**
     * Méthode qui cherche les employés gagnant ayant un salaire inférieur au paramètre
     * @param salaire
     * @return les employés satisfaisant la condition de recherche, une liste vide sinon
     */
    List<Employe> findBySalaireLessThan(Double salaire);
}
