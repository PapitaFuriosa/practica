
package practica01.practica.service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import practica01.practica.domain.Arbol;

public interface ArbolService {
    List<Arbol> getArboles();
    Optional<Arbol> getArbol(Long idArbol);
    void save(Arbol arbol, MultipartFile imagenFile);
    void delete(Long idArbol);
}