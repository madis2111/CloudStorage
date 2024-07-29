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
        UserDetails userDetails = userDetailsService.loadUserByUsername("myusername");
        // todo no such user
        if (credentials.getPasswordHash().equals(userDetails.getPassword())) {
//            Login login = new Login(generateToken());
            return new ResponseEntity<>(HttpStatus.OK); // todo ap return login
        }
        return null; // fixme ap remove return
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
        System.out.println("UPLOAD CALLED");
//        if (fileService.uploadFile(file, filename)) {
            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(new Error("Error input data",), HttpStatus.BAD_REQUEST);
//        }
//        // todo what if exception?
    }

    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestParam String filename) {
        fileService.getFile(filename);
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
    public String putFile(@RequestParam String filename) {
        fileService.putFile(filename);
        return null; // fixme ap
    }
}
