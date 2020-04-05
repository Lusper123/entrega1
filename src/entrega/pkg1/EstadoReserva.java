package entrega.pkg1;

public enum EstadoReserva {
    RESERVADO ("Reservado"), DISPONIBLE ("Disponible");

    private String name;

    EstadoReserva(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
