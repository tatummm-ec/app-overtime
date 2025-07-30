package cc.edu.unl.dto;

import cc.edu.unl.domain.Equipo;

public class EstadisticaDTO {

    private Equipo equipo;
    private int jugados = 0;
    private int ganados = 0;
    private int perdidos = 0;
    private int empatados = 0;
    private int golesFavor = 0;
    private int golesContra = 0;

    public EstadisticaDTO(Equipo equipo) {
        this.equipo = equipo;
    }

    public void agregarPartido(int golesAFavor, int golesEnContra) {
        jugados++;
        golesFavor += golesAFavor;
        golesContra += golesEnContra;

        if (golesAFavor > golesEnContra) {
            ganados++;
        } else if (golesAFavor < golesEnContra) {
            perdidos++;
        } else {
            empatados++;
        }
    }

    public int getPuntos() {
        return ganados * 3 + empatados;
    }

    public int getDiferenciaGoles() {
        return golesFavor - golesContra;
    }

    // Getters
    public Equipo getEquipo() { return equipo; }
    public int getJugados() { return jugados; }
    public int getGanados() { return ganados; }
    public int getPerdidos() { return perdidos; }
    public int getEmpatados() { return empatados; }
    public int getGolesFavor() { return golesFavor; }
    public int getGolesContra() { return golesContra; }
}
