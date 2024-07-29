package myproject.services;

import myproject.entities.File;
import myproject.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getFile(String filename) {
       return null; // fixme ap fileRepository.find;
    }

    public boolean deleteFile(String filename) {
        return false; // fixme ap fileRepository.
    }

    public String putFile(String filename) {

        return null; // fixme ap fileRepository.
    }

    public boolean uploadFile(File file, String filename) {

        // todo ap filename
        return false; // fixme ap fileRepository.save(file);
    }

    public List<File> getAll(int limit) {
        return fileRepository.findAll();
    }
}
