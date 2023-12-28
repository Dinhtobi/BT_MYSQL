package com.assignment.java.Service;

import org.springframework.web.multipart.MultipartFile;

import com.assignment.java.DTO.Payload.Response.FileResponse;

public interface IFileService {

	FileResponse importFile(MultipartFile file, String name);
}
