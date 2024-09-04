package com.udacity.jwdnd.course1.cloudstorage.model;

public class Notes {
    private Integer noteId;

    public Notes(Integer noteId, Integer userId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.userId = userId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    private Integer userId;
    private String noteTitle ;
    private String noteDescription;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
