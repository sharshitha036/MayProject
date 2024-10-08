package com.myMayProject.MayProject.service;

import com.myMayProject.MayProject.exception.CloudVendorNotFoundException;
import com.myMayProject.MayProject.model.CloudVendor;
import com.myMayProject.MayProject.repository.CloudVendorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CloudVendorImplTest {
    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("1", "Amazon",
                "USA", "xxxxx");
    }

    @AfterEach
    void tearDown()throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetCloudVendorDetails_exceptionThrown(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

       // CloudVendor c = Mockito.mock(CloudVendor.class);


        when(cloudVendorRepository.findById("1")).thenReturn(Optional.empty()); //setup
        //execution
        Throwable exception = assertThrows(CloudVendorNotFoundException.class, () -> cloudVendorService.getCloudVendorDetails("1"));
        //asserts
        assertEquals("Requested cloud vendor doesn't exist", exception.getMessage());
    }
    @Test
    void testGetCloudVendorDetails(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendorDetails("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testCreateCloudVendorDetails() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendorDetails(cloudVendor)).isEqualTo("Successfully Created");
    }



    @Test
    void testUpdateCloudVendorDetails() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendorDetails(cloudVendor)).isEqualTo("Successfully Updated");
    }

    @Test
    void testDeleteCloudVendorDetails() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepository)
                .deleteById(any());
        assertThat(cloudVendorService.deleteCloudVendorDetails("1")).isEqualTo("Deleted");
    }

    @Test
    void testGetAllCloudVendorDetails() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findAll())
                .thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorService.getAllCloudVendorDetails().get(0).getVendorPhoneNumber()).isEqualTo(cloudVendor.getVendorPhoneNumber());
    }

    @Test
    void testGetByVendorName(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        assertThat(cloudVendorService.getByVendorName("Amazon").get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());

    }
}