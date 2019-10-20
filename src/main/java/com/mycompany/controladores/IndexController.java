/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controladores;

import com.mycompany.beans.DiplomadoFacade;
import com.mycompany.dto.DTOUniversidad;
import com.mycompany.entity.Diplomado;
import com.mycompany.entity.Estudiante;
import com.mycompany.entity.Universidad;
import com.mycompany.entity.Vista;
import com.mycompany.intefaces.DiplomadoFacadeLocal;
import com.mycompany.intefaces.EstudianteFacadeLocal;
import com.mycompany.intefaces.UniversidadFacadeLocal;
import com.mycompany.intefaces.VistaFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author E430
 */
@Named
@ViewScoped
public class IndexController implements Serializable{

    //Datos Universidad
    private String nombreU;
    private String extensionU;
    private String fotoEscudo;
    private Date fechaFundacion;
    
    //Datos Diplomado
    private String tituloD;
    private String descripcionD;
    private Date fechaInicioD;
    private Date fechaFinD;
    private int universidadSeleccionada;
    
    //datos Estudiante
    private String nombreE;
    private int edadE;
    private int diplomadoSeleccionado;
    
    //Atributo para cargar imagen
    private UploadedFile file;
    
    
    
    private List<Universidad> listaUniversidades;
    private List<Diplomado> listaDiplomados;
    private List<Estudiante> listaEstudiantes;
    private List<Vista> listaVista;
    
    @EJB
    UniversidadFacadeLocal universidadFacade;
    @EJB
    DiplomadoFacadeLocal diplomadoFacade;
    @EJB
    EstudianteFacadeLocal estudianteFacade;
    @EJB
    VistaFacadeLocal vistaFacade;
    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
        listaUniversidades = new ArrayList();
        listaDiplomados = new ArrayList();
        listaEstudiantes = new ArrayList();
        listaVista = new ArrayList();
    }
    
    @PostConstruct
    public void _init(){
        listaUniversidades = universidadFacade.findAll();
        listaDiplomados = diplomadoFacade.findAll();
        listaEstudiantes = estudianteFacade.findAll();
        listaVista = vistaFacade.obtenerVista();
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getExtensionU() {
        return extensionU;
    }

    public void setExtensionU(String extensionU) {
        this.extensionU = extensionU;
    }

    public String getFotoEscudo() {
        return fotoEscudo;
    }

    public void setFotoEscudo(String fotoEscudo) {
        this.fotoEscudo = fotoEscudo;
    }

    public Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getTituloD() {
        return tituloD;
    }

    public void setTituloD(String tituloD) {
        this.tituloD = tituloD;
    }

    public String getDescripcionD() {
        return descripcionD;
    }

    public void setDescripcionD(String descripcionD) {
        this.descripcionD = descripcionD;
    }

    public Date getFechaInicioD() {
        return fechaInicioD;
    }

    public void setFechaInicioD(Date fechaInicioD) {
        this.fechaInicioD = fechaInicioD;
    }

    public Date getFechaFinD() {
        return fechaFinD;
    }

    public void setFechaFinD(Date fechaFinD) {
        this.fechaFinD = fechaFinD;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public int getEdadE() {
        return edadE;
    }

    public void setEdadE(int edadE) {
        this.edadE = edadE;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public int getUniversidadSeleccionada() {
        return universidadSeleccionada;
    }

    public void setUniversidadSeleccionada(int universidadSeleccionada) {
        this.universidadSeleccionada = universidadSeleccionada;
    }

    public List<Universidad> getListaUniversidades() {
        return listaUniversidades;
    }

    public void setListaUniversidades(List<Universidad> listaUniversidades) {
        this.listaUniversidades = listaUniversidades;
    }

    public int getDiplomadoSeleccionado() {
        return diplomadoSeleccionado;
    }

    public void setDiplomadoSeleccionado(int diplomadoSeleccionado) {
        this.diplomadoSeleccionado = diplomadoSeleccionado;
    }

    public List<Diplomado> getListaDiplomados() {
        return listaDiplomados;
    }

    public void setListaDiplomados(List<Diplomado> listaDiplomados) {
        this.listaDiplomados = listaDiplomados;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public List<Vista> getListaVista() {
        return listaVista;
    }

    public void setListaVista(List<Vista> listaVista) {
        this.listaVista = listaVista;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        if (event != null) {
            file = event.getFile();
        }
        FacesMessage msg = new FacesMessage("Imagen Cargada en el Sistema");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void guardarUniversidad(){
        try {
            if (file != null) {
                fotoEscudo = cargarImagen(file.getFileName(), file.getInputstream());
                DTOUniversidad dtoUniversidad = new DTOUniversidad(nombreU, extensionU, fotoEscudo, fechaFundacion);
                universidadFacade.agregarUniversidad(dtoUniversidad);
                obtenerUniversidades();
                FacesMessage msg = new FacesMessage("Universidad "+nombreU+" agregada.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay archivos cargados.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void obtenerUniversidades(){
        listaUniversidades = universidadFacade.findAll();
    }
    
    public void guardarDiplomado(){
        Diplomado diplo = new Diplomado(tituloD, descripcionD, fechaInicioD, fechaFinD);
        Universidad uni = universidadFacade.find(universidadSeleccionada);
        diplo.setUniversidad(uni);
        uni.getListaDiplomados().add(diplo);
        universidadFacade.edit(uni);
        obtenerDiplomados();
        FacesMessage msg = new FacesMessage("Diplomado "+tituloD+" agregado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void obtenerDiplomados(){
        listaDiplomados = diplomadoFacade.findAll();
    }
    
    public void guardarEstudiante(){
        try {
            if (file != null) {
                fotoEscudo = cargarImagen(file.getFileName(), file.getInputstream());
                Estudiante est = new Estudiante(nombreE, edadE, fotoEscudo);
                Diplomado dip = diplomadoFacade.find(diplomadoSeleccionado);
                est.setDiplomado(dip);
                dip.getListaEstudiantes().add(est);
                diplomadoFacade.edit(dip);
                obtenerEstudiantes();
                obtenerVista();
                FacesMessage msg = new FacesMessage("Estudainte "+nombreE+" agregado.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay archivos cargados.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void obtenerEstudiantes(){
        listaEstudiantes=estudianteFacade.findAll();
    }
    
    public void obtenerVista(){
        listaVista = vistaFacade.obtenerVista();
    }
    
    //Metodo de Guardado de la Imagen del Producto
    public String cargarImagen(String fileName, InputStream in) {
        String nombreA = "";
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) context.getExternalContext().getContext();
            System.out.println("Ruta Path: " + scontext.getRealPath("/"));
            String ruta = scontext.getRealPath("\\") + "/resources/";
            try (OutputStream out = new FileOutputStream(new File(ruta + fileName))) {
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
            }
            System.out.println("El archivo se ha creado con éxito!");

            DateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
            Date date = new Date();
            String ruta1 = ruta + fileName;
            System.err.println("Ruta Inicial: " + ruta1);
            String ruta2 = ruta + dateFormat.format(date) + fileName;
            System.out.println("Archivo: " + ruta1 + "\nRenombrado a: " + ruta2);
            File archivo = new File(ruta1);
            archivo.renameTo(new File(ruta2));
            nombreA = dateFormat.format(date) + fileName;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nombreA;
    }
}
