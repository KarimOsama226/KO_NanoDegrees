package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credentials> getAllCredential(Integer userId);
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credentials getCredentialById(int credentialId);
    @Insert("INSERT INTO CREDENTIALS (url,key,username,password, userid) VALUES(#{url}, #{key}, #{username}, #{password}, #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credentials Credential);
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int deleteCredential(Integer credentialId);
    @Update("UPDATE NOTES SET (url,key,username,password, userid) VALUES(#{url}, #{key}, #{username}, #{password}, #{userId}, #{userId} WHERE credentialid = #{credentialid}")
    int updateCred(Credentials Credential);
}