package com.myMayProject.MayProject.service;

import com.myMayProject.MayProject.exception.CloudVendorNotFoundException;
import com.myMayProject.MayProject.model.CloudVendor;
import com.myMayProject.MayProject.repository.CloudVendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorImpl implements CloudVendorService{


    CloudVendorRepository cloudVendorRepository;

    public CloudVendorImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public CloudVendor getCloudVendorDetails(String vendorId) {
        //cloudVendorRepository.findById(vendorId).get();
        if(cloudVendorRepository.findById(vendorId).isEmpty())
            throw new CloudVendorNotFoundException("Requested cloud vendor doesn't exist");
        return cloudVendorRepository.findById(vendorId).get();
    }

    @Override
    public String createCloudVendorDetails(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Successfully Created";
    }

    @Override
    public String updateCloudVendorDetails(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Successfully Updated";
    }

    @Override
    public String deleteCloudVendorDetails(String vendorId) {
        cloudVendorRepository.deleteById(vendorId);
        return "Deleted";
    }

    @Override
    public List<CloudVendor> getAllCloudVendorDetails() {
        return cloudVendorRepository.findAll();

    }
    @Override
    public List<CloudVendor> getByVendorName(String vendorName)
    {
        return cloudVendorRepository.findByVendorName(vendorName);
    }
}
