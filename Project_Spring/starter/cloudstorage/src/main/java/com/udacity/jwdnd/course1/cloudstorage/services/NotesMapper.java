package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Notes getNote(String noteTitle);
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(Notes note);
    @Delete("DELETE FROM NOTES WHERE notetitle = #{noteTitle}")
    int deleteNote(String noteTitle);
    @Update("UPDATE NOTES SET (notetitle, notedescription,userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId} WHERE noteid = #{noteId}")
    int updateNote(Notes note);
}