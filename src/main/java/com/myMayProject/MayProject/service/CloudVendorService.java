package com.myMayProject.MayProject.service;

import com.myMayProject.MayProject.model.CloudVendor;
import org.springframework.stereotype.Service;

import java.util.*;

public interface CloudVendorService {

    public CloudVendor getCloudVendorDetails(String vendorId);
    public String createCloudVendorDetails(CloudVendor cloudVendor);
    public String updateCloudVendorDetails(CloudVendor cloudVendor);
    public String deleteCloudVendorDetails(String vendorId);
    public List<CloudVendor> getAllCloudVendorDetails();
    public List<CloudVendor> getByVendorName(String vendorName);

}
