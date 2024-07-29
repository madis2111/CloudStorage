package myproject.repositories;

import myproject.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,String> {

}


//      filename        hash       file
//      myfile1.txt     738748923   00101010111
//      myfile2.txt     738748923   00101010111
//      myfile3.txt     738748923   00101010111
//      myfile4.txt     738748923   00101010111
