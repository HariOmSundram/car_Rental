package com.carrental.user.service;

import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	List<String> loadAllLicenseImage();

	String storeLicenseImage(MultipartFile file);

	Resource loadLicenseImage(String fileName);

	void deleteLicenseImage(String fileName);

}
