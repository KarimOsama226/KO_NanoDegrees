package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<Files> getAllFiles(Integer userId);
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFile(Integer fileId);
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    Files getFileByName(String filename);
    @Insert("INSERT INTO FILES (filename, contenttype,filesize,userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(Files file);
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(Integer fileId);
    @Update("UPDATE FILES SET filename = #{filename}, contenttype = #{contenttype}, filesize = #{filesize}, filedata = #{fileData}, userid = #{userId} WHERE fileId = #{fileId}")
    int updateFile(Files file);
}