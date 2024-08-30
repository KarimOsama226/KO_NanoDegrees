package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE url = #{url}")
    Credentials getFile(String url);
    @Insert("INSERT INTO CREDENTIALS (url,key,username,password, userid) VALUES(#{url}, #{key}, #{username}, #{password}, #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credentials Credential);
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int deleteCredential(String credentialid);
}