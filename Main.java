import Controller.MainController;
import Model.Entitys.Usuario;
import Model.Repository.RepoUsuarios;
import View.View;


public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        View view = new View();

        mainController.switchMenuRegistroInicioSesion(view.menuRegistroInicioSesion());



       /* RepoUsuarios ru = RepoUsuarios.getInstance();
        ru.addUsuario(new Usuario("Carlos","Carlos","1234","c@gmail.com","0"));
        ru.addUsuario(new Usuario("Juan","Jua n","1234","j@gmail.com","a"));
        ru.save("usuarios.bin");

        RepoUsuarios.load("usuarios.bin");
        RepoUsuarios ru = RepoUsuarios.getInstance();
        System.out.println(ru.getUsuarios());*/
    }
}