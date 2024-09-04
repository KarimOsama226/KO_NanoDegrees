package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
@Service

public class FileService {
    private final FilesMapper filesMapper;

    public FileService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }
    public List<Files> getAllFiles(Integer userId) {
        System.out.println("Searching userId = " + userId);
        return filesMapper.getAllFiles(userId);
    }
    public Files getFileByName(Integer fileId) {
        System.out.println("Searching fileId = " + fileId);
        return filesMapper.getFile(fileId);
    }

    public int createFile(Files file)
    {
        System.out.println("Creating File  "+ file.getFileName());
        if (filesMapper.getFileByName(file.getFileName()) == null)
        {
            return filesMapper.insertFile (new Files (null, file.getFileData(),file.getFileSize(),file.getContentType(),file.getFileName(), file.getUserId()));

        }
        else {
            System.out.println("Failed Creating File  "+ file.getFileName() + " Already exists");
            return -100;
        }
    }
    public int updateFile(Files file)
    {
        System.out.println("Edit File  "+ file.getFileName());
        return filesMapper.updateFile (new Files (null, file.getFileData(),file.getFileSize(),file.getContentType(),file.getFileName(),file.getUserId()));
    }
    public int deleteFile(Files file)
    {
        System.out.println("Delete File  "+ file.getFileName());
        return filesMapper.deleteFile(file.getFileName());
    }

}
