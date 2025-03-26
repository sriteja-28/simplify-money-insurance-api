package com.insurance.service;

import com.insurance.model.Insurance;
import com.insurance.repository.InsuranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InsuranceServiceTest {

    @Mock
    private InsuranceRepository repository; // Mockito will create this mock

    @InjectMocks
    private InsuranceService service; // Mockito will inject the mock into this service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks and inject them
    }

    @Test
    void testGetAllInsurances() {
        when(repository.findAll())
                .thenReturn(List.of(new Insurance(1L, "Health Insurance", "Health", 5000)));
        assertEquals(1, service.getAllInsurances().size());
        verify(repository, times(1)).findAll();
    }
}
