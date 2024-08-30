package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM FILES WHERE username = #{fileName}")
    Files getFile(String fileName);
    @Insert("INSERT INTO FILES (filename, contenttype,filesize,userid) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userid}")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(Files file);
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(String noteTitle);
    @Update("UPDATE FILES SET filename = #{filename}, contenttype = #{contenttype}, filesize = #{filesize}, filedata = #{filedata}, userid = #{userid} WHERE fileId = #{fileId}")
    int updateFile(Files file);
}