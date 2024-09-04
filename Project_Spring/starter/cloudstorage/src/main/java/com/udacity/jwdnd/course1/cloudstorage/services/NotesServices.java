package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServices {
    private final NotesMapper notesMapper;

    public NotesServices(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<Notes> getNoteByUserId(Integer userId) {
        System.out.println("Searching Notes for userId = " + userId);
        return notesMapper.getAllNotes(userId);
    }
    public Notes getNoteByNoteId(Integer noteId) {
        System.out.println("Searching noteId = " + noteId);
        return notesMapper.getNote(noteId);
    }

    public int createFile(Notes note)
    {
        System.out.println("Creating Note  "+ note.getNoteTitle());
        return notesMapper.insertNote (new Notes (null,note.getUserId(),note.getNoteTitle(),note.getNoteDescription() ));
    }
    public int updateFile(Notes note)
    {
        System.out.println("Edit File  "+ note.getNoteTitle());
        return notesMapper.updateNote (new Notes (null,note.getUserId(),note.getNoteTitle(),note.getNoteDescription()));
    }
    public int deleteFile(Notes note)
    {
        System.out.println("Delete Note ID =  "+ note.getNoteId());
        return notesMapper.deleteNote(note.getNoteId());
    }

}
