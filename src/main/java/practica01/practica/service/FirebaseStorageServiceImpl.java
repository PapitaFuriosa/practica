package practica01.practica.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Value("${firebase.bucket.name}")
    private String bucketName;

    @Value("${firebase.storage.path}")
    private String storagePath;

    @Value("${firebase.json.path}")
    private String jsonPath;

    @Value("${firebase.json.file}")
    private String jsonFile;

    private Storage storage;

    private Storage getStorage() throws Exception {
        if (storage != null) return storage;

        ClassPathResource resource = new ClassPathResource(jsonPath + "/" + jsonFile);
        try (InputStream is = resource.getInputStream()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(is);
            storage = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .build()
                    .getService();
        }
        return storage;
    }

    @Override
    public String subirImagen(MultipartFile imagenFile) {
        try {
            String original = imagenFile.getOriginalFilename();
            String ext = (original != null && original.contains(".")) 
                    ? original.substring(original.lastIndexOf(".")) 
                    : "";

            String nombreArchivo = UUID.randomUUID() + ext;
            String objeto = storagePath + "/" + nombreArchivo;

            BlobId blobId = BlobId.of(bucketName, objeto);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(imagenFile.getContentType())
                    .build();

            getStorage().create(blobInfo, imagenFile.getBytes());

            String encoded = URLEncoder.encode(objeto, StandardCharsets.UTF_8);
            return "https://firebasestorage.googleapis.com/v0/b/" 
                    + bucketName + "/o/" + encoded + "?alt=media";

        } catch (Exception e) {
            throw new RuntimeException("Error subiendo imagen a Firebase: " + e.getMessage(), e);
        }
    }
}