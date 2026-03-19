package UI;

import Excepciones.IdInvalidoException;
import Excepciones.IdentificadorDuplicadoException;
import Excepciones.NumVersionInvalidoException;
import Modelo.Juego;
import Modelo.Media;
import Repositorios.RepositorioMedia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUI // UI = User Interface
{
    private final RepositorioMedia<Media> repositorio = new RepositorioMedia<>();
    private final Scanner sc = new Scanner(System.in);

    public void iniciar()
    {
        int option = 0;

        do
        {
            System.out.println("\nMENU DE OPCIONES\n");
            System.out.println("1. Agregar un Juego");
            System.out.println("2. Agregar una expansión");
            System.out.println("3. Eliminar una media de la colección por su ID");
            System.out.println("4. Mostrar todos los elementos de la coleccion ordenados por título");
            System.out.println("5. Filtrar media por género");
            System.out.println("\n999. SALIR");

            try
            {
                System.out.print("\nIngrese una opcion: ");
                option = sc.nextInt();
                sc.nextLine();
                procesarOpcion(option);
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nError: debes ingresar un número.");
                sc.nextLine();
            }
            catch (Exception e)
            {
                System.out.println("\nError inesperado: " + e.getMessage());
            }

        } while (option != 999);
    }

    public void procesarOpcion(int opcion)
    {
        switch (opcion)
        {
            case 1 -> agregarJuego();
            case 2 -> iniciar();
            case 3 -> iniciar();
            case 4 -> iniciar();
            case 5 -> iniciar();
            case 999 -> System.out.println("\nSaliendo del sistema...");
            default -> System.out.println("\nIngrese una opcion correcta...");
        }
    }

    public void agregarJuego()
    {
        Juego juego = crearJuego();

        if (juego != null)
        {
            try
            {
                repositorio.agregar(juego);
                System.out.println("\nJuego agregado correctamente...");
            }
            catch (IdentificadorDuplicadoException e)
            {
                System.out.println("\nError al agregar el juego: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("\nError al cargar los datos del juego.");
        }
    }

    public Juego crearJuego()
    {
        System.out.println("CREACION DE JUEGO\n");

        int id;
        String genero;
        int numVersion;

        while (true)
        {
            try
            {
                id = pedirEntero("Ingrese el ID del Juego: ");

                if (id <= 0)
                {
                    throw new IdInvalidoException();
                }
                break;
            }
            catch (IdInvalidoException e)
            {
                System.out.println(e.getMessage());
            }
        }

        String titulo = pedirStringNoVacio("Ingrese el nombre del titulo: ", "titulo");
        String creador = pedirStringNoVacio("Ingrese el nombre del creador: ", "creador");


        while (true)
        {
            try
            {
                genero = pedirStringNoVacio("Ingrese el nombre del genero: ", "genero");
                if (genero.matches(".*\\d+.*"))
                {
                    throw new InputMismatchException("El género no puede contener números.");
                }
                break;
            }
            catch (InputMismatchException e)
            {
                System.out.println(e.getMessage());
            }
        }

        while (true)
        {
            try
            {
                numVersion = pedirEntero("Ingrese el número de versión: ");

                if (numVersion <= 0)
                {
                    throw new NumVersionInvalidoException();
                }
                break;
            }
            catch (NumVersionInvalidoException e)
            {
                System.out.println(e.getMessage());
            }
        }


        return new Juego(id, titulo, creador, genero, numVersion);
    }

    private int pedirEntero(String mensaje)
    {
        int numero;

        while (true)
        {
            try
            {
                System.out.print(mensaje);
                numero = Integer.parseInt(sc.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("❌ Error: Debes ingresar un número entero.");
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
            }
        }

        return numero;
    }

    private String pedirStringNoVacio(String mensaje, String nombreCampo)
    {
        String entrada;

        while (true)
        {
            try
            {
                System.out.print(mensaje);
                entrada = sc.nextLine();
                if (entrada.trim().isEmpty())
                {
                    throw new InputMismatchException();
                }
                break;
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: El " + nombreCampo + " no puede estar vacío.");
            }
        }

        return entrada;
    }
}
