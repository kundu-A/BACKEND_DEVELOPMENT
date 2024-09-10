package com.arpan.StudentData.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.arpan.StudentData.models.Students;
import com.arpan.StudentData.service.StudentDataService;
import com.itextpdf.text.DocumentException;

import jakarta.validation.Valid;

@Controller
public class StudentDataController {

	@Autowired
	StudentDataService studentDataService;
	
    @GetMapping("/information")
    public ModelAndView getStudentData() {
        String viewName = "StudentInterface";
        Map<String, Object> model = new HashMap<>();
        model.put("studentItem", new Students());
        return new ModelAndView(viewName, model);
    }
    
    @PostMapping("/information")
    public ModelAndView putStudentData(@Valid @ModelAttribute("studentItem") Students students , BindingResult bindingResult , @RequestPart("file") MultipartFile file){
    	if(bindingResult.hasErrors()) {
    		return new ModelAndView("StudentInterface");
    	}
    	
    	try {
    	studentDataService.create(students,file);
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	RedirectView rd=new RedirectView();
    	rd.setUrl("/information");
    	return new ModelAndView(rd);
    }
    
    @GetMapping("/details")
    public ModelAndView displayStudentDetails() {
    	String viewName="StudentDetails";
		Map<String , Object> model=new HashMap<>();
		model.put("students", studentDataService.getStudents());
		return new ModelAndView(viewName,model);
    }
    
    @GetMapping("/generatepdf")
	public ResponseEntity<byte[]> createPdf(Students student) throws Exception{

		try {
            byte[] pdfContents = studentDataService.createPdf(student.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+student.getId()+".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);

        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/downloadpdf/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id) {
        Students fileEntity = studentDataService.getFile(id);
        return ResponseEntity.ok()
        		.contentType(MediaType.parseMediaType(fileEntity.getFileType()))
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
        		.body(new ByteArrayResource(fileEntity.getResume()));
    }
}
