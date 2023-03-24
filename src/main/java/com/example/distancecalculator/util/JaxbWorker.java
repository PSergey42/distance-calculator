package com.example.distancecalculator.util;

import com.example.distancecalculator.entity.City;
import com.example.distancecalculator.entity.Distance;
import com.example.distancecalculator.entity.model.DistanceMatrix;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class JaxbWorker {

    public static DistanceMatrix fromXmlToObject(MultipartFile file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(City.class, Distance.class, DistanceMatrix.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            return ((DistanceMatrix)un.unmarshal(saveFileOnServer(file)));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static File saveFileOnServer(MultipartFile multiFile){
        File file = new File("src/main/resources/file/file.xml");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multiFile.getInputStream().readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return file;
    }
}
