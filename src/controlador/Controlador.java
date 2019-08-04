package controlador;

import dao.Persistencia;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.Foro;
import modelo.Materia;
import modelo.Pregunta;
import modelo.Pregunta_;
import modelo.Respuesta;
import modelo.Usuario;
import modelo.UsuarioAcademico;
import modelo.Voto;
import servicios.Hash;
import vistas.MainFrame;

//controla la vista: VerPerfil
public class Controlador {

    Persistencia persistencia;

    public Controlador(Persistencia p) {
        this.persistencia = p;
    }

    public Persistencia getPersistencia() {
        return persistencia;
    }

    public List<Pregunta> listarPregunta() {
        return this.persistencia.buscarTodos(Pregunta.class);
    }

    public List<Pregunta> listarPreguntaOrdenadasPorFecha() {
        return this.persistencia.buscarTodosOrdenadosPor(Pregunta.class, Pregunta_.fechaPublicacion);
    }

    public List<Foro> listarForos() {
        return this.persistencia.buscarTodos(Foro.class);
    }

    public List<UsuarioAcademico> listarProfesor() {
        List<UsuarioAcademico> lista = this.persistencia.buscarTodos(UsuarioAcademico.class);
        List<UsuarioAcademico> listaFinal = new ArrayList<>();
        for (UsuarioAcademico u : lista) {
            if (u.getTipoUsuario().toUpperCase().equals(Usuario.PROFESOR)) {
                listaFinal.add(u);
            }
        }
        return listaFinal;
    }

    public List<UsuarioAcademico> listarEstudiante() {
        List<UsuarioAcademico> lista = this.persistencia.buscarTodos(UsuarioAcademico.class);
        List<UsuarioAcademico> listaFinal = new ArrayList<>();
        for (UsuarioAcademico u : lista) {
            if (u.getTipoUsuario().toUpperCase().equals(Usuario.ESTUDIANTE)) {
                listaFinal.add(u);
            }
        }
        return listaFinal;
    }

    public List<Usuario> listarRegistrador() {
        List<Usuario> lista = this.persistencia.buscarTodos(Usuario.class);
        List<Usuario> listaFinal = new ArrayList<>();
        for (Usuario u : lista) {
            if (u.getTipoUsuario().toUpperCase().equals(Usuario.REGISTRADOR)) {
                listaFinal.add(u);
            }
        }
        return listaFinal;
    }

    public List<UsuarioAcademico> listarAdministrador() {
        List<UsuarioAcademico> lista = this.persistencia.buscarTodos(UsuarioAcademico.class);
        List<UsuarioAcademico> listaFinal = new ArrayList<>();
        for (UsuarioAcademico u : lista) {
            if (u.getTipoUsuario().toUpperCase().equals(Usuario.ADMINISTRADOR)) {
                listaFinal.add(u);
            }
        }
        return listaFinal;
    }

    public List<UsuarioAcademico> listarUsuarioAcademicos() {
        return this.persistencia.buscarTodos(UsuarioAcademico.class);
    }

    public List<Usuario> listarUsuarios() {
        return this.persistencia.buscarTodos(Usuario.class);

    }

    public List<Usuario> listarUsuariosPersonalizado(String filtro, String nombre) {
        List<Usuario> lista = new ArrayList<>();
        if (filtro.toUpperCase().equals(Usuario.ADMINISTRADOR)) {
            if (nombre.trim().equals("")) {
                for (Usuario us : this.listarAdministrador()) {
                    lista.add(us);
                }
                return lista;
            } else {
                for (Usuario us : this.listarAdministrador()) {
                    if (us.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                        lista.add(us);
                    }

                }
                return lista;

            }

        } else if (filtro.toUpperCase().equals(Usuario.REGISTRADOR)) {
            if (nombre.trim().equals("")) {
                for (Usuario us : this.listarRegistrador()) {
                    lista.add(us);
                }
                return lista;
            } else {
                for (Usuario us : this.listarRegistrador()) {
                    if (us.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                        lista.add(us);
                    }

                }
                return lista;

            }
        } else if (filtro.toUpperCase().equals(Usuario.PROFESOR)) {
            if (nombre.trim().equals("")) {
                for (Usuario us : this.listarProfesor()) {
                    lista.add(us);
                }
                return lista;
            } else {
                for (Usuario us : this.listarProfesor()) {
                    if (us.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                        lista.add(us);
                    }

                }
                return lista;

            }
        } else if (filtro.toUpperCase().equals(Usuario.ESTUDIANTE)) {
            if (nombre.trim().equals("")) {
                for (Usuario us : this.listarEstudiante()) {
                    lista.add(us);
                }
                return lista;
            } else {
                for (Usuario us : this.listarEstudiante()) {
                    if (us.getNombre().toUpperCase().equals(nombre.toUpperCase())) {
                        lista.add(us);
                    }

                }
                return lista;

            }
        } else if (filtro.toUpperCase().equals("CORREO")) {
            for (Usuario us : this.listarUsuarios()) {
                if (us.getCorreo().toUpperCase().equals(nombre.toUpperCase())) {
                    lista.add(us);
                    break;
                }
            }
            return lista;
        } else if (filtro.toUpperCase().equals("DOCUMENTO")) {
            for (Usuario us : this.listarUsuarios()) {
                if (us.getDocumento().toUpperCase().equals(nombre.toUpperCase())) {
                    lista.add(us);
                    break;
                }
            }
            return lista;
        } else if (filtro.toUpperCase().equals("TODOS")) {

            return this.listarUsuarios();
        }
        return null;
    }

