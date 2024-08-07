package myproject.services;

import myproject.entities.File;
import myproject.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getFile(String filename) {
        return fileRepository.findByFilename(filename);
       return null; // fixme ap fileRepository.find;
    }

    public boolean deleteFile(String filename) {
        return false; // fixme ap fileRepository.
    }

    public String putFile(File file, String filename) {

        if (! fileRepository.existsById(filename)) {
            // todo return error 400
        } else {
            fileRepository.save(file);
        }
        // todo error 500
        return null; // fixme ap what to return?
    }

    public boolean uploadFile(File file, String filename) {

        // todo ap filename
        return false; // fixme ap fileRepository.save(file);
    }

    public List<File> getAll(int limit) {
        return fileRepository.findAll();
    }
}
