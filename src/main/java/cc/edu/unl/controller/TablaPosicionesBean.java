package cc.edu.unl.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class TablaPosicionesBean implements Serializable {

    private List<Posicion> posiciones;

    @PostConstruct
    public void init() {
        posiciones = new ArrayList<>();

        // Datos de ejemplo
        posiciones.add(new Posicion(1, "Equipo A", 10, 8, 1, 1));
        posiciones.add(new Posicion(2, "Equipo B", 10, 7, 2, 1));
        posiciones.add(new Posicion(3, "Equipo C", 10, 6, 3, 1));
        posiciones.add(new Posicion(4, "Equipo D", 10, 5, 2, 3));
        posiciones.add(new Posicion(5, "Equipo E", 10, 4, 3, 3));
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public static class Posicion {
        private int pos;
        private String equipo;
        private int pj;
        private int g;
        private int e;
        private int p;

        public Posicion(int pos, String equipo, int pj, int g, int e, int p) {
            this.pos = pos;
            this.equipo = equipo;
            this.pj = pj;
            this.g = g;
            this.e = e;
            this.p = p;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public String getEquipo() {
            return equipo;
        }

        public void setEquipo(String equipo) {
            this.equipo = equipo;
        }

        public int getPj() {
            return pj;
        }

        public void setPj(int pj) {
            this.pj = pj;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getE() {
            return e;
        }

        public void setE(int e) {
            this.e = e;
        }

        public int getP() {
            return p;
        }

        public void setP(int p) {
            this.p = p;
        }
    }
}
