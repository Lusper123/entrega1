package entrega.pkg1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Member;

public class HorarioReservas {
    private String hora;
    private Member member;

    public HorarioReservas(String hora, Member usuario) {
        this.hora = hora;
        this.member = usuario;
    }

    public HorarioReservas(String hora) {
        this(hora, null);
    }

    public Member getMember() {
        return member;
    }

    public StringProperty getMemberProperty() {
        if (member == null) return new SimpleStringProperty(this, "");
        return new SimpleStringProperty(this, member.getLogin());
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getHora() {
        return hora;
    }

    public StringProperty getHoraProperty() {
        return new SimpleStringProperty(this, hora);
    }

    public String getEstado() {
        if (this.member == null)
            return EstadoReserva.DISPONIBLE.getName();
        return EstadoReserva.RESERVADO.getName();
    }
}
