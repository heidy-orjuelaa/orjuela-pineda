

/**
 * Clase que representa un robot en la Ruta de la Seda.
 * Cada robot tiene una ubicación y una ganancia acumulada.
 * Puede moverse y, si es el de mayor ganancia, parpadear.
 */
public class Robot {

    // ======================
    // Atributos
    // ======================
    private int initialLocation;  // Ubicación inicial
    private int currentLocation;  // Ubicación actual
    private int profit;           // Ganancia acumulada
    private Circle shape;         // Representación gráfica del robot
    private boolean visible;      // Estado de visibilidad
    private boolean blinking;     // Indica si el robot está parpadeando
    
    // ======================
    // Constructores
    // ======================

    /**
     * Crea un robot en la ubicación dada con color azul por defecto.
     */
    public Robot(int location) {
        // Llama al constructor principal con color por defecto
        this(location, "blue");
    }

    /**
     * Crea un robot en la ubicación dada con un color específico.
     */
    public Robot(int location, String color) {
        this.initialLocation = location;
        this.currentLocation = location;
        this.profit = 0;
        this.visible = false;
        this.blinking = false;

        // Crear representación visual (usando shapes)
        this.shape = new Circle();
        this.shape.changeColor(color);
        this.shape.moveHorizontal(location);
    }

    // ======================
    // Métodos 
    // ======================

    /**
     * Mueve el robot una cierta cantidad de metros.
     */
    public void move(int meters) {
        this.currentLocation += meters;
        this.shape.moveHorizontal(meters);
    }

    /**
     * Regresa el robot a su posición inicial.
     */
    public void resetPosition() {
        int delta = initialLocation - currentLocation;
        this.currentLocation = initialLocation;
        this.shape.moveHorizontal(delta);
    }

    /**
     * Aumenta la ganancia del robot.
     */
    public void addProfit(int amount) {
        this.profit += amount;
    }

    /**
     * Devuelve la ganancia acumulada del robot.
     */
    public int getProfit() {
        return this.profit;
    }

    /**
     * Devuelve la ubicación actual del robot.
     */
    public int getLocation() {
        return this.currentLocation;
    }

    /**
     * Hace visible el robot en el lienzo.
     */
    public void show() {
        this.visible = true;
        this.shape.makeVisible();
    }

    /**
     * Hace invisible el robot en el lienzo.
     */
    public void hide() {
        this.visible = false;
        this.shape.makeInvisible();
    }

    /**
     * Activa el parpadeo del robot.
     */
    public void blink() {
        this.blinking = true;
        // Temporizador para simular el parpadeo (falta implementar)
    }

    /**
     * Detiene el parpadeo del robot.
     */
    public void stopBlink() {
        this.blinking = false;
        this.shape.makeVisible();
    }

    /**
     * Verifica si el robot está parpadeando.
     */
    public boolean isBlinking() {
        return this.blinking;
    }

}
