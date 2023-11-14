import java.io.*;
import java.util.Scanner;

public class BibliotecaApp {
    private static String usuarioActual = null;
    private static int librosPrestados = 0; // Lleva el registro de la cantidad de libros prestados por el usuario

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú
            System.out.println("----- BibliotecaApp Menu -----");
            System.out.println("1. Modo registro");
            System.out.println("2. Ingresar/Salir");
            System.out.println("3. Modo selección");
            System.out.println("4. Modo préstamo");
            System.out.println("5. Modo perfil");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            // Leer la opción seleccionada
            opcion = scanner.nextInt();

            // Realizar acciones según la opción seleccionada
            switch (opcion) {
                case 1:
                    modoRegistro(scanner);
                    break;
                case 2:
                    modoIngresarSalir(scanner);
                    break;
                case 3:
                    if (usuarioActual != null) {
                        modoSeleccion(scanner);
                    } else {
                        System.out.println("Debe iniciar sesión para acceder a esta opción.");
                    }
                    break;
                case 4:
                    if (usuarioActual != null) {
                        modoPrestamo(scanner);
                    } else {
                        System.out.println("Debe iniciar sesión para acceder a esta opción.");
                    }
                    break;

                case 5:
                    if (usuarioActual != null) {
                        modoPerfil();
                    } else {
                        System.out.println("Debe iniciar sesión para acceder a esta opción.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo de BibliotecaApp. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void modoRegistro(Scanner scanner) {
        // Lógica para el modo de registro
        System.out.println("Modo Registro seleccionado.");

        // Crear un usuario nuevo
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.next();

        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.next();

        // Seleccionar el plan
        System.out.println("Seleccione un plan:");
        System.out.println("1. Gratis");
        System.out.println("2. Premium");
        System.out.print("Ingrese el número correspondiente al plan: ");
        int planSeleccionado = scanner.nextInt();

        String plan;
        if (planSeleccionado == 1) {
            plan = "Gratis";
        } else if (planSeleccionado == 2) {
            plan = "Premium";
        } else {
            System.out.println("Opción no válida. Seleccionando plan Gratis por defecto.");
            plan = "Gratis";
        }

        // Guardar la información en un archivo CSV
        guardarUsuarioEnCSV(nombreUsuario, contrasena, plan);
        System.out.println("Usuario registrado con éxito.");
    }

    // ... (código existente)

    private static void definirHorarioEntrega(Scanner scanner) {
        System.out.println("Definir horario de entrega (Implementación básica)");
    }

    private static void definirSucursalRecoger(Scanner scanner) {
        System.out.println("Definir sucursal para recoger el préstamo (Implementación básica)");
    }

    private static void imprimirListadoPrestamo() {
        System.out.println("Imprimir listado de préstamo (Implementación básica)");
    }

    private static void seleccionarDireccionEnvio(Scanner scanner) {
        System.out.println("Seleccionar dirección de envío (Implementación básica)");
    }

    private static void definirHorarioPasar(Scanner scanner) {
        System.out.println("Definir horario de pasar por el préstamo (Implementación básica)");
    }

    // ... (resto del código)

    private static void modoIngresarSalir(Scanner scanner) {
        // Lógica para el modo Ingresar/Salir
        System.out.println("Modo Ingresar/Salir seleccionado.");

        if (usuarioActual == null) {
            // Si no hay un usuario actual, solicitar ingreso
            System.out.print("Ingrese el nombre de usuario: ");
            String nombreUsuario = scanner.next();
            System.out.print("Ingrese la contraseña: ");
            String contrasena = scanner.next();

            if (validarCredenciales(nombreUsuario, contrasena)) {
                usuarioActual = nombreUsuario;
                System.out.println("Ingreso exitoso. ¡Bienvenido, " + usuarioActual + "!");
            } else {
                System.out.println("Credenciales incorrectas. Vuelve a intentarlo.");
            }
        } else {
            // Si hay un usuario actual, ofrecer salir
            System.out.println("¿Desea salir? (S/N)");
            String respuesta = scanner.next().toUpperCase();

            if (respuesta.equals("S")) {
                usuarioActual = null;
                System.out.println("Salida exitosa. ¡Hasta luego!");
            }
        }
    }

    private static boolean validarCredenciales(String nombreUsuario, String contrasena) {
        // Validar las credenciales del usuario consultando el archivo CSV
        String csvFile = "usuarios.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos[0].equals(nombreUsuario) && datos[1].equals(contrasena)) {
                    return true; // Credenciales válidas
                }
            }
        } catch (IOException e) {
            System.out.println("Error al validar las credenciales.");
            e.printStackTrace();
        }

