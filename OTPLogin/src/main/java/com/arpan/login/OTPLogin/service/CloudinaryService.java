package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.PersonDTO;
import com.arpan.login.OTPLogin.config.CloudinaryConfiguration;
import com.arpan.login.OTPLogin.models.Person;
import com.arpan.login.OTPLogin.repository.PersonRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    Cloudinary cloudinary;

    public Person savePerson(MultipartFile file , PersonDTO personDTO) throws IOException {
        String savedUrl=saveMedia(file);
        Person newPerson=convertPersonDTOToPerSon(personDTO);
        newPerson.setImageUrl(savedUrl);
        return personRepository.save(newPerson);
    }

    private String saveMedia(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
        return (String) uploadResult.get("url");
    }

    private Person convertPersonDTOToPerSon(PersonDTO personDTO){
        Person newPerson=new Person();
        newPerson.setName(personDTO.getName());
        return newPerson;
    }
}
