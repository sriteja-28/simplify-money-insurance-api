package com.insurance.controller;

import com.insurance.model.Insurance;
import com.insurance.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService service;

    // API to list all insurances
    @GetMapping
    public List<Insurance> getAllInsurances() {
        return service.getAllInsurances();
    }

    // API to purchase an insurance (assume payment is successful)
    @PostMapping("/purchase/{id}")
    public ResponseEntity<String> purchaseInsurance(@PathVariable Long id) {
        Insurance insurance = service.purchaseInsurance(id);
        if (insurance != null) {
            return ResponseEntity.ok("Purchase successful for insurance: " + insurance.getName());
        }
        return ResponseEntity.badRequest().body("Insurance not found");
    }

    // API to download policy document (dummy PDF file)
    @GetMapping("/download-policy")
    public ResponseEntity<byte[]> downloadPolicy() throws IOException {
        Resource resource = new ClassPathResource("dummy-policy.pdf");
        byte[] pdfData = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dummy-policy.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }

    // Brownie Points: Get curated insurance recommendations
    @GetMapping("/curated")
    public List<Insurance> getCurated(@RequestParam int age,
                                      @RequestParam String gender,
                                      @RequestParam double income) {
        return service.getCuratedInsurances(age, gender, income);
    }
}
