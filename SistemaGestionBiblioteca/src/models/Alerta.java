package models;

import enums.NivelAlerta;

public class Alerta {
    private final NivelAlerta nivel;
    private final String mensaje;

    public Alerta(NivelAlerta nivel, String mensaje) {
        this.nivel = nivel;
        this.mensaje = mensaje;
    }

    public String formatear() {
        return "[" + nivel.name() + "] " + mensaje;
    }
}
