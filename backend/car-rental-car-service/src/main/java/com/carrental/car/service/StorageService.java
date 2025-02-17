package com.carrental.car.service;

import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	List<String> loadAllVariantImage();

	String storeVariantImage(MultipartFile file);

	Resource loadVariantImage(String fileName);

	void deleteVariantImage(String fileName);

}
