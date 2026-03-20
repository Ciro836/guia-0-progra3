package UI;

import Excepciones.IdInvalidoException;
import Excepciones.IdentificadorDuplicadoException;
import Excepciones.NumVersionInvalidoException;
import Excepciones.StringVacioException;
import Modelo.Expansion;
import Modelo.Juego;
import Modelo.Media;
import Repositorios.RepositorioMedia;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private void procesarOpcion(int opcion)
    {
        switch (opcion)
        {
            case 1 -> agregarJuego();
            case 2 -> agregarExpansion();
            case 3 -> eliminarMedia();
            case 4 -> mostrarColeccionOrdenadaPorTitulo();
            case 5 -> filtrarMediaPorGenero();
            case 999 -> System.out.println("\nSaliendo del sistema...");
            default -> System.out.println("\nIngrese una opcion correcta...");
        }
    }

    private void agregarJuego()
    {
        Juego juego = crearJuego();

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

    private Juego crearJuego()
    {
        System.out.println("CREACION DE JUEGO\n");

        int id = pedirID();
        String titulo = pedirStringNoVacio("Ingrese el nombre del titulo: ", "titulo");
        String creador = pedirStringNoVacio("Ingrese el nombre del creador: ", "creador");
        String genero = pedirGenero();

        int numVersion;

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
                    throw new StringVacioException(nombreCampo);
                }
                break;
            }
            catch (StringVacioException e)
            {
                System.out.println(e.getMessage());
            }
        }

        return entrada;
    }

    private int pedirID()
    {
        int id;

        while (true)
        {
            try
            {
                id = pedirEntero("Ingresa el ID: ");

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
        return id;
    }

    private String pedirGenero()
    {
        String genero;

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
        return genero;
    }

    private void agregarExpansion()
    {
        Expansion expansion = crearExpansion();

        try
        {
            repositorio.agregar(expansion);
        }
        catch (IdentificadorDuplicadoException e)
        {
            System.out.println("\nError al agregar la expansion: " + e.getMessage());
        }
    }

    private Expansion crearExpansion()
    {
        System.out.println("CREACION DE EXPANSION\n");

        int id = pedirID();
        String titulo = pedirStringNoVacio("Ingrese el titulo de la expansion: ", "titulo");
        String creador = pedirStringNoVacio("Ingrese el nombre del creador: ", "creador");
        String genero = pedirGenero();
        LocalDateTime fecha = pedirFecha();

        return new Expansion(id, titulo, creador, genero, fecha);
    }

    private LocalDateTime pedirFecha()
    {
        while (true)
        {
            try
            {
                int anio = pedirEntero("Año (ej. 2024): ");
                int mes = pedirEntero("Mes (1-12): ");
                int dia = pedirEntero("Día (1-31): ");
                int hora = pedirEntero("Hora (0-23): ");
                int min = pedirEntero("Minutos (0-59): ");

                // LocalDateTime.of valida automáticamente la lógica del calendario
                return LocalDateTime.of(anio, mes, dia, hora, min);
            }
            catch (DateTimeException e)
            {
                System.out.println("❌ Error: La fecha o hora ingresada no es válida. " + e.getMessage());
                System.out.println("Por favor, intente de nuevo.\n");
            }
        }
    }

    private void eliminarMedia()
    {
        int id = pedirID();
        repositorio.eliminar(id);
    }

    private void mostrarColeccionOrdenadaPorTitulo()
    {
        repositorio.mostrar();
    }

    private void filtrarMediaPorGenero()
    {
        String genero = pedirGenero();
        repositorio.filtrarPorGenero(genero);
    }
}