        return false; // Credenciales inválidas
    }

    private static void modoSeleccion(Scanner scanner) {
        // Lógica para el modo de selección
        System.out.println("Modo Selección seleccionado.");
        char opcion;

        do {
            System.out.println("a. Agregar un libro");
            System.out.println("b. Agregar revista");
            System.out.println("c. Vaciar lista");
            System.out.println("Presiona 'q' para salir");

            System.out.print("Selecciona una opción: ");
            opcion = scanner.next().charAt(0);

            switch (opcion) {
                case 'a':
                    if (agregarLibro()) {
                        System.out.println("Libro agregado con éxito.");
                    } else {
                        System.out.println("No se pudo agregar el libro. Límite de libros alcanzado.");
                    }
                    break;
                case 'b':
                    agregarRevista();
                    break;
                case 'c':
                    vaciarLista();
                    System.out.println("Lista vaciada.");
                    break;
                case 'q':
                    System.out.println("Saliendo del Modo Selección.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

        } while (opcion != 'q');
    }

    private static boolean agregarLibro() {
        // Lógica para agregar un libro
        if (usuarioActual.equals("Premium") && librosPrestados >= 5) {
            System.out.println("Límite de libros para usuarios Premium alcanzado.");
            return false;
        } else if (usuarioActual.equals("Gratis") && librosPrestados >= 3) {
            System.out.println("Límite de libros para usuarios Gratis alcanzado.");
            return false;
        }

        // Aquí podrías agregar la lógica para ingresar detalles del libro
        // y guardarlos en algún lugar (puede ser en un archivo o una lista, por
        // ejemplo).

        librosPrestados++;
        return true;
    }

    private static void agregarRevista() {
        // Lógica para agregar una revista
        // Puedes agregar la lógica necesaria aquí para ingresar detalles de la revista
        // y guardarlos en algún lugar (puede ser en un archivo o una lista, por
        // ejemplo).
    }

    private static void vaciarLista() {
        // Lógica para vaciar la lista de libros y revistas
        // Puedes agregar la lógica necesaria aquí para borrar todos los libros y
        // revistas
        // asociados al usuario actual.
        librosPrestados = 0;
    }

    private static void modoPrestamo(Scanner scanner) {
        // Lógica para el modo de préstamo
        System.out.println("Modo Préstamo seleccionado.");
        char opcion;

        do {
            System.out.println("a. Definir días de entrega");
            System.out.println("b. Definir horario de entrega (Premium)");
            System.out.println("c. Definir sucursal para recoger el préstamo (No premium)");
            System.out.println("d. Imprimir listado de préstamo");
            System.out.println("e. Seleccionar dirección de envío (Premium)");
            System.out.println(
                    "f. Definir si va a pasar por el préstamo a las 12 o 24 horas de haber realizado la solicitud (No premium)");
            System.out.println("Presiona 'q' para salir");

            System.out.print("Selecciona una opción: ");
            opcion = scanner.next().charAt(0);

            switch (opcion) {
                case 'a':
                    definirDiasEntrega(scanner);
                    break;
                case 'b':
                    if (usuarioActual.equals("Premium")) {
                        definirHorarioEntrega(scanner);
                    } else {
                        System.out.println("Opción no válida para usuarios no premium.");
                    }
                    break;
                case 'c':
                    if (!usuarioActual.equals("Premium")) {
                        definirSucursalRecoger(scanner);
                    } else {
                        System.out.println("Opción no válida para usuarios premium.");
                    }
                    break;
                case 'd':
                    imprimirListadoPrestamo();
                    break;
                case 'e':
                    if (usuarioActual.equals("Premium")) {
                        seleccionarDireccionEnvio(scanner);
                    } else {
                        System.out.println("Opción no válida para usuarios no premium.");
                    }
                    break;
                case 'f':
                    if (!usuarioActual.equals("Premium")) {
                        definirHorarioPasar(scanner);
                    } else {
                        System.out.println("Opción no válida para usuarios premium.");
                    }
                    break;
                case 'q':
                    System.out.println("Saliendo del Modo Préstamo.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 'q');
    }

    private static void definirDiasEntrega(Scanner scanner) {
        // Lógica para definir días de entrega
        int maxDias = (usuarioActual.equals("Premium")) ? 50 : 30;
        System.out.print("Ingrese los días de entrega (máximo " + maxDias + " días): ");
        int diasEntrega = scanner.nextInt();

        if (diasEntrega <= maxDias) {
            System.out.println("Días de entrega definidos con éxito.");
        } else {
            System.out.println("El número de días excede el límite máximo permitido.");
        }
    }

    // Aquí puedes agregar las demás funciones correspondientes a las opciones b, c,
    // d, e, y f.
    // Te he proporcionado la estructura básica y la primera función para que puedas
    // continuar.

    private static void modoPerfil() {
        // Lógica para el modo de perfil
        System.out.println("Modo Perfil seleccionado.");
        // Puedes agregar aquí la lógica para mostrar y editar el perfil del usuario
    }

    private static void guardarUsuarioEnCSV(String nombreUsuario, String contrasena, String plan) {
        // Guardar la información del usuario en un archivo CSV
        String csvFile = "usuarios.csv";

        try (FileWriter writer = new FileWriter(csvFile, true)) {
            // Si el archivo no existe, agregar encabezados
            File file = new File(csvFile);
            if (file.length() == 0) {
                writer.append("NombreUsuario,Contrasena,Plan\n");
            }

            // Agregar la información del usuario
            writer.append(nombreUsuario).append(",").append(contrasena).append(",").append(plan).append("\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario en el archivo CSV.");
            e.printStackTrace();
        }
    }
}