    public List<Pregunta> buscarPreguntas(String titulo) {
        List<Pregunta> listaPreg = new ArrayList<>();
        for (Pregunta preg : this.listarPregunta()) {
            if (preg.getTitulo().toUpperCase().contains(titulo.toUpperCase())) {
                listaPreg.add(preg);
            }
        }

        return listaPreg;
    }

    public List<Foro> buscarForos(String titulo) {
        List<Foro> listaForo = new ArrayList<>();
        for (Foro foro : this.listarForos()) {
            if (foro.getTitulo().toUpperCase().contains(titulo.toUpperCase())) {
                listaForo.add(foro);
            }
        }

        return listaForo;
    }

    public List<Usuario> buscarUsuarios(String nombre) {
        List<Usuario> listaUs = new ArrayList<>();
        for (Usuario us : this.listarUsuarios()) {
            if (us.getNombre().toUpperCase().contains(nombre.toUpperCase()) || us.getApellido().toUpperCase().contains(nombre.toUpperCase())) {
                listaUs.add(us);
            }
        }
        return listaUs;
    }

    public List<Materia> listarMaterias() {
        return this.persistencia.buscarTodos(Materia.class);
    }

    public List<Pregunta> listarOrdenadoPreguntas() {
        return this.persistencia.buscarTodosOrdenadosPor(Pregunta.class, Pregunta_.fechaPublicacion);

    }

    public Foro buscarForo(String nombre) {
        return this.persistencia.buscar(Foro.class, nombre);
    }

    public UsuarioAcademico buscarUsuarioAcademico(Long id) {
        return this.persistencia.buscar(UsuarioAcademico.class, id);
    }

    public Usuario buscarUsuario(Long id) {
        return this.persistencia.buscar(Usuario.class, id);
    }

    public List<Pregunta> obtenerPreguntasDelForo(Foro foro) {
        return foro.getPreguntas();
    }

    public Foro obtenerForoDeLaPregunta(Pregunta pregunta) {
        return pregunta.getForo();
    }

    public List<Pregunta> obtenerPreguntasDelUsuarioAcademico(UsuarioAcademico usuario) {
        return usuario.getPreguntas();
    }

    public List<Respuesta> obtenerRespuestasDelUsuarioAcademico(UsuarioAcademico usuario) {
        return usuario.getRespuestas();
    }

    public List<Respuesta> obtenerRepuestasDePregunta(Pregunta p) {
        return p.getRespuestas();
    }

    public List obtenerListRespuestaOrdenada(Pregunta pre) {
        return pre.getRespuestas();
    }

    public Boolean crearPregunta(String titulo, String descripcion, Foro foro, Usuario usuario) {
        this.persistencia.iniciarTransaccion();

        try {
            Long id = usuario.getId();
            UsuarioAcademico usuarioA = this.buscarUsuarioAcademico(id);
            Pregunta nuevaPregunta = new Pregunta(titulo, descripcion, usuarioA, foro);
            //insertamos la pregunta en la BD y asociamos a su usuario que publico.
            if ((usuarioA) != null) {
                this.persistencia.insertar(nuevaPregunta);
                usuarioA.agregarPregunta(nuevaPregunta);
                this.persistencia.modificar(usuarioA);
            }
            foro.agregarPregunta(nuevaPregunta);
            this.persistencia.modificar(foro);
            this.persistencia.confirmarTransaccion();
            return true;
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            System.out.println("Error al insertar");
            return false;
        }
    }

