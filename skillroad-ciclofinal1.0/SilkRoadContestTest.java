
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Clase de pruebas unitarias para la clase SilkRoadContest.
 * Verifica el correcto funcionamiento del método solve(),
 * encargado de calcular la máxima utilidad diaria.
 * 
 * @author (Orjuela-Pineda)
 * @version 3.0
 */
public class SilkRoadContestTest {

    private SilkRoadContest contest;

    @Before
    public void setUp() {
        contest = new SilkRoadContest();
    }

    /**
     * Caso básico: verifica que el método solve() calcule correctamente
     * la utilidad máxima por cada día con valores positivos.
     */
    @Test
    public void testSolveBasicCase() {
        int[][] days = {
            {3, 5, 2},
            {4, 1, 7},
            {0, 6, 6}
        };

        int[] expected = {5, 7, 6};
        assertArrayEquals("Debe devolver los máximos por día", expected, contest.solve(days));
    }

    /**
     * Caso con valores negativos: debe seleccionar el menor número negativo (más cercano a cero)
     * como máxima utilidad de cada día.
     */
    @Test
    public void testSolveWithNegativeValues() {
        int[][] days = {
            {-5, -2, -10},
            {-3, -7, -1}
        };

        int[] expected = {-2, -1};
        assertArrayEquals("Debe manejar correctamente los negativos", expected, contest.solve(days));
    }

    /**
     * Caso con matriz vacía: debe devolver un arreglo vacío.
     */
    @Test
    public void testSolveEmptyMatrix() {
        int[][] days = new int[0][0];
        int[] expected = new int[0];

        assertArrayEquals("Debe manejar una matriz vacía", expected, contest.solve(days));
    }

    /**
     * Caso con entrada nula: debe devolver un arreglo vacío sin generar excepciones.
     */
    @Test
    public void testSolveNullInput() {
        int[] expected = new int[0];
        assertArrayEquals("Debe manejar una entrada nula", expected, contest.solve(null));
    }
}
