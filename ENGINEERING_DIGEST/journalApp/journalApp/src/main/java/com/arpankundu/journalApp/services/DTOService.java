package com.arpankundu.journalApp.services;

import com.arpankundu.journalApp.DTO.JournalDTO;
import com.arpankundu.journalApp.DTO.UserDTO;
import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.models.Users;
import org.springframework.stereotype.Service;

@Service
public class DTOService {

    //Convert JournalEntry to JournalDTO
    public JournalDTO convertJournaltoJournalDTO(JournalEntry journalEntry){
        JournalDTO journalDTO=new JournalDTO();

        journalDTO.setId(journalEntry.getId());
        journalDTO.setContent(journalDTO.getContent());
        journalDTO.setTitle(journalEntry.getTitle());
        journalDTO.setSharedBy(journalEntry.getSharedBy());
        journalDTO.setModifiedDate(journalEntry.getModifiedDate());
        journalDTO.setUploadDate(journalEntry.getDate());

        return journalDTO;
    }

    //Convert User to UserDTO
    public UserDTO convertUserToUserDTO(Users user){
        UserDTO userDTO=new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    //Convert UserDTO to Users
    public Users convertUserDTOToUser(UserDTO userDTO){
        Users users=new Users();

        users.setId(userDTO.getId());
        users.setEmail(userDTO.getEmail());
        users.setName(userDTO.getName());
        users.setPassword(userDTO.getPassword());
        users.setRole(userDTO.getRole());
        users.setUsername(userDTO.getUsername());

        return users;
    }

    //Convert journalDTO to JournalEntry
    public JournalEntry convertJournalDTOToJournal(JournalDTO journalDTO){

        JournalEntry journalEntry=new JournalEntry();

        journalEntry.setId(journalDTO.getId());
        journalEntry.setTitle(journalDTO.getTitle());
        journalEntry.setContent(journalDTO.getContent());
        journalEntry.setSharedBy(journalDTO.getSharedBy());
        journalEntry.setModifiedDate(journalDTO.getModifiedDate());
        journalEntry.setDate(journalDTO.getUploadDate());
        journalEntry.setCategory(journalEntry.getCategory());

        return journalEntry;
    }
}
