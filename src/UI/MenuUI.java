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
        String titulo;
        String creador;
        String genero;
        int numVersion;

        while (true)
        {
            try
            {
                System.out.print("Ingrese el ID: ");
                id = Integer.parseInt(sc.nextLine());
                if (id < 0) throw new IdInvalidoException();
                break;
            }
            catch (IdInvalidoException e)
            {
                System.out.println(e.getMessage());
            }
            catch (NumberFormatException e)
            {
                System.out.println("❌ Error: Debe ingresar un número entero.");
            }
        }


        while (true)
        {
            try
            {
                System.out.print("Ingrese el titulo del Juego: ");
                titulo = sc.nextLine();

                // Verificamos que no esté vacío (opcional pero recomendado)
                if (titulo.trim().isEmpty())
                {
                    throw new InputMismatchException("⚠️ El título no puede estar vacío.");
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
                System.out.print("Ingrese el nombre del creador: ");
                creador = sc.nextLine();

                // Verificamos que no esté vacío (opcional pero recomendado)
                if (creador.trim().isEmpty())
                {
                    throw new InputMismatchException("⚠️ El género no puede estar vacío.");
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
                System.out.print("Ingrese el genero del Juego: ");
                genero = sc.nextLine();

                // 🎯 Verificamos si el texto contiene al menos un número
                if (genero.matches(".*\\d+.*"))
                {
                    throw new InputMismatchException("El género no puede contener números.");
                }

                // Verificamos que no esté vacío (opcional pero recomendado)
                if (genero.trim().isEmpty())
                {
                    throw new InputMismatchException("⚠️ El género no puede estar vacío.");
                }

                break;
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        while (true)
        {
            try
            {
                System.out.print("Ingrese el num de version del juego: ");
                numVersion = Integer.parseInt(sc.nextLine());

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
            catch (NumberFormatException e)
            {
                System.out.println("❌ Error: Debes ingresar un número entero para la Versión.");
            }
            catch (InputMismatchException e)
            {
                System.out.println("❌ Error: debes ingresar un tipo de dato valido.");
            }
        }


        return new Juego(id, titulo, creador, genero, numVersion);
    }
}
