package com.ipiecoles.java.eval2x0.service;

import com.ipiecoles.java.eval2x0.model.Employe;
import com.ipiecoles.java.eval2x0.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    public Employe findById(Long id){
        return employeRepository.findOne(id);
    }

    public Long countAllEmploye() {
        return employeRepository.count();
    }

    public void deleteEmploye(Long id){
        employeRepository.delete(id);
    }

    public Employe creerEmploye(Employe e) {
        return employeRepository.save(e);
    }

    public Employe findByMatricule(String matricule) {
        return employeRepository.findByMatricule(matricule);
    }

    public List<Employe> findBySalaire(Double Salaire) {
        return employeRepository.findBySalaireLessThan(Salaire);
    }

    public List<Employe> findByNomOrPrenom(String nomOuPrenom) {
        return employeRepository.findByNomOrPrenomAllIgnoreCase(nomOuPrenom);
    }

    public Page<Employe> findAllEmployes(Integer page, Integer size, String sortProperty, String sortDirection) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.fromString(sortDirection),sortProperty));
        Pageable pageable = new PageRequest(page,size,sort);
        return employeRepository.findAll(pageable);
    }

    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

}
