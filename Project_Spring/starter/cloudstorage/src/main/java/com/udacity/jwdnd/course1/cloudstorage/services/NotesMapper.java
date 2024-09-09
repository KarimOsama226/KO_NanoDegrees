package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Notes> getAllNotes(Integer userId);
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Notes getNote(int noteId);
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Notes note);
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleteNote(Integer noteId);
    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription =  #{noteDescription} ,userId = #{userId} WHERE noteid = #{noteId}")
    int updateNote(Notes note);
}