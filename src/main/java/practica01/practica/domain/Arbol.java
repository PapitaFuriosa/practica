/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica01.practica.domain;



import jakarta.persistence.*;

@Entity
@Table(name="arbol")
public class Arbol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_arbol")
    private Long idArbol;

    @Column(name="nombre_comun")
    private String nombreComun;

    @Column(name="tipo_flor")
    private String tipoFlor;

    @Column(name="dureza_madera")
    private Integer durezaMadera;

    @Column(name="ruta_imagen")
    private String rutaImagen; // URL de Firebase

    public Long getIdArbol() { return idArbol; }
    public void setIdArbol(Long idArbol) { this.idArbol = idArbol; }

    public String getNombreComun() { return nombreComun; }
    public void setNombreComun(String nombreComun) { this.nombreComun = nombreComun; }

    public String getTipoFlor() { return tipoFlor; }
    public void setTipoFlor(String tipoFlor) { this.tipoFlor = tipoFlor; }

    public Integer getDurezaMadera() { return durezaMadera; }
    public void setDurezaMadera(Integer durezaMadera) { this.durezaMadera = durezaMadera; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}