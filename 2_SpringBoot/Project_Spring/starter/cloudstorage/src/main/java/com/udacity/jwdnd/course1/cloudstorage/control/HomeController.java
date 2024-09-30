package com.udacity.jwdnd.course1.cloudstorage.control;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final FileService fileService;
    private final NotesServices notesServices;
    private final CredentialsServices credentialsServices;
    private final EncryptionService encryptionService;


    public HomeController(UserService userService, FileService fileService, NotesServices notesServices, CredentialsServices credentialsServices, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.notesServices = notesServices;
        this.credentialsServices = credentialsServices;
        this.encryptionService = encryptionService;
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            assert authentication != null;
            return authentication.getName();  // In case of other authentication mechanisms (e.g., OAuth)
        }
    }
    public User getLoggedInUser() {

        String username = getLoggedInUsername();
        return userService.getUser(username);
    }
    @GetMapping()
    public String homeView(@RequestParam(value = "tab", required = false, defaultValue = "files") String tab,Model model) {
        User loggedInUser = getLoggedInUser();
        model.addAttribute("files", fileService.getAllFiles(loggedInUser.getUserId()));
        model.addAttribute("notes", notesServices.getNoteByUserId(loggedInUser.getUserId()));
        model.addAttribute("credentials", credentialsServices.getCredByUser(loggedInUser.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("activeTab", tab);
        return "home";
    }
    @PostMapping("/logout")
    public String logoutFromHome(HttpServletRequest request) {
        System.out.println("Logging Out");
        // Invalidate the session
        request.getSession().invalidate();
        return "redirect:/login";  // Redirect to login or another page after logout
    }
    // Handle file upload
    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("uploadFile");
        User loggedInUser = getLoggedInUser();
            Files newFile = new Files (null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    String.valueOf(file.getSize()),
                    loggedInUser.getUserId(),
                    file.getBytes());
            int fileId = fileService.createFile(newFile);
            newFile.setFileId(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home?tab=files&error=true"; // Handle error case
        }

        return "redirect:/home?tab=files";
    }

    // Handle delete file
    @GetMapping("/delete-file/{fileId}")
    public String deleteFile(@PathVariable Integer fileId) {
        System.out.println("deleteFile : " + fileId);
        fileService.deleteFile(fileId);
        return "redirect:/home?tab=files";
    }

    // Handle save note
    @PostMapping("/save-note")
    public String saveNote(@ModelAttribute Notes note , Model model) {
        // Save or update note
        System.out.println("saveNote");
        int noteId;
        User loggedInUser = getLoggedInUser();
        note.setUserId(loggedInUser.getUserId());
        if (note.getNoteId() == null) {
            noteId = this.notesServices.createNote(note);
            note.setNoteId(noteId);
            System.out.println("Note ID = " + noteId+  " Created  Successfully !");
        }
        else
        {
            System.out.println("Editing Note ID = " + note.getNoteId());
            noteId = this.notesServices.updateNote(note);
            System.out.println("Note ID = " + noteId+  "Edited  Successfully !");

        }
        model.addAttribute("notes", this.notesServices.getNoteByUserId(loggedInUser.getUserId()));
        model.addAttribute("updateSuccess", true);
        model.addAttribute("link", "/home");
        return "result";
    }

    // Handle delete note
    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(@PathVariable int noteId) {
        System.out.println("deleteNote" + noteId);
        int id = notesServices.deleteNote(noteId);
        System.out.println("deleted Note Successfully" + id);
        return "redirect:/home?tab=notes";
    }

    // Handle save credential
    @PostMapping("/save-credential")
    public String saveCredential(@ModelAttribute Credentials credential,Model model) {
        // Save or update credential
        User loggedInUser = getLoggedInUser();
        credential.setUserId(loggedInUser.getUserId());
        if(credential.getCredentialId() == null) {
            this.credentialsServices.addCred(credential);
            System.out.println("Key Saved is" + credential.getKey());
        } else {
            Credentials cred = credentialsServices.getCredById(credential.getCredentialId());
            cred.setUrl(credential.getUrl());
            cred.setPassword(credential.getPassword());
            cred.setUsername(credential.getUsername());
            cred.setPassword(encryptionService.encryptValue(cred.getPassword(),cred.getKey()));
            this.credentialsServices.updateCred(cred);
        }
        model.addAttribute("credentials", this.credentialsServices.getCredByUser(loggedInUser.getUserId()));
        model.addAttribute("updateSuccess", true);
        model.addAttribute("link", "/home");
        return "result";
    }

    // Handle delete credential
    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId) {
        System.out.println("deleteCredential");
        int credId = credentialsServices.deletecCedential(credentialId);
        System.out.println("Cred ID = " + credId+  "Created  Successfully !");
        // Delete credential
        return "redirect:/home?tab=credentials";
    }
    @GetMapping("/view-file/{fileId}")
    public ResponseEntity getFile(Model model, @PathVariable(value = "fileId") Integer fileId, Authentication auth) {
        Files file = this.fileService.getFileByName(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file);
    }

}