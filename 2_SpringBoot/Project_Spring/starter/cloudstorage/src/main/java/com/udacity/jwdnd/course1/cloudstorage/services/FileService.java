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
        return filesMapper.getAllFiles(userId);
    }
    public Files getFileByName(Integer fileId) {
        return filesMapper.getFile(fileId);
    }

    public int createFile(Files file)
    {
        System.out.println("Creating File  "+ file.getFileName());
        if (filesMapper.getFileByName(file.getFileName()) == null)
        {
            return filesMapper.insertFile (new Files (null,file.getFileName(),file.getContentType(),file.getFileSize(), file.getUserId(), file.getFileData()));

        }
        else {
            System.out.println("Failed Creating File  "+ file.getFileName() + " Already exists");
            return -100;
        }
    }
    public int updateFile(Files file)
    {
        System.out.println("Edit File  "+ file.getFileName());
        return filesMapper.updateFile (new Files (null, file.getFileName(),file.getContentType(),file.getFileSize(), file.getUserId(), file.getFileData()));
    }
    public int deleteFile(Integer fileId)
    {
        return filesMapper.deleteFile(fileId);
    }

}
