package com.insurance.service;

import com.insurance.model.Insurance;
import com.insurance.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository repository;
    
    void setRepository(InsuranceRepository repository) {
        this.repository = repository;
    }

    public List<Insurance> getAllInsurances() {
        return repository.findAll();
    }

    public Insurance purchaseInsurance(Long id) {
        return repository.findById(id).orElse(null);
    }

    // This method uses getType() on each Insurance object.
    public List<Insurance> getCuratedInsurances(int age, String gender, double income) {
        return repository.findAll().stream()
                .filter(insurance ->
                        (age > 30 && insurance.getType().equalsIgnoreCase("Life"))
                        || (income > 50000 && insurance.getType().equalsIgnoreCase("Premium")))
                .toList();
    }
}
