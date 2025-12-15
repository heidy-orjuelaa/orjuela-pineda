


 

/**
 * SilkRoadContest (actualizada)
 *
 * Contiene:
 *  - solve(int[][] days) : int[]    -> calcula máximos por día y guarda 'days' internamente
 *  - simulate(int[][] days) : void  -> simula usando los datos pasados
 *  - simulate() : void              -> simula usando los últimos datos pasados a solve()
 *  - setSlow(long) / slow()         -> control de velocidad (slow() es atajo sin argumentos)
 *
 * Esta versión es compatible tanto con tests que llamen simulate(days)
 * como con tests que llamen simulate() sin argumentos (suponen que solve() ya fue invocado).
 */
public class SilkRoadContest {

    /** pausa entre pasos de la simulación (ms) */
    private long slow = 1000;

    /** últimos datos pasados a solve(...) — simulate() usará esto si no se le pasan datos explícitos */
    private int[][] lastDays = null;

    public SilkRoadContest() { }

    /** Establece la velocidad (ms) entre pasos de la simulación */
    public void setSlow(long millis) {
        if (millis < 0) millis = 0;
        this.slow = millis;
    }

    /** Atajo pedido por tests: llama a setSlow con un valor por defecto */
    public void slow() {
        this.slow = 1000; // valor por defecto cuando se invoca slow() sin argumentos
    }

    /**
     * Requisito 14: Calcula la máxima utilidad diaria.
     * Además guarda 'days' internamente para que simulate() pueda usarlo si se invoca sin args.
     *
     * @param days matriz days[d][i]
     * @return arreglo con la máxima utilidad por cada día
     */
    public int[] solve(int[][] days) {
        // Guardamos los datos para simulate() (aunque sean nulos/vacíos)
        this.lastDays = days;

        if (days == null || days.length == 0) {
            return new int[0];
        }

        int[] maxDailyProfit = new int[days.length];

        for (int i = 0; i < days.length; i++) {
            if (days[i] == null || days[i].length == 0) {
                maxDailyProfit[i] = 0;
                continue;
            }
            int max = days[i][0];
            for (int j = 1; j < days[i].length; j++) {
                if (days[i][j] > max) max = days[i][j];
            }
            maxDailyProfit[i] = max;
        }

        return maxDailyProfit;
    }

    /**
     * Requisito 15: Simula día a día usando los datos pasados.
     * Esta sobrecarga usa los 'lastDays' (últimos pasados a solve).
     */
    public void simulate() {
        simulate(this.lastDays);
    }

    /**
     * Simula la ejecución día a día mostrando en consola cada resultado.
     * Si days es null o vacío no hace nada (pero no lanza excepción).
     *
     * @param days datos para simular; si null -> se imprime mensaje y se retorna.
     */
    public void simulate(int[][] days) {
        if (days == null || days.length == 0) {
            System.out.println("simulate(): no hay datos para simular.");
            return;
        }

        // Obtenemos los resultados de solve (además solve guardará lastDays)
        int[] profits = solve(days);

        System.out.println("=== Simulación de la Maratón de Silk Road ===");
        for (int i = 0; i < profits.length; i++) {
            System.out.println("Día " + (i + 1) + ": utilidad máxima = " + profits[i]);

            // Pausa controlada por 'slow' (si slow==0 no hay pausa)
            try {
                if (slow > 0) Thread.sleep(slow);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulación interrumpida.");
                return;
            }
        }
        System.out.println("=== Fin de la simulación ===");
    }
}
