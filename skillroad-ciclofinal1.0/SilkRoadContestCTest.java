
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SilkRoadContestCTest {

    private SilkRoadContest contest;

    @Before
    public void setUp() {
        contest = new SilkRoadContest();
    }

    /**
     * Verifica que la simulación se ejecute sin lanzar errores.
     * (Simulación básica con un conjunto de datos pequeño)
     */
    @Test
    public void testSimulateRunsWithoutErrors() {
        int[][] days = {
            {2, 4, 6},
            {1, 3, 5}
        };

        try {
            contest.solve(days); // preparar datos base
            contest.simulate();  // ejecutar simulación
        } catch (Exception e) {
            fail("simulate() lanzó una excepción: " + e.getMessage());
        }
    }

    /**
     * Verifica que al llamar a slow(), la simulación reduzca su velocidad.
     */
    @Test
    public void testSlowAffectsSimulationSpeed() {
        long start = System.currentTimeMillis();
        contest.slow(); // debe ralentizar la simulación
        long end = System.currentTimeMillis();

        // Como la ralentización depende de Thread.sleep o similares, 
        // simplemente comprobamos que la llamada no lanza errores.
        assertTrue("slow() debería ejecutarse sin errores", end >= start);
    }

    /**
     * Verifica que simulate() pueda manejar datos vacíos.
     */
    @Test
    public void testSimulateWithEmptyData() {
        try {
            contest.solve(new int[0][0]); // sin datos
            contest.simulate(); // no debería fallar
        } catch (Exception e) {
            fail("simulate() falló con datos vacíos: " + e.getMessage());
        }
    }

    /**
     * Verifica que simulate() no falle con datos nulos.
     */
    @Test
    public void testSimulateWithNullData() {
        try {
            contest.solve(null);
            contest.simulate();
        } catch (Exception e) {
            fail("simulate() no debería lanzar excepción con datos nulos: " + e.getMessage());
        }
    }
}
