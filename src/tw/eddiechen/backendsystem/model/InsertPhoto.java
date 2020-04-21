package tw.eddiechen.backendsystem.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;
@Repository
public class InsertPhoto {
    private static ServiceRegistry serviceRegistry;
    private static Session session;
     
    public static void main(String[] args) throws IOException {
        initSession();
        
        String photoFilePathToRead = "D:\\Test\\p17.jpg";
        savePersonWithPhoto(photoFilePathToRead);
         
//        int personId = 1;
//        String photoFilePathToSave = "D:\\Test\\p18.jpg";
//        readPhotoOfPerson(personId, photoFilePathToSave);
         
        endSession();
    }
     
    private static void savePersonWithPhoto(String photoFilePath) throws IOException {
        Clinic clinic = new Clinic();
        byte[] photoBytes = readBytesFromFile(photoFilePath);
        clinic.setClinicPhoto(photoBytes);
        session.save(clinic);
    }
     
    private static void readPhotoOfPerson(int personId, String photoFilePath) throws IOException {
    	Clinic clinic = (Clinic) session.get(Clinic.class, personId);
        byte[] photoBytes = clinic.getClinicPhoto();
        saveBytesToFile(photoFilePath, photoBytes);
    }
     
    private static byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);
         
        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();
         
        return fileBytes;
    }
     
    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }
     
     
    private static void initSession() {
        Configuration configuration = new Configuration().configure();
        serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
         
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         
        session = sessionFactory.openSession();
        session.beginTransaction();
    }
     
    private static void endSession() {
        session.getTransaction().commit();
        session.close();
         
        StandardServiceRegistryBuilder.destroy(serviceRegistry);       
    }
}