    public Boolean crearRespuesta(String respu, String titulo, Pregunta pregunta, Usuario usuario) {
        this.persistencia.iniciarTransaccion();

        try {
            Long id = usuario.getId();
            UsuarioAcademico usuarioA = this.buscarUsuarioAcademico(id);

            Respuesta respuesta = new Respuesta(respu, titulo, usuarioA, pregunta);
            //insertamos la respuesta en la BD y asociamos a su usuario que publico.
            if ((usuarioA) != null) {
                this.persistencia.insertar(respuesta);
                usuarioA.agregarRespuesta(respuesta);
                this.persistencia.modificar(usuarioA);
            }
            //asociamos la respuesta a la pregunta
            pregunta.agregarRespuesta(respuesta);
            this.persistencia.modificar(pregunta);
            this.persistencia.confirmarTransaccion();
            return true;
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            System.out.println("Error al insertar");
            return false;
        }
    }

    public Boolean eliminarRespuesta(Respuesta respuesta, Pregunta pregunta) {
        if (respuesta.getVotos().isEmpty()) {
            try {
                this.persistencia.iniciarTransaccion();
                UsuarioAcademico usuarioA = respuesta.getUsuario();
                if (usuarioA != null) {
                    usuarioA.getPreguntas().remove(respuesta);
                    this.persistencia.modificar(usuarioA);
                }
                pregunta.quitarRespuesta(respuesta);
                this.persistencia.modificar(pregunta);
                this.persistencia.eliminar(respuesta);
                this.persistencia.confirmarTransaccion();
                return true;

            } catch (Exception ex) {
                this.persistencia.descartarTransaccion();
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque esta asociado a " + respuesta.getVotos().size() + " votos");
            return false;
        }

    }

    public Boolean crearMateria(String materia, String enlace) {
        Materia materia1 = new Materia(materia, enlace);
        try {
            this.persistencia.iniciarTransaccion();
            this.persistencia.insertar(materia1);
            this.persistencia.confirmarTransaccion();
            return true;
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            return false;
        }
    }

    public Boolean modificarMateria(Materia materia, String nombre, String enlace) {
        materia.setMateria(nombre);
        materia.setEnlace(enlace);
        try {
            this.persistencia.iniciarTransaccion();
            this.persistencia.modificar(materia);
            this.persistencia.confirmarTransaccion();
            return true;
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            return false;
        }
    }

    public Boolean eliminarMateria(Materia materia) {

        if (materia.getProfesores() == null) {
            this.persistencia.iniciarTransaccion();
            this.persistencia.eliminar(materia);
            this.persistencia.confirmarTransaccion();
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque esta asociado a " + materia.getProfesores().size() + " profesores");
            return false;
        }

    }

    public Boolean crearForo(String nombre) {
        this.persistencia.iniciarTransaccion();
        List foros = this.listarForos();
        Boolean existe = false;
        for (Foro foro1 : (List<Foro>) foros) {
            String titulo = foro1.getTitulo();
            if (titulo.toUpperCase().equals(nombre.toUpperCase())) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            Foro foro = new Foro(nombre);
            this.persistencia.insertar(foro);
            this.persistencia.confirmarTransaccion();
            return true;
        } else {
            this.persistencia.descartarTransaccion();
            return false;
        }
    }

    //eliminar como administrador
    public Boolean eliminarForo(Foro foro) {
        this.persistencia.iniciarTransaccion();
        List<Pregunta> preguntas = foro.getPreguntas();
        for (Pregunta pregunta : preguntas) {

            this.eliminarPreguntaCompleta(pregunta, this.persistencia);
        }
        this.persistencia.eliminar(foro);
        this.persistencia.confirmarTransaccion();
        Boolean respuesta = true;
        return respuesta;
    }

    public void eliminarPregunta(Pregunta pregunta) {
        this.persistencia.iniciarTransaccion();
        this.eliminarPreguntaCompleta(pregunta, persistencia);
        Foro foro = pregunta.getForo();
        foro.quitarPregunta(pregunta);
        this.persistencia.modificar(pregunta.getForo());
        this.persistencia.confirmarTransaccion();
    }

    public void eliminarRespuesta(Respuesta respuesta) {
        this.persistencia.iniciarTransaccion();
        this.eliminarRespuestaCompleta(respuesta, persistencia);
        Pregunta pregunta = respuesta.getPregunta();
        pregunta.quitarRespuesta(respuesta);
        this.persistencia.modificar(respuesta.getPregunta());
        this.persistencia.confirmarTransaccion();
    }

    //eliminar como administrador
    public Boolean eliminarPreguntaCompleta(Pregunta pregunta, Persistencia pers) {
        UsuarioAcademico usuarioA = pregunta.getUsuario();
        if (usuarioA != null) {
            usuarioA.quitarPregunta(pregunta);
            pers.modificar(usuarioA);
        }
        List<Respuesta> listaRespuestas = pregunta.getRespuestas();
        for (Respuesta respuesta : listaRespuestas) {
            //eliminamos la respuesta con sus respectivas asociaciones
            this.eliminarRespuestaCompleta(respuesta, pers);
        }

        pers.eliminar(pregunta);
        return true;

    }

    //eliminar respuesta con todas sus asociaciones
    public Boolean eliminarRespuestaCompleta(Respuesta respuesta, Persistencia persis) {
        //eliminamos la respuesta del usuario asociado
        UsuarioAcademico usuarioA = respuesta.getUsuario();
        if (usuarioA != null) {
            usuarioA.quitarRespuesta(respuesta);
            persis.modificar(usuarioA);
        }
        List<Voto> votos = respuesta.getVotos();
        for (Voto voto : votos) {
            this.eliminarVoto(voto, persis);
        }

        //eliminamos la respuesta 
        persis.eliminar(respuesta);
        return true;
    }

    public void eliminarVoto(Voto voto, Persistencia persis) {
        UsuarioAcademico usuarioA = voto.getUsuario();
        if (usuarioA != null) {
            usuarioA.quitarVoto(voto);
            persis.modificar(usuarioA);
        }
        persis.eliminar(voto);
    }

    /*se crea una lista con materias, reputacion, apellido, nombre, correo, 
 preguntas, respuestas realizadas, documento
    si es un alumno no se carga las materias*/
    public List obtenerDatosVerPerfil(Usuario usuario) {
        List<Object> datos = new ArrayList<>();
        Boolean respuesta = this.esUsuarioRegistrador(usuario);
        if (respuesta) {
            //si entra singifica que es registrador el usuario
            datos.add(0);//aca iria la reputacion
            datos.add(usuario.getApellido());
            datos.add(usuario.getNombre());
            datos.add(usuario.getCorreo());
            datos.add(0);//cantidad de preguntas
            datos.add(0);//cantidad de respuestas
            datos.add(usuario.getDocumento());
            datos.add(usuario.getPassword());
            return datos;
        }
        Long idu = usuario.getId();
        UsuarioAcademico ua = this.buscarUsuarioAcademico(idu);

        datos.add(ua.getReputacion());
        datos.add(ua.getApellido());
        datos.add(ua.getNombre());
        datos.add(ua.getCorreo());
        datos.add(ua.getPreguntas());
        datos.add(ua.getRespuestas());
        datos.add(ua.getDocumento());
        datos.add(ua.getPassword());
        String tipo = ua.getTipoUsuario();
        if (tipo.toUpperCase().equals(Usuario.PROFESOR)) {
            Set<Materia> materias = ua.getMaterias();
            if (!materias.isEmpty()) {
                datos.add(materias);
            } else {
                ArrayList<Materia> listM = new ArrayList<>();
                Materia nueva = new Materia("Vacio", "");
                listM.add(nueva);
                datos.add(listM);
            }

        }
        return datos;
    }
//visa crear usuario crea al usuario dependiendo el tipo de usuario que sea (administrador,registrador, estudiante, profesor)

    public Boolean crearUsuario(String nombre, String apellido, String correo, String documento, String password, String usuario) {
        this.persistencia.iniciarTransaccion();
        if (usuario.toUpperCase().equals(Usuario.ADMINISTRADOR)) {
            UsuarioAcademico admin = new UsuarioAcademico(apellido, nombre, documento, correo, password, usuario);
            this.persistencia.insertar(admin);
        } else if (usuario.toUpperCase().equals(Usuario.REGISTRADOR)) {
            Usuario re = new Usuario(apellido, nombre, documento, correo, password, usuario);
            this.persistencia.insertar(re);
        } else if (usuario.toUpperCase().equals(Usuario.PROFESOR)) {
            UsuarioAcademico re = new UsuarioAcademico(apellido, nombre, documento, correo, password, usuario);
            this.persistencia.insertar(re);
        } else if (usuario.toUpperCase().equals(Usuario.ESTUDIANTE)) {
            UsuarioAcademico re = new UsuarioAcademico(apellido, nombre, documento, correo, password, usuario);
            this.persistencia.insertar(re);
        } else {
            this.persistencia.descartarTransaccion();
            return false;
        }
        this.persistencia.confirmarTransaccion();
        return true;

    }
//la lista de datos tiene Apellido y nombre, tipo de usuario, titulo, descripcion, fecha de publicacion, cantidad de respuestas, fecha de la ultima respuesta

    public List obtenerInformacionPregunta(Pregunta pregunta) {
        List datos = new ArrayList<>();
        try {
            UsuarioAcademico usuarioA = pregunta.obtenerPublicador();
            String apellido = usuarioA.getApellido();
            String nombre = usuarioA.getNombre();
            datos.add(apellido + ' ' + nombre);

            String tipo = usuarioA.getTipoUsuario();
            datos.add(tipo);

            String titulo = pregunta.getTitulo();
            datos.add(titulo);

            String descripcion = pregunta.getDescripcion();
            datos.add(descripcion);

            SimpleDateFormat tranformador = new SimpleDateFormat("dd/MM/yy HH:mm");
            Date fecha = pregunta.getFechaPublicacion();
            datos.add(tranformador.format(fecha));

            List<Respuesta> listaRespuesta = pregunta.getRespuestas();
            datos.add(listaRespuesta.size());

            if (!listaRespuesta.isEmpty()) {
                Date fechaResp = pregunta.obtenerFechaDeUltimaRespuesta();
                datos.add(tranformador.format(fechaResp));
            } else {
                datos.add("Vacio");
            }
        } catch (Exception ex) {
        }
        return datos;
    }

//vista  ViewPreunta pasarle el nombre, tipo de usuario,hora, titulo,respuesta,votos postivos, votos negativos
//[0]= apellido y nombre del usuario [1]= tipoUsuario [2]=fechaPublicacion [3]=titulo respuesta [4]=respuesta [5]=cantidadVotosPositivos [6]=cantidad de votos negativos
    public List obtenerInformacionRespuesta(Respuesta respuesta) {
        List datos = new ArrayList<>();
        try {
            UsuarioAcademico usuarioA = respuesta.obtenerPublicador();
            String apellido = usuarioA.getApellido();
            String nombre = usuarioA.getNombre();
            datos.add(apellido + ' ' + nombre);
            String tipo = usuarioA.getTipoUsuario();
            datos.add(tipo);

            SimpleDateFormat tranformador = new SimpleDateFormat("dd/MM/yy HH:mm");
            Date fecha = respuesta.getFechaPublicacion();
            datos.add(tranformador.format(fecha));
            String titulo = respuesta.getTitulo();
            datos.add(titulo);

            String descripcion = respuesta.getRespuesta();
            datos.add(descripcion);

            Integer positivos = respuesta.getVotosPositivos();
            datos.add(positivos);

            Integer negativos = respuesta.getVotosNegativos();
            datos.add(negativos);
            return datos;
        } catch (Exception ex) {

        }

        return null;
    }

    //se usa en la vista MainFrame
    //la lista esta en esta orden Apellido, nombre, documento, correo , ttipo de usuario (ejemplo "Administrador") 
    public List<String> obtenerDatosUsuario(Usuario usuario) {
        List<String> datos = new ArrayList<>();
        datos.add(usuario.getApellido());
        datos.add(usuario.getNombre());
        datos.add(usuario.getDocumento());
        datos.add(usuario.getCorreo());
        datos.add(usuario.getTipoUsuario());
        return datos;
    }

    public void crearVoto(Boolean voto, Respuesta respuesta, Usuario usuario) {
        this.persistencia.iniciarTransaccion();
        try {
            List<Voto> votos = respuesta.getVotos();
            UsuarioAcademico usuarioA = null;
            Boolean existe = false;
            Long idUS = usuario.getId();
            //recorremos todos los votos de la respuesta
            for (Voto viejo : votos) {
                usuarioA = viejo.getUsuario();
                //verificamos si el usuario es un administrador o no
                if (usuarioA != null) {
                    //verificamos si el usuario que hizo el voto ahora es el mismo del voto viejo 
                    if (idUS.equals(usuarioA.getId())) {
                        existe = true;
                        this.cambiarVoto(voto, viejo, respuesta, usuarioA, this.persistencia);
                    }
                }
            }
//si no existe es porque el usuario no voto anteriormente
            if (!existe) {
                usuarioA = this.buscarUsuarioAcademico(idUS);
                if (usuarioA != null) {
                    this.nuevoVoto(voto, respuesta, usuarioA, persistencia);
                }

            }
            this.persistencia.confirmarTransaccion();
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            System.err.println("Transaccion descartada");
            System.out.println(ex.getMessage());
        }

    }

//si el usuario ya habia votado se ejecuta el siguiente comando
    //usuario es el usuario que relizo el voto
    public void cambiarVoto(Boolean votoNuevo, Voto votoViejo, Respuesta respuesta, UsuarioAcademico usuarioVoto, Persistencia persi) {
        //si es positivo es true
        Boolean votoPositivo = votoViejo.isVoto();
        if (!votoPositivo.equals(votoNuevo)) {
            //si no son iguales debo modificar al nuevo voto
            votoViejo.setVoto(votoNuevo);
            //aumenta los votos o los disminuye y calcula la reputacion del usuario
            respuesta.modificarVoto(votoNuevo);
            //el usuario que voto modifica los votos que tenia
            usuarioVoto.modificarVoto(votoNuevo);
            //usuarioA es el usuario que relizo la respuesta
            UsuarioAcademico usuarioRealizoRespuesta = respuesta.getUsuario();
            //guardan los cambios de la reputacion del usuario
            persi.modificar(usuarioRealizoRespuesta);

            persi.modificar(respuesta);
            //el usuario que voto gaurda los cambios
            persi.modificar(usuarioVoto);
            persi.modificar(votoViejo);

        }

        //si los dos votos son iguales no tengo que hacer nada ya que voto anteriormente
    }

    public void nuevoVoto(Boolean votoNuevo, Respuesta respuesta, UsuarioAcademico usuarioVoto, Persistencia persi) {
        Long id = usuarioVoto.getId();
        UsuarioAcademico usuarioA = this.buscarUsuarioAcademico(id);
        Voto nuevoVoto = new Voto(votoNuevo, usuarioA);
        this.persistencia.insertar(nuevoVoto);
        usuarioVoto.agregarVoto(nuevoVoto);
        respuesta.agregarVoto(nuevoVoto);
        UsuarioAcademico usuarioRealizoRepuesta = respuesta.getUsuario();
        String tipo = usuarioRealizoRepuesta.getTipoUsuario();
        if (votoNuevo) {
            if (!tipo.equals(Usuario.ADMINISTRADOR)) {
                usuarioRealizoRepuesta.aumentarReputacion();
                this.persistencia.modificar(usuarioRealizoRepuesta);
            }

        } else {
            if (!tipo.equals(Usuario.ADMINISTRADOR)) {
                usuarioRealizoRepuesta.disminuirReputacion();
                this.persistencia.modificar(usuarioRealizoRepuesta);
            }
        }
        this.persistencia.modificar(respuesta);
        this.persistencia.modificar(usuarioVoto);

    }

//verifica si el usuario que esta usando el sistema ya voto
    public Boolean verificarSiVoto(Usuario usuario, Respuesta respuesta) {
        UsuarioAcademico usuarioA = this.buscarUsuarioAcademico(usuario.getId());
        if (usuarioA != null) {
            for (Voto voto : respuesta.getVotos()) {
                if (voto.getUsuario().getId().equals(usuarioA.getId())) {
                    return true;
                }

            }
        }
        return false;
    }
    //ver perfil solo del estudiante y el profesor

    public void agregarMateria(Materia materia, UsuarioAcademico profesor) {
        try {
            this.persistencia.iniciarTransaccion();
            profesor.agregarMateria(materia);
            this.persistencia.modificar(profesor);
            this.persistencia.modificar(materia);
            this.persistencia.confirmarTransaccion();
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
        }
    }

    public void quitarMateria(Materia materia, UsuarioAcademico profesor) {
        try {
            this.persistencia.iniciarTransaccion();
            profesor.quitarMateria(materia);
            this.persistencia.modificar(profesor);
            this.persistencia.modificar(materia);
            this.persistencia.confirmarTransaccion();
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
        }
    }

    public Boolean modificarUsuario(Usuario usuario, String apellido, String nombre, String documento, String correo) {

        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setDocumento(documento);
        usuario.setCorreo(correo);
        try {
            this.persistencia.iniciarTransaccion();
            this.persistencia.modificar(usuario);

            this.persistencia.confirmarTransaccion();
            return true;
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            return false;
        }
    }

    public Boolean eliminarUsuario(Usuario usuario) {

        this.persistencia.iniciarTransaccion();
        try {
            Long id = usuario.getId();
            UsuarioAcademico usuarioA = this.buscarUsuarioAcademico(id);
            Boolean asociaciones = false;
            this.persistencia.eliminar(usuario);
            if (usuarioA != null) {
                List<Pregunta> preguntas = usuarioA.getPreguntas();
                List<Respuesta> respuestas = usuarioA.getRespuestas();
                List<Voto> votos = usuarioA.getVotos();
                if (preguntas.isEmpty() && respuestas.isEmpty() && votos.isEmpty()) {
                    this.persistencia.eliminar(usuarioA);

                } else {
                    asociaciones = true;
                }
            }
            if (!asociaciones) {
                this.persistencia.eliminar(usuario);
                this.persistencia.confirmarTransaccion();
                return true;
            }
            //si no es un usuario academico es un registrador

            this.persistencia.confirmarTransaccion();
        } catch (Exception ex) {
            this.persistencia.descartarTransaccion();
            return false;
        }
        return false;
    }

    public void cambiarPassUsuario(Usuario usuario, String pass) {
        this.persistencia.iniciarTransaccion();
        usuario.setPassword(pass);
        this.persistencia.modificar(usuario);
        this.persistencia.confirmarTransaccion();

    }

    /**
     * Si no hay administrador devuelve false Si hay devuelve true
     *
     * @return
     *
     */
    public Boolean existeAdministrador() {
        List<UsuarioAcademico> lista = this.listarAdministrador();
        if (lista.isEmpty()) {
            return false;
        }
        return true;
    }

    public Usuario iniciarSesion(String password, String correo) {
        // 0= Estudiante. 1= Profesor,2=Administrador , 3= Registrador
        //usuario[0]= id del usuario----- usuario[1]= tipo de usuario explicado arriba
        Boolean existe = this.existeAdministrador();
        if (!existe) {
            this.crearUsuario("Admin".toUpperCase(), "BitNet".toUpperCase(), "admin@admin.com", "000000", Hash.MD5("adminBitNet"), Usuario.ADMINISTRADOR);
        }
        List<Usuario> listUsuario = this.listarUsuarios();
        for (Usuario usuarioA : listUsuario) {
            String passUsuario = usuarioA.getPassword();
            String correoUsuario = usuarioA.getCorreo();
            if (correoUsuario.equals(correo) && passUsuario.equals(password)) {
                return usuarioA;
            }
        }

        return null;
    }

    public Boolean esUsuarioAdministrador(Usuario a) {
        UsuarioAcademico e = this.buscarUsuarioAcademico(a.getId());
        if ((e != null) && (a.getTipoUsuario().equals(Usuario.ADMINISTRADOR))) {
            return true;
        }
        return false;
    }

    public Boolean esUsuarioProfesor(Usuario a) {
        UsuarioAcademico e = this.buscarUsuarioAcademico(a.getId());
        if ((e != null) && (a.getTipoUsuario().equals(Usuario.PROFESOR))) {
            return true;
        }
        return false;
    }

    public Boolean esUsuarioEstudiante(Usuario a) {
        UsuarioAcademico e = this.buscarUsuarioAcademico(a.getId());
        if ((e != null) && (a.getTipoUsuario().equals(Usuario.ESTUDIANTE))) {
            return true;
        }
        return false;
    }

    public Boolean esUsuarioRegistrador(Usuario a) {
        UsuarioAcademico e = this.buscarUsuarioAcademico(a.getId());
        if ((e == null) && (a.getTipoUsuario().equals(Usuario.REGISTRADOR))) {
            return true;
        }
        return false;
    }

    public Set<Materia> obtenerMateriasProfesor(Usuario usuario) {
        UsuarioAcademico u = this.buscarUsuarioAcademico(usuario.getId());
        if (u.getTipoUsuario().equals(Usuario.PROFESOR)) {
            return u.getMaterias();
        }
        return null;
    }
}
