package com.udacity.jwdnd.course1.cloudstorage.services;

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
        return notesMapper.getAllNotes(userId);
    }
    public Notes getNoteByNoteId(Integer noteId) {
        return notesMapper.getNote(noteId);
    }

    public int createNote(Notes note)
    {
        System.out.println("Creating Note  "+ note.getNoteTitle());
        return notesMapper.insertNote (new Notes (null,note.getNoteTitle(),note.getNoteDescription(),note.getUserId()));
    }
    public int updateNote(Notes note)
    {
        System.out.println("Edit Note  "+ note.getNoteTitle());
        return notesMapper.updateNote (new Notes (note.getNoteId(),note.getNoteTitle(),note.getNoteDescription(),note.getUserId()));
    }
    public int deleteNote(Integer noteid)
    {
        System.out.println("Delete Note ID =  "+ noteid);
        return notesMapper.deleteNote(noteid);
    }

}
