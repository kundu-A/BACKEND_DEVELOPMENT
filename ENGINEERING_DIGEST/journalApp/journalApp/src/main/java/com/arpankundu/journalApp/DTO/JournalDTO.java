package com.arpankundu.journalApp.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class JournalDTO {

    private int id;

    @NotBlank(message = "Title can't be null")
    private String title;

    @NotBlank(message = "Content can't be null")
    private String content;

    private String sharedBy;
    private LocalDate uploadDate;
    private LocalDate modifiedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Title can't be null") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title can't be null") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Content can't be null") String getContent() {
        return content;
    }

    public void setContent(@NotBlank(message = "Content can't be null") String content) {
        this.content = content;
    }

    public String getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(String sharedBy) {
        this.sharedBy = sharedBy;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
