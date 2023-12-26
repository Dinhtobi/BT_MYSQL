package com.assignment.java.Controller;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.java.Service.IJobService;
import com.assignment.java.Service.Impl.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private IJobService jobService;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/import")
	public ResponseEntity<?> importFile(@RequestParam("url") String url) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		jobService.importFile(url);
		return ResponseEntity.ok("Run batch import and export");
	}
	
	
	@GetMapping("/export")
	public ResponseEntity<?> exportFile() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		jobService.exportFile();
		return ResponseEntity.ok("Run batch import and export");
	}
	
	
	@PostMapping
	public ResponseEntity<?> storageFile(@RequestParam("file") MultipartFile file , @RequestParam("name") String name){
		return ResponseEntity.ok(fileService.importFile(file, name));
	}
}
