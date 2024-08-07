package myproject.controllers;

import myproject.entities.Credentials;
import myproject.entities.Error;
import myproject.entities.File;
import myproject.entities.Login;
import myproject.services.FileService;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) {
        // todo verification of login and pass
        UserDetailsService userDetailsService = new JdbcDaoImpl();
        String username = credentials.getUsername();
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            Error error = new Error("Wrong username",400);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // todo no such user
        if (credentials.getPasswordHash().equals(userDetails.getPassword())) {
//            Login login = new Login(generateToken());
            return new ResponseEntity<>(login, HttpStatus.OK); // todo ap return login
        }
        Error error = new Error("Wrong password",400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // todo ap remove
    // localhost:8080/logout <-- parameters
    //      header: ...
    //      body: ...

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader String authToken) {
        // TODO AP
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestBody File file, @RequestParam String filename) {

        if (fileService.uploadFile(file, filename)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // todo file already exists?
        return new ResponseEntity<>(new Error("Error input data",400), HttpStatus.BAD_REQUEST);
//        // todo what if exception?
    }

    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestParam String filename) {
        File file = fileService.getFile(filename);
        if (file == null) { // todo difference between 400 and 500 how to tell
            return new ResponseEntity<>(new Error("Error input data",400), HttpStatus.BAD_REQUEST);
        }
        return null; // fixme ap
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll(@RequestParam int limit) {
        return new ResponseEntity<>(fileService.getAll(limit),HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestParam String filename) {
        if (fileService.deleteFile(filename)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // fixme ap return new ResponseEntity<>(new Error("Error deleting file",), HttpStatus.INTERNAL_SERVER_ERROR);
        } // todo ap else ...
        return null;    // fixme ap remove return
    }

    @PutMapping("/file")
    public String putFile(@RequestParam String filename, @RequestBody File file) {
        fileService.putFile(file, filename);
        return null; // fixme ap
    }
}
