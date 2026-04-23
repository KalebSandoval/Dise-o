package Controlador.coordinador;

import Controlador.interfaz.ICoordinadorAplicacion;
import Pantallas.FrmInicioSesion;
import Pantallas.FrmPago;
import Pantallas.FrmPlantillaSistema;
import Pantallas.FrmRegistrarse;
import Pantallas.FrmDetallesCompra;
import Pantallas.FrmRegistroItson;
import Pantallas.vistas.PnlCategorias;
import Pantallas.vistas.PnlConsultar;
import Pantallas.vistas.PnlConsultarEvento;
import Pantallas.vistas.PnlEventos;
import dtos.CategoriaDTO;
import dtos.EventoDTO;
import dtos.LoginDTO;
import dtos.UsuarioDTO;
import fachada.InicioSesionFachada;
import interfaces.IFachadaInicioSesion;
import java.util.ArrayList;
import java.util.List;
import interfaces.IGestionUsuariosFachada;
/**
 *
 * @author Aaron Burciaga - 262788
 * @author Brian Sandoval - 262741
 * @author Dayanara Peralta - 262695
 * @author María Valdez - 262775
 */
public class CoordinadorAplicacion implements ICoordinadorAplicacion {
    private IFachadaInicioSesion logi = InicioSesionFachada.getInstance();

    private FrmInicioSesion frmInicioSesion;
    private FrmRegistrarse frmRegistrarse;
    private FrmPago frmPago;
    private FrmPlantillaSistema frmPlantilla;
    private FrmDetallesCompra frmDetalles;
    private FrmRegistroItson frmRegistro;

    private void ocultarTodo() {
        if (frmInicioSesion != null) {
            frmInicioSesion.setVisible(false);
        }
        if (frmRegistrarse != null) {
            frmRegistrarse.setVisible(false);
        }       
        if(frmPago != null){
            frmPago.setVisible(false);
        }        
        if(frmDetalles != null){
            frmDetalles.setVisible(false);
        }
        if(frmRegistro != null){
            frmRegistro.setVisible(false);
        }
        if(frmInicioSesion != null){
            frmInicioSesion.setVisible(false);
        }
    }

    @Override
    public void iniciaSistema() {
        if (frmInicioSesion == null) {
            frmInicioSesion = new FrmInicioSesion(this);
        }
        frmInicioSesion.setVisible(true);
        frmInicioSesion.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarInicioSesion() {
        ocultarTodo();
        if (frmPlantilla != null) {
            frmPlantilla.dispose();
            frmPlantilla = null;
        }
        if (frmInicioSesion == null) {
            frmInicioSesion = new FrmInicioSesion(this);
        }
        frmInicioSesion.limpiarCampos();
        frmInicioSesion.setVisible(true);
        frmInicioSesion.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarRegistro() {
        ocultarTodo();
        if (frmRegistrarse == null) {
            frmRegistrarse = new FrmRegistrarse(this);
        }
        frmRegistrarse.setVisible(true);
        frmRegistrarse.setLocationRelativeTo(null);
    }

    @Override
    public void mostrarInicio() {
        ocultarTodo();
        if (frmPlantilla == null) {
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlCategorias(this));
        frmPlantilla.setVisible(true);
        frmInicioSesion.dispose();
    }

    @Override
    public void mostrarConsultar() {
        ocultarTodo();
        if(frmPlantilla == null){
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlConsultar(this));
        frmPlantilla.setVisible(true);
    }

    @Override
    public void mostrarInfoEvento(EventoDTO evento) {
        ocultarTodo();
        if(frmPlantilla == null){
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlConsultarEvento(this, evento));
        frmPlantilla.setVisible(true);       
    }
    
    @Override
    public void mostrarDetalles(EventoDTO evento){
        ocultarTodo();
        if(frmDetalles == null){
            frmDetalles = new FrmDetallesCompra(this, evento);
        }
        frmDetalles.setVisible(true);
    }

    @Override
    public void finalizarCompra() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarEventos(CategoriaDTO categoria) {
        ocultarTodo();
        if(frmPlantilla == null){
            frmPlantilla = new FrmPlantillaSistema(this);
        }
        frmPlantilla.setContenido(new PnlEventos(this, categoria));
        frmPlantilla.setVisible(true);
    }

    @Override
    public void volverAEventos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarConsultarEvento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void volverAConsultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EventoDTO> consultarEventos() {
        return new ArrayList<>();
    }

    @Override
    public List<EventoDTO> consultarEventos(CategoriaDTO categoria) {
        return new ArrayList<>();
    }

    @Override
    public List<EventoDTO> consultarEventosProximos(Long idUsuario) {
        return new ArrayList<>();
    }

    @Override
    public List<EventoDTO> consultarEventosPasados(Long idUsuario) {
        return new ArrayList<>();
    }

    @Override
    public List<EventoDTO> consultarEventosCancelados(Long idUsuario) {
        return new ArrayList<>();
    }

    @Override
    public List<CategoriaDTO> consultarCategorias() {
        return new ArrayList<>();
    }
    @Override
    public UsuarioDTO iniciarSesion(String correo, String contrasenia){
        return logi.verificarUsuario(correo, contrasenia);
    }

    @Override
    public void setUsuarioSesion(UsuarioDTO usuario){
    }

    @Override
    public UsuarioDTO getUsuarioSesion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public void cerrarSesion() {
        logi.cerrarSesion();
        this.mostrarInicioSesion();
    }
}
