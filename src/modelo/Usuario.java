/**
 * This file was generated by the Jeddict
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Admin
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idU;
    @Basic
    private String nombre;
    private String tipoUsuario;
    @Basic
    private String apellido;

    @Basic
    private String correo;

    @Basic
    private String password;

    @Basic
    private String documento;

    public static final String PROFESOR = "PROFESOR";
    public static final String ADMINISTRADOR = "ADMINISTRADOR";
    public static final String ESTUDIANTE = "ESTUDIANTE";
    public static final String REGISTRADOR = "REGISTRADOR";

    public Usuario() {
    }

    public Usuario(String apellido, String nombre, String dni, String correo, String password, String tipoUsuario) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.documento = dni;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getId() {
        return idU;
    }

    public void setId(Long id) {
        this.idU = id;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " [" + tipoUsuario + "]";
    }

}
