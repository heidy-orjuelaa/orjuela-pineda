
/**
 * Clase principal que modela el simulador de la Ruta de la Seda.
 * Implementación orientada a objetos alineada con el diagrama UML.
 * 
 * @author (Orjuela - Pineda)
 * @version 3.0
 */

import java.util.ArrayList;

public class SilkRoad {

    // ============================
    // Atributos
    // ============================
    private int length;
    private Store[] stores;
    private Robot[] robots;
    private boolean visible;

    // ============================
    // Constructores
    // ============================
    public SilkRoad(int length) {
        this.length = length;
        this.stores = new Store[0];
        this.robots = new Robot[0];
        this.visible = false;
    }

    public SilkRoad(int[][] days) {
        this(days.length);
    }

    // ============================
    // MÉTODOS DE TIENDAS
    // ============================
    public void placeStore(int location, int tenges) {
        if (location < 0 || location >= length) return;

        Store nueva = new Store(location, tenges);

        Store[] newStores = new Store[stores.length + 1];
        for (int i = 0; i < stores.length; i++) {
            newStores[i] = stores[i];
        }
        newStores[newStores.length - 1] = nueva;
        stores = newStores;

        if (visible) nueva.show();
    }

    public void removeStore(int location) {
        int count = 0;
        for (Store s : stores) {
            if (s.getLocation() != location) count++;
        }

        Store[] newStores = new Store[count];
        int idx = 0;
        for (Store s : stores) {
            if (s.getLocation() != location) {
                newStores[idx++] = s;
            } else {
                s.hide();
            }
        }
        stores = newStores;
    }

    // ============================
    // MÉTODOS DE ROBOTS
    // ============================
    public void placeRobot(int location) {
        if (location < 0 || location >= length) return;

        // Evitar duplicados
        for (Robot r : robots) {
            if (r.getLocation() == location) return;
        }

        Robot nuevo = new Robot(location, "blue");

        Robot[] newRobots = new Robot[robots.length + 1];
        for (int i = 0; i < robots.length; i++) {
            newRobots[i] = robots[i];
        }
        newRobots[newRobots.length - 1] = nuevo;
        robots = newRobots;

        if (visible) nuevo.show();
    }

    public void removeRobot(int location) {
        int count = 0;
        for (Robot r : robots) {
            if (r.getLocation() != location) count++;
        }

        Robot[] newRobots = new Robot[count];
        int idx = 0;
        for (Robot r : robots) {
            if (r.getLocation() != location) {
                newRobots[idx++] = r;
            } else {
                r.hide();
            }
        }
        robots = newRobots;
    }

    public void moveRobot(int location, int meters) {
        for (Robot r : robots) {
            if (r.getLocation() == location) {
                int nueva = r.getLocation() + meters;
                if (nueva < 0) nueva = 0;
                if (nueva >= length) nueva = length - 1;
                r.move(nueva - r.getLocation());
                break;
            }
        }
    }

    public void moveRobots() {
        // Implementación básica o se puede extender según reglas del ciclo 3
    }

    // ============================
    // MÉTODOS GET
    // ============================
    public Robot[] robots() {
        return robots;
    }

    public Store[] stores() {
        return stores;
    }

    // ============================
    // OTROS MÉTODOS
    // ============================
    public void returnRobots() {
        for (Robot robot : robots) {
            robot.resetPosition();
        }
    }

    public void resupplyStores() {
        for (Store store : stores) {
            store.setTenges(store.getInitialTenges());
        }
    }

    public void reboot() {
        stores = new Store[0];
        robots = new Robot[0];
    }

    public int profit() {
        int total = 0;
        for (Robot robot : robots) {
            total += robot.getProfit();
        }
        return total;
    }

    public void makeVisible() {
        visible = true;
        for (Store store : stores) {
            store.show();
        }
        for (Robot robot : robots) {
            robot.show();
        }
    }

    public void makeInvisible() {
        visible = false;
        for (Store store : stores) {
            store.hide();
        }
        for (Robot robot : robots) {
            robot.hide();
        }
    }

    public void finish() {
        reboot();
        visible = false;
    }

    public boolean ok() {
        return true;
    }
}
