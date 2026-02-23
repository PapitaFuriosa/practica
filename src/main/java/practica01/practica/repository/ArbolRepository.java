/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practica01.practica.repository;

import practica01.practica.domain.Arbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArbolRepository extends JpaRepository<Arbol, Long> {
}