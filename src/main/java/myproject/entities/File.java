package myproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class File {
    private String hash;
    private String file;        // todo ap ???

    public File() {}
}
