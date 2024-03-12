package uniandes.edu.co.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Scanner;
import uniandes.edu.co.proyecto.repositorios.*;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

    
    @Autowired
    private UsuarioRepository usuarioRepository;
    // aca se llaman todos los repos

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido a BancAndes");
        // Ejemplo de menú
        System.out.println("Seleccione una opción:\n1. CRUD Clientes \n2. CRUD Gerentes \n3. OTROS");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                // interfazCliente
                break;
            case 2:
                // interfazGerente
                break;
			case 3:
				//otros
        }
        scanner.close();
    }




///Clientes

	void interfazCliente(){




	}






///Gerentes

	void interfazGerente(){


		
	}
}