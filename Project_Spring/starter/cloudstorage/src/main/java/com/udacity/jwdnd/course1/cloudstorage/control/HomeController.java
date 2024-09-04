package com.udacity.jwdnd.course1.cloudstorage.control;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsServices;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServices;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final FileService fileService;
    private final NotesServices notesServices;
    private final CredentialsServices credentialsServices;

    public HomeController(UserService userService, FileService fileService, NotesServices notesServices, CredentialsServices credentialsServices) {
        this.userService = userService;
        this.fileService = fileService;
        this.notesServices = notesServices;
        this.credentialsServices = credentialsServices;
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            System.out.println("here getUsername()");
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            assert authentication != null;
            System.out.println("here getUsername!()");
            return authentication.getName();  // In case of other authentication mechanisms (e.g., OAuth)
        }
    }
    public User getLoggedInUser() {

        String username = getLoggedInUsername();
        System.out.println("getLoggedInUser");
        return userService.getUser(username);
    }
    @GetMapping()
    public String homeView(Model model) {
        System.out.println("homeView");
        User loggedInUser = getLoggedInUser();
        model.addAttribute("files", fileService.getAllFiles(loggedInUser.getUserId()));
        model.addAttribute("notes", notesServices.getNoteByUserId(loggedInUser.getUserId()));
        model.addAttribute("credentials", credentialsServices.getCredByUser(loggedInUser.getUserId()));
        return "home";
    }
    @PostMapping("/home")
    public String logoutFromHome() {
        // Handle file upload logic
        System.out.println("Logging Out");

        return "redirect:/login";
    }
    // Handle file upload
    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes) {
        // Handle file upload logic
        System.out.println("uploadFile");

        return "redirect:/home?tab=files";
    }

    // Handle delete file
    @GetMapping("/delete-file/{fileId}")
    public String deleteFile(@PathVariable int fileId) {
        System.out.println("deleteFile");
        // Handle file deletion
        return "redirect:/home?tab=files";
    }

    // Handle save note
    @PostMapping("/save-note")
    public String saveNote(@ModelAttribute Notes note) {
        // Save or update note
        System.out.println("saveNote");
        return "redirect:/home?tab=notes";
    }

    // Handle delete note
    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(@PathVariable int noteId) {
        System.out.println("deleteNote");
        // Delete note
        return "redirect:/home?tab=notes";
    }

    // Handle save credential
    @PostMapping("/save-credential")
    public String saveCredential(@ModelAttribute Credentials credential) {
        // Save or update credential
        System.out.println("saveCredential credential "+ credential.getUrl());
        return "redirect:/home?tab=credentials";
    }

    // Handle delete credential
    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId) {
        System.out.println("deleteCredential");
        // Delete credential
        return "redirect:/home?tab=credentials";
    }

}