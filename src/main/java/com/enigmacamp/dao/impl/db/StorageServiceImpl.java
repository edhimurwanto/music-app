package com.enigmacamp.dao.impl.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.enigmacamp.dao.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	@Value("${user.home}${app.upload.dir:${user.home}}")
	public String uploadDir;

	@Override
	public String store(MultipartFile file, String id) throws FileNotFoundException {
		try {
			
			String extension = "";

			int i = file.getOriginalFilename().lastIndexOf('.');
			if (i >= 0) {
			    extension = file.getOriginalFilename().substring(i);
			}

			Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(id+extension));

			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return copyLocation.toString();
		} catch (Exception e) {
			throw new FileNotFoundException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
		}

	}

	@Override
	public String load(String filename) {
		return uploadDir + "/" + filename;
	}

	@Override
	public Resource loadAsResource(String id) throws MalformedURLException {
		Path path = Paths.get(id);
		Resource resource = null;

		resource = new UrlResource(path.toUri());

		return resource;
	}

}
