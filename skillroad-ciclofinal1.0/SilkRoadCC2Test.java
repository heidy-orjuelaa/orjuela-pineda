
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Casos de prueba colectivos para el ciclo 2 de SilkRoad.
 * Adaptados a la versión orientada a objetos (con Store[] y Robot[])
 * Autores: Orjuela y Pineda (OP)
 */
public class SilkRoadCC2Test {

    private SilkRoad road;

    @Before
    public void setUp() {
        road = new SilkRoad(10); // Ruta de longitud 10
    }

    /**
     * (OP) No debe permitir colocar tiendas en ubicaciones fuera de la ruta.
     */
    @Test
    public void accordingOPShouldNotPlaceStoreOutsideBounds() {
        road.placeStore(-1, 50);
        assertEquals("No debe agregarse tienda en ubicación negativa", 0, road.stores().length);

        road.placeStore(15, 50);
        assertEquals("No debe agregarse tienda en ubicación mayor a la longitud", 0, road.stores().length);
    }

    /**
     * (OP) Debe permitir mover el robot sin salirse de la ruta.
     */
    @Test
    public void accordingOPShouldMoveRobotWithinBounds() {
        road.placeRobot(0);
        road.moveRobot(0, 20); // Mover más de la longitud
        assertEquals("El robot no debe salirse de la ruta", 9, road.robots()[0].getLocation());
    }

    /**
     * (OP) Al eliminar un robot debe desaparecer de la lista.
     */
    @Test
    public void accordingOPShouldRemoveRobot() {
        road.placeRobot(3);
        assertEquals(1, road.robots().length);
        road.removeRobot(3);
        assertEquals(0, road.robots().length);
    }

    /**
     * (OP) Al reiniciar debe limpiar completamente las tiendas y robots.
     */
    @Test
    public void accordingOPShouldRebootSystem() {
        road.placeRobot(1);
        road.placeStore(2, 50);
        road.reboot();
        assertEquals(0, road.robots().length);
        assertEquals(0, road.stores().length);
    }

    /**
     * (OP) El reabastecimiento debe restaurar el valor inicial de todas las tiendas.
     */
    @Test
    public void accordingOPShouldResupplyAllStores() {
        road.placeStore(4, 30);
        road.placeStore(6, 10);

        // Simulamos que se vacían
        for (Store s : road.stores()) {
            s.withdraw(s.getTenges());
            assertEquals(0, s.getTenges());
        }

        // Reabastecer
        road.resupplyStores();

        // Comprobar que recuperaron dinero
        for (Store s : road.stores()) {
            assertTrue("La tienda debe tener dinero nuevamente", s.getTenges() > 0);
        }
    }

    /**
     * (OP) No debe permitir colocar dos robots en la misma ubicación.
     */
    @Test
    public void accordingOPShouldNotAllowDuplicateRobots() {
        road.placeRobot(5);
        road.placeRobot(5);
        assertEquals("Sólo debe haber un robot en la ubicación 5", 1, road.robots().length);
    }
}
