package Controller;

import IO.Teclado;
import Interfaces.iController;
import Model.Archivos.Datos;
import Model.Archivos.Sesion;
import Model.Entitys.Colaborador;
import Model.Entitys.Usuario;
import Model.Proyectos.Proyectos;
import Model.Repository.RepoProjectos;
import View.View;


public class MainController implements iController {

    View view = new View();
    RepoProjectos rp = RepoProjectos.getInstance();
    SecondaryController secondaryController = new SecondaryController();


    @Override
    public void startApp() {
        switchMenuRegistroInicioSesion(view.menuRegistroInicioSesion());
    }


    @Override
    public void switchMenuRegistroInicioSesion(int opcion) {
        do {
            switch (opcion) {
                case 1:
                    RepoProjectos.load("Repositorio.bin");
                    Sesion.getInstancia();
                    view.mensajesDeInicio();
                    switchEleccionCrud(view.eleccionCRUD());
                    break;
                case 2:
                    view.menuRegistroUsuario();
                    switchMenuRegistroInicioSesion(view.menuRegistroInicioSesion());
                    break;

                case 3:
                    Teclado.imprimirCadena("Adios y gracias por usar TaskFlow");
                    break;
                default:
                    Teclado.imprimirCadena("Ups... Parece que te has equivocado, prueba otra vez.");
                    opcion = view.menuRegistroInicioSesion();
            }
        } while (opcion < 1 || opcion > 3);
    }


    public void switchEleccionCrud(int opcion) {
        switch (opcion) {
            case 1:
                Teclado.imprimirCadena("Listando proyectos...");
                switchListar(view.eleccionListarProyecto());
                switchEleccionCrud(view.eleccionCRUD());
                break;
            case 2:
                Teclado.imprimirCadena("Crear proyecto...");
                rp.crearProjecto(view.viewAñadirProjecto());
                rp.saveData();
                switchEleccionCrud(view.eleccionCRUD());
                break;
            case 3:
                Teclado.imprimirCadena("Borrando proyecto...");
                rp.borrarProyecto(view.viewBorrarProyecto());
                switchEleccionCrud(view.eleccionCRUD());
                break;
            case 4:
                Teclado.imprimirCadena("");
                Teclado.imprimirCadena("Lista de usuarios del proyecto:");
                Usuario.listarUsuarios("usuariosRegistrados");
                Teclado.imprimirCadena("");
                switchEleccionCrud(view.eleccionCRUD());
                break;
            case 5:
                Teclado.imprimirCadena("Saliendo, los cambios se han guardado correctamente.");
                rp.saveData();
                rp.save("Repositorio.bin");
                break;
        }
    }


    public void switchListar(int opcion) {
        switch (opcion) {
            case 1:
                RepoProjectos.listarProyectos(rp.getProyectos());
                break;
            case 2:
                Usuario ultimoUsuario = Sesion.getUsuarioIniciado();
                Proyectos aux = RepoProjectos.listarProyectoporNombre(rp.getProyectos());
                if (aux.getCreador().getNombre().equals(ultimoUsuario.getNombre())) {
                    Teclado.imprimirCadena(aux.toString());
                    secondaryController.switchMenuCRUDcreador(view.menuCreador(),aux);
                } else {
                    Colaborador colaborador = Colaborador.encontrarColaborador(aux, ultimoUsuario);
                    if (colaborador != null && colaborador.getUsuario().equals(ultimoUsuario.getNombre())) {
                        Teclado.imprimirCadena(aux.toString());
                        secondaryController.switchMenuColaborador(view.menuColaborador(), aux);
                    } else {
                        Teclado.imprimirCadena("No perteneces a ningun proyecto");
                    }
                }
            default:
                break;
        }
    }
}






