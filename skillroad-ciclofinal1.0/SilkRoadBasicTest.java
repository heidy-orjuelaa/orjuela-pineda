
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SilkRoadBasicTest {

    private SilkRoad road;

    @Before
    public void setUp() {
        // Crea una ruta de longitud 10
        road = new SilkRoad(10);
        road.makeInvisible(); // evita gráficos si los hubiera
    }

    @Test
    public void testPlaceStore() {
        road.placeStore(4, 100);
        assertEquals(1, road.stores().length);
        assertEquals(4, road.stores()[0].getLocation());
    }

    @Test
    public void testPlaceRobot() {
        road.placeRobot(2);
        assertEquals(1, road.robots().length);
        assertEquals(2, road.robots()[0].getLocation());
    }

    @Test
    public void testMoveRobot() {
        road.placeRobot(1);
        road.moveRobot(1, 3); // lo movemos 3 unidades
        assertEquals(4, road.robots()[0].getLocation());
    }

    @Test
    public void testResupplyStores() {
        road.placeStore(5, 50);
        int before = road.stores()[0].getTenges();
        road.resupplyStores(); // debe restaurar los tenges iniciales
        assertEquals(before, road.stores()[0].getTenges());
    }
}
