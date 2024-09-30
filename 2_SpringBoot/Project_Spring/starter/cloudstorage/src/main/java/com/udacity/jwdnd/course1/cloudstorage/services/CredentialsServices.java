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
        String encodedKey = EncrypService.generateKey();
        System.out.println("The Key is " + encodedKey);
        cred.setKey(encodedKey);
        cred.setPassword(EncrypService.encryptValue(cred.getPassword(),cred.getKey()));
        return CredMapper.insertCredential (new Credentials(null, cred.getUrl(), cred.getUsername(),cred.getKey(), cred.getPassword(), cred.getUserId()));
    }
    public int updateCred(Credentials cred)
    {
        System.out.println("Edit Cred  "+ cred.getUrl());
        System.out.println("With Cred  "+ cred.getKey());
        return CredMapper.updateCred (new Credentials (cred.getCredentialId()   , cred.getUrl(), cred.getUsername(), cred.getKey(), cred.getPassword(), cred.getUserId()));
    }
    public int deletecCedential(int credId)
    {
        System.out.println("Delete Cred  "+ credId);
        return CredMapper.deleteCredential(credId);
    }

}
