import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.*;

public class ArchivosJSON {

    public void cargarPacientes() throws IOException {
        String archivoPacientes = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                "\\src\\db\\Pacientes.json";
        FileReader fileReader = new FileReader(archivoPacientes);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder json = new StringBuilder();
        String datos;
        while ((datos = bufferedReader.readLine()) != null) {
            json.append(datos);
        }
        bufferedReader.close();
        String archivo = json.toString();
        Gson gson = new Gson();
        Pacientes[] pacientes = gson.fromJson(archivo, Pacientes[].class);
        for (Pacientes datosPacientes : pacientes) {
            SistemaPrincipal.pacientes.add(datosPacientes);
        }
    }

    public void cargarDoctores() throws IOException {
        String archivoDoctores = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                "\\src\\db\\Doctores.json";
        FileReader fileReader = new FileReader(archivoDoctores);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder json = new StringBuilder();
        String datos;
        while ((datos = bufferedReader.readLine()) != null) {
            json.append(datos);
        }
        bufferedReader.close();
        String archivo = json.toString();
        Gson gson = new Gson();
        Doctores[] doctores = gson.fromJson(archivo, Doctores[].class);
        for (Doctores datosDoctores : doctores) {
            SistemaPrincipal.doctores.add(datosDoctores);
        }
    }

    public void cargarCitas() throws IOException {
        String archivoCitas = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                "\\src\\db\\Citas.json";
        FileReader fileReader = new FileReader(archivoCitas);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder json = new StringBuilder();
        String datos;
        while ((datos = bufferedReader.readLine()) != null) {
            json.append(datos);
        }
        bufferedReader.close();
        String archivo = json.toString();
        Gson gson = new Gson();
        Citas[] citas = gson.fromJson(archivo, Citas[].class);
        for (Citas datosCitas : citas) {
            SistemaPrincipal.citas.add(datosCitas);
        }
    }

    public void cargarUsuarios() throws IOException {
        String archivoUsuarios = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                "\\src\\db\\Usuarios.json";
        FileReader fileReader = new FileReader(archivoUsuarios);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder json = new StringBuilder();
        String datos;
        while ((datos = bufferedReader.readLine()) != null) {
            json.append(datos);
        }
        bufferedReader.close();
        String archivo = json.toString();
        Gson gson = new Gson();
        Usuarios[] usuarios = gson.fromJson(archivo, Usuarios[].class);
        for (Usuarios datosUsuaios : usuarios) {
            SistemaPrincipal.usuarios.add(datosUsuaios);
        }
    }

    public void guardarPacientes() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(SistemaPrincipal.pacientes);
            String nuevoArchivo = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                    "\\src\\db\\Pacientes.json";
            File archivo = new File(nuevoArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(archivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarDoctores() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(SistemaPrincipal.doctores);
            String nuevoArchivo = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                    "\\src\\db\\Doctores.json";
            File archivo = new File(nuevoArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(archivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarCitas() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(SistemaPrincipal.citas);
            String nuevoArchivo = "C:\\Users\\111466\\Documents\\QUINTO SEMESTRE\\COMPUTACIÓN EN JAVA\\EVIDENCIA-FINAL" +
                    "\\src\\db\\Citas.json";
            File archivo = new File(nuevoArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(archivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}