
package practica01.practica.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practica01.practica.domain.Arbol;
import practica01.practica.repository.ArbolRepository;

@Service
public class ArbolServiceImpl implements ArbolService {

    private final ArbolRepository arbolRepo;
    private final FirebaseStorageService firebaseStorageService;

    public ArbolServiceImpl(ArbolRepository arbolRepo, FirebaseStorageService firebaseStorageService) {
        this.arbolRepo = arbolRepo;
        this.firebaseStorageService = firebaseStorageService;
    }

    @Override
    public List<Arbol> getArboles() {
        return arbolRepo.findAll();
    }

    @Override
    public Optional<Arbol> getArbol(Long idArbol) {
        return arbolRepo.findById(idArbol);
    }

    @Override
    public void save(Arbol arbol, MultipartFile imagenFile) {
      
        if (imagenFile != null && !imagenFile.isEmpty()) {
            String url = firebaseStorageService.subirImagen(imagenFile);
            arbol.setRutaImagen(url);
        } else {
            
            if (arbol.getIdArbol() != null) {
                Optional<Arbol> actual = arbolRepo.findById(arbol.getIdArbol());
                if (actual.isPresent()) {
                    arbol.setRutaImagen(actual.get().getRutaImagen());
                }
            }
        }

        arbolRepo.save(arbol);
    }

    @Override
    public void delete(Long idArbol) {
        arbolRepo.deleteById(idArbol);
    }
}