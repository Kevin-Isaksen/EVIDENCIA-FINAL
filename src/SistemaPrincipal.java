import java.io.IOException;
import java.util.*;

public class SistemaPrincipal {

    public static List<Usuarios> usuarios = new ArrayList<>();
    public static List<Pacientes> pacientes = new ArrayList<>();
    public static List<Doctores> doctores = new ArrayList<>();
    public static List<Citas> citas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        inicioSesion();
    }

    public static void inicioSesion () throws IOException {
        ArchivosJSON archivo = new ArchivosJSON();
        int salir = 1;
        Scanner entrada = new Scanner(System.in);
        String usuario, password;
        archivo.cargarUsuarios();
        System.out.println("********** Clinica Medica **********");
        System.out.println("Inicio de sesión...\n");
        while (salir != 0) {
            System.out.print("Usuario: ");
            usuario = entrada.nextLine();
            System.out.print("Contraseña: ");
            password = entrada.nextLine();
            for (Usuarios admin: usuarios) {
                if (admin.getName().equals(usuario) && admin.getPassword().equals(password)) {
                    System.out.println();
                    System.out.println("Bienvenido(a) " + usuario + "\n");
                    archivo.cargarPacientes();
                    archivo.cargarDoctores();
                    archivo.cargarCitas();
                    menu();
                    salir = 0;
                } else if (salir == 0) {
                    break;
                } else {
                    System.out.println("El usuarios ingresado no existe\n");
                    break;
                }
            }
        }
    }

    public static void menu () {
        Scanner entrada = new Scanner(System.in);
        ArchivosJSON archivo = new ArchivosJSON();
        int opcion;
        boolean salir = false;
        while (!salir) {
            try {
                System.out.println("1. Dar de alta a un paciente");
                System.out.println("2. Dar de alta a un doctor");
                System.out.println("3. Crear cita");
                System.out.println("4. Ver citas especificas");
                System.out.println("5. Ver todas las citas");
                System.out.println("0. Salir");
                System.out.println();
                System.out.print("Escoge una opción: ");
                opcion = entrada.nextInt();
                System.out.println();
                if (opcion == 0) {
                    salir = true;
                } else if (opcion > 0 && opcion < 6) {
                    switch (opcion) {
                        case 1:
                            darAltaPacientes();
                            archivo.guardarPacientes();
                            System.out.println();
                            break;
                        case 2:
                            darAltaDoctores();
                            archivo.guardarDoctores();
                            System.out.println();
                            break;
                        case 3:
                            crearCitas();
                            archivo.guardarCitas();
                            System.out.println();
                            break;
                        case 4:
                            mostrarCitasEspecificas();
                            System.out.println();
                            break;
                        case 5:
                            mostrarCitas();
                            System.out.println();
                            break;
                    }
                }else {
                    throw new IllegalArgumentException("Opción no valida");
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Debes de teclear un número");
                entrada.next();
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public static void darAltaPacientes () {
        Scanner entrada = new Scanner(System.in);
        Pacientes paciente = new Pacientes();
        boolean verificarId = false;
        int contador;
        System.out.print("Ingresa el nombre del paciente: ");
        String name = entrada.nextLine();
        while (!verificarId) {
            contador = 0;
            System.out.print("Ingresa el id del paciente: ");
            int id = entrada.nextInt();
            for (Pacientes pac: pacientes) {
                if (pac.getId() == id) {
                    contador ++;
                }
            }
            if (contador > 0) {
                System.out.println("Ingresa un id nuevo, ya existe un paciente con id " + id);
            } else {
                paciente.setId(id);
                verificarId = true;
            }
        }
        paciente.setName(name);
        pacientes.add(paciente);
        System.out.println("Se agrego al paciente " + name + "\n");
        System.out.println("Se guardo al paciente en los archivos");
    }

    public static void darAltaDoctores () {
        Scanner entrada = new Scanner(System.in);
        Doctores doctor = new Doctores();
        boolean verificarId = false;
        int contador;
        System.out.print("Ingresa el nombre del doctor: ");
        String name = entrada.nextLine();
        System.out.print("Ingresa la especialidad del doctor: ");
        String especialidad = entrada.nextLine();
        while (!verificarId) {
            contador = 0;
            System.out.print("Ingresa el id del doctor: ");
            int id = entrada.nextInt();
            for (Doctores doc: doctores) {
                if (doc.getId() == id) {
                    contador ++;
                }
            }
            if (contador > 0) {
                System.out.println("Ingresa un id nuevo, ya existe un doctor con id " + id);
            } else {
                doctor.setId(id);
                verificarId = true;
            }
        }
        doctor.setName(name);
        doctor.setEspecialida(especialidad);
        doctores.add(doctor);
        System.out.println("Se agrego al doctor " + name + "\n");
        System.out.println("Se guardo al doctor en los archivos");
    }

    public static void crearCitas () {
        Scanner entrada = new Scanner(System.in);
        Citas cita = new Citas();
        boolean verificarPaciente = false, verificarDoctor = false, verificarId = false;
        int contadorId, contadorPaciente, contadorDoctor;
        while (!verificarPaciente) {
            contadorPaciente = 0;
            System.out.print("Nombre del paciente: ");
            String namePaciente = entrada.nextLine();
            for (Pacientes pac: pacientes) {
                if (pac.getName().contains(namePaciente)) {
                    contadorPaciente ++;
                }
            }
            if (contadorPaciente > 0) {
                cita.setNamePaciente(namePaciente);
                verificarPaciente = true;
            } else {
                System.out.println("El paciente ingresado no existe en los archivos");
            }
        }
        System.out.print("Motivo: ");
        String motivo = entrada.nextLine();
        while (!verificarDoctor) {
            contadorDoctor = 0;
            System.out.print("Nombre del doctor: ");
            String nameDoctor = entrada.nextLine();
            for (Doctores doc: doctores) {
                if (doc.getName().contains(nameDoctor)) {
                    contadorDoctor ++;
                }
            }
            if (contadorDoctor > 0) {
                cita.setNameDoctor(nameDoctor);
                verificarDoctor = true;
            } else {
                System.out.println("El doctor ingresado no existe en los archivos");
            }
        }
        System.out.print("Fecha: ");
        String fecha = entrada.nextLine();
        System.out.print("Horario: ");
        String hora = entrada.nextLine();
        while (!verificarId) {
            contadorId = 0;
            System.out.print("id de la cita: ");
            int id = entrada.nextInt();
            for (Citas cit: citas) {
                if (cit.getId() == id) {
                    contadorId ++;
                }
            }
            if (contadorId > 0) {
                System.out.println("Ingresa un id nuevo, ya existe una cita con id " + id);
            } else {
                cita.setId(id);
                verificarId = true;
            }
        }
        cita.setMotivo(motivo);
        cita.setFecha(fecha);
        cita.setHora(hora);
        citas.add(cita);
        System.out.println("¡Se creo la cita con exito! \n");
        System.out.println("Se guardo la cita en los archivos");
    }

    public static void mostrarCitas () {
        for (Citas cita: citas) {
            System.out.println("\n-----------------------------------");
            System.out.println("id: " + cita.getId());
            System.out.println("Nombre del paciente: " + cita.getNamePaciente());
            System.out.println("Nombre del doctor: " + cita.getNameDoctor());
            System.out.println("Motivo: " + cita.getMotivo());
            System.out.println("Fecha: " + cita.getFecha());
            System.out.println("Hora: " + cita.getHora());
            System.out.println("-----------------------------------");
        }
    }

    public static void mostrarCitasEspecificas () {
        Scanner entrada = new Scanner(System.in);
        boolean salir = false, verificar = false;
        while (!verificar) {
            System.out.print("Ingresa 'Doctor' o 'Paciente' para ver las citas de cada doctor o paciente: ");
            String opcion = entrada.nextLine();
            if (opcion.equals("Paciente") || opcion.equals("paciente")) {
                while (!salir) {
                    System.out.print("Ingresa el nombre del paciente: ");
                    String nombrePaciente = entrada.nextLine();
                    for (Citas cita : citas) {
                        if (cita.getNamePaciente().contains(nombrePaciente)) {
                            System.out.println("\n-----------------------------------");
                            System.out.println("id: " + cita.getId());
                            System.out.println("Nombre del paciente: " + cita.getNamePaciente());
                            System.out.println("Nombre del doctor: " + cita.getNameDoctor());
                            System.out.println("Motivo: " + cita.getMotivo());
                            System.out.println("Fecha: " + cita.getFecha());
                            System.out.println("Hora: " + cita.getHora());
                            System.out.println("-----------------------------------");
                            salir = true;
                            verificar = true;
                        }
                    }
                    if (!salir) {
                        System.out.println("No existe el paciente en los archivos \n");
                    }
                }
            } else if (opcion.equals("Doctor") || opcion.equals("doctor")) {
                while (!salir) {
                    System.out.print("Ingresa el nombre del doctor: ");
                    String nombreDoctor = entrada.nextLine();
                    for (Citas cita: citas) {
                        if (cita.getNameDoctor().contains(nombreDoctor)) {
                            System.out.println("\n-----------------------------------");
                            System.out.println("id: " + cita.getId());
                            System.out.println("Nombre del paciente: " + cita.getNamePaciente());
                            System.out.println("Nombre del doctor: " + cita.getNameDoctor());
                            System.out.println("Motivo: " + cita.getMotivo());
                            System.out.println("Fecha: " + cita.getFecha());
                            System.out.println("Hora: " + cita.getHora());
                            System.out.println("-----------------------------------");
                            salir = true;
                            verificar = true;
                        }
                    }
                    if (!salir) {
                        System.out.println("No existe el doctor en los archivos \n");
                    }
                }
            } else {
                System.out.println("El caracter o palabra ingresada es incorrecta \n");
            }
        }
    }
}