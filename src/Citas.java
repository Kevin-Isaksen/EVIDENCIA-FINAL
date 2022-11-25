public class Citas {

    private int id;
    private String fecha, hora, motivo, namePaciente, nameDoctor;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getMotivo() { return motivo; }

    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }

    public void setHora(String hora) { this.hora = hora; }

    public String getNamePaciente() { return namePaciente; }

    public void setNamePaciente(String namePaciente) { this.namePaciente = namePaciente; }

    public String getNameDoctor() { return nameDoctor; }

    public void setNameDoctor(String nameDoctor) { this.nameDoctor = nameDoctor; }
}