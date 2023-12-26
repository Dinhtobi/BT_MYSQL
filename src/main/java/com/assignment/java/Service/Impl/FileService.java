package com.assignment.java.Service.Impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.java.Config.FileStorageConfig;
import com.assignment.java.Entities.File;
import com.assignment.java.Exception.BadRequestException;
import com.assignment.java.Payload.Response.FileResponse;
import com.assignment.java.Repository.FileRepository;
import com.assignment.java.Service.IFileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService implements IFileService {

	private final Path fileStorageLocation ;
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	public FileService(FileStorageConfig fileStorageConfig) {
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception e) {
			
		}
	}

	@Override
	public FileResponse importFile(MultipartFile file,String name) {
		String url = createUrl(file);
		File filesaved = new File();
		filesaved.setName(name);
		filesaved.setUrl(url);
		fileRepository.save(filesaved);
		
		return new FileResponse("Success", "File saved with url " + url);
	}

	private String createUrl(MultipartFile file) {
		String fileName = storeFile(file);
		return fileName;
	}
	
	private String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new BadRequestException("File contrains invalid path sequence" + fileName);
			}
			String dateTimeNowStr = LocalDateTime.now().toString().replace("-", "").replace(":", "").replace(".", "");
			fileName = dateTimeNowStr + "_" + fileName.trim().replace(" ","");
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			log.info("address {}", targetLocation);
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (Exception e) {
			throw new BadRequestException("Can't store file");
		}
	}
}
