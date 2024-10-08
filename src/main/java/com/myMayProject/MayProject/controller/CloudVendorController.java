package com.myMayProject.MayProject.controller;

import com.myMayProject.MayProject.model.CloudVendor;
import com.myMayProject.MayProject.response.ResponseHandler;
import com.myMayProject.MayProject.service.CloudVendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/cloudvendor")
public class CloudVendorController {

    @Autowired
    CloudVendorService cloudVendorservice;

    public CloudVendorController(CloudVendorService cloudVendorservice) {
        this.cloudVendorservice = cloudVendorservice;
    }

    private final Logger LOGGER =
            LoggerFactory.getLogger(CloudVendorController.class);

    @GetMapping("/cloudVendor/{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        return ResponseHandler.responseBuilder("Requested Vendor details are given here", HttpStatus.OK,cloudVendorservice.getCloudVendorDetails(vendorId));
        //new CloudVendor("c1", "Vendor 1", "address 1", "1234");
    }

    @PostMapping("/cloudVendor")
    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorservice.createCloudVendorDetails(cloudVendor);
        return ("Cloud Vendor Details Successfully created");
    }
    @PutMapping("/cloudVendor")
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorservice.updateCloudVendorDetails(cloudVendor);
        return ("Cloud Vendor Details Successfully updated");
    }

    @DeleteMapping("/cloudVendor/{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        cloudVendorservice.deleteCloudVendorDetails(vendorId);
        return ("Cloud Vendor Details Deleted Successfully");
    }

    @GetMapping("/cloudVendor")
    public List<CloudVendor> getAllCloudVendorDetails(){
        return cloudVendorservice.getAllCloudVendorDetails();
    }
}
