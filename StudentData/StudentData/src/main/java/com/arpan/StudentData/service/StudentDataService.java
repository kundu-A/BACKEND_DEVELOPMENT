package com.arpan.StudentData.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.arpan.StudentData.models.Students;
import com.arpan.StudentData.repository.StudentRepo;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class StudentDataService {

	@Autowired
	StudentRepo studentRepo;
	public void create(Students students , MultipartFile file) throws IOException {
		students.setResume(file.getBytes());
		students.setFileName(file.getOriginalFilename());
		students.setFileType(file.getContentType());
		studentRepo.save(students);
	}
	
	public List<Students> getStudents(){
		return studentRepo.findAll();
	}
	
	public byte[] createPdf(int id) throws Exception{
		Students studentWriter = studentRepo.findById(id).get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph("First name: "+studentWriter.getFirstName()));
            document.add(new Paragraph("Last Name: "+studentWriter.getLastName()));
            document.add(new Paragraph("Date of Birth: "+studentWriter.getDob()));
            document.add(new Paragraph("Email: "+studentWriter.getEmail()));
            document.add(new Paragraph("Roll Number: "+studentWriter.getRollNo().toString()));
            document.add(new Paragraph("Registration Number: "+studentWriter.getRegNo().toString()));
            document.add(new Paragraph("College Name: "+studentWriter.getCollegeName()));
            document.add(new Paragraph("Stream: "+studentWriter.getStream()));
            document.add(new Paragraph("Uploaded File Name: "+studentWriter.getFileName()));
            document.add(new Paragraph("Uploaded File Type: "+studentWriter.getFileType()));
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        finally {
            document.close();
        }
        return byteArrayOutputStream.toByteArray();
	}

	public Students getFile(int id) {
		return studentRepo.findById(id).get();
	}
}
