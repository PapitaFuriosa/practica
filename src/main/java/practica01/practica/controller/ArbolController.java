/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practica01.practica.domain.Arbol;
import practica01.practica.service.ArbolService;

@Controller
@RequestMapping("/arbol")
public class ArbolController {

    private final ArbolService arbolService;

    public ArbolController(ArbolService arbolService) {
        this.arbolService = arbolService;
    }

  

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("arboles", arbolService.getArboles());
        return "arbol/listado";
    }


    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "arbol/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Arbol arbol = arbolService.getArbol(id).orElse(null);
        model.addAttribute("arbol", arbol);
        return "arbol/form";
    }


    @PostMapping("/guardar")
    public String guardar(Arbol arbol, @RequestParam("imagenFile") MultipartFile imagenFile) {
        arbolService.save(arbol, imagenFile);
        return "redirect:/arbol/listado";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        arbolService.delete(id);
        return "redirect:/arbol/listado";
    }
}