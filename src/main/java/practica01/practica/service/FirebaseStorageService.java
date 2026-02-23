/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practica01.practica.service;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {
    String subirImagen(MultipartFile imagenFile);
}