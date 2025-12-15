
/**
 * Clase que representa una tienda en la Ruta de la Seda.
 * Cada tienda tiene una ubicación, cantidad de tenges (dinero)
 * y un contador de cuántas veces ha sido desocupada.
 */
public class Store {

    // ======================
    // Atributos
    // ======================
    private int location;         // Ubicación en la ruta
    private int tenges;           // Cantidad de dinero disponible
    private int initialTenges;    // Cantidad inicial de dinero (para reabastecer)
    private int timesEmptied;     // Veces que la tienda fue desocupada
    private Rectangle shape;      // Representación visual de la tienda
    private boolean visible;      // Estado de visibilidad

    // ======================
    // Constructor
    // ======================

    /**
     * Crea una tienda en una ubicación con cierta cantidad de tenges.
     */
    public Store(int location, int tenges) {
        this.location = location;
        this.tenges = tenges;
        this.initialTenges = tenges;
        this.timesEmptied = 0;
        this.visible = false;

        // Crear representación gráfica
        this.shape = new Rectangle();
        this.shape.changeColor("green");
        this.shape.moveHorizontal(location);
    }

    // ======================
    // Métodos principales
    // ======================

    /**
     * Reduce el número de tenges en la tienda.
     * Si queda en 0, aumenta el contador de desocupaciones
     * y cambia su color para indicar que está vacía.
     */
    public void withdraw(int amount) {
        if (amount <= tenges) {
            tenges -= amount;
        } else {
            tenges = 0; // No puede quedar negativa
        }

        if (tenges == 0) {
            timesEmptied++;
            showEmpty();
        }
    }

    /**
     * Reabastece la tienda a su cantidad inicial de tenges.
     * Cambia su color para indicar que está nuevamente disponible.
     */
    public void resupply() {
        this.tenges = this.initialTenges;
        showFull();
    }

    // ======================
    // Getters y Setters
    // ======================

    /**
     * Devuelve la cantidad de dinero disponible en la tienda.
     */
    public int getTenges() {
        return this.tenges;
    }

    /**
     * Devuelve cuántas veces fue desocupada esta tienda.
     */
    public int getTimesEmptied() {
        return this.timesEmptied;
    }

    /**
     * Devuelve la ubicación de la tienda.
     */
    public int getLocation() {
        return this.location;
    }

    /**
     * Devuelve la cantidad inicial de tenges (para reabastecer).
     * ⚠️ Este método se agregó para que SilkRoad pueda leer este valor.
     */
    public int getInitialTenges() {
        return this.initialTenges;
    }

    /**
     * Permite modificar la cantidad actual de tenges.
     * Este método se agregó para que SilkRoad pueda actualizar el dinero.
     */
    public void setTenges(int tenges) {
        this.tenges = tenges;
    }

    // ======================
    // Métodos de apariencia
    // ======================

    /**
     * Hace visible la tienda en el lienzo.
     */
    public void show() {
        this.visible = true;
        this.shape.makeVisible();
    }

    /**
     * Hace invisible la tienda en el lienzo.
     */
    public void hide() {
        this.visible = false;
        this.shape.makeInvisible();
    }

    /**
     * Cambia el color de la tienda para indicar que está vacía.
     */
    private void showEmpty() {
        this.shape.changeColor("red"); // Tienda vacía se muestra en rojo
    }

    /**
     * Cambia el color de la tienda para indicar que está llena.
     */
    private void showFull() {
        this.shape.changeColor("green"); // Tienda llena se muestra en verde
    }

}
