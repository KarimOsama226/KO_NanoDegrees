package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
@Service
public class CredentialsServices {

    private final CredentialsMapper CredMapper;
    private final EncryptionService EncrypService;

    public CredentialsServices(CredentialsMapper CredMapper,EncryptionService EncrypService) {
        this.CredMapper = CredMapper;
        this.EncrypService = EncrypService;
    }
    public List<Credentials> getCredByUser(Integer userId) {
        System.out.println("Searching Cred for ID = " + userId);
        return CredMapper.getAllCredential(userId);
    }
    public Credentials getCredById(Integer credId) {
        System.out.println("Get Single Cred : " + credId);
        Credentials cred = CredMapper.getCredentialById(credId);
        cred.setPassword(EncrypService.decryptValue(cred.getPassword(),cred.getKey()));
        return cred;
    }

    public int addCred(Credentials cred)
    {
        System.out.println("Creating cred  "+ cred.getUrl());
        /*
        if (CredMapper.getCredential(cred.getUserId()) == null)
        {
            return CredMapper.insertNote (new Credentials (null,note.getUserId(),note.getNoteTitle(),note.getNoteDescription() ));

        }
        else {
            System.out.println("Failed Creating Note  "+ note.getNoteTitle() + " Already exists");
            return -100;
        }
         */
        String encodedKey = EncrypService.generateKey();
        cred.setKey(encodedKey);
        cred.setPassword(EncrypService.encryptValue(cred.getPassword(),cred.getKey()));
        return CredMapper.insertCredential (new Credentials(null, cred.getUrl(), cred.getKey(), cred.getUsername(), cred.getPassword(), cred.getUserId()));
    }
    public int updateFile(Credentials cred)
    {
        System.out.println("Edit Cred  "+ cred.getUrl());
        return CredMapper.updateCred (new Credentials (null, cred.getUrl(), cred.getKey(), cred.getUsername(), cred.getPassword(), cred.getUserId()));
    }
    public int deleteFile(Credentials cred)
    {
        System.out.println("Delete Cred  "+ cred.getUrl());
        return CredMapper.deleteCredential(cred.getCredentialId());
    }

}
