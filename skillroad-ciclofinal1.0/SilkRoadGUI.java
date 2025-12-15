import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SilkRoadGUI extends JFrame {

    private SilkRoad silkRoad;
    private JButton addStoreButton, addRobotButton, simulateButton, resetButton;
    private JTextArea outputArea;
    private JPanel canvasPanel;

    public SilkRoadGUI() {
        silkRoad = new SilkRoad(10); // tamaño de la ruta
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Silk Road Contest");
        setSize(900, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 255, 240));

        // Panel superior con botones
        JPanel topPanel = new JPanel(new FlowLayout());
        addStoreButton = new JButton("Agregar Tienda");
        addRobotButton = new JButton("Agregar Robot");
        simulateButton = new JButton("Simular");
        resetButton = new JButton("Reiniciar");

        topPanel.add(addStoreButton);
        topPanel.add(addRobotButton);
        topPanel.add(simulateButton);
        topPanel.add(resetButton);
        add(topPanel, BorderLayout.NORTH);

        // Panel de dibujo (canvas)
        canvasPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSilkRoad(g);
            }
        };
        canvasPanel.setBackground(new Color(250, 250, 250));
        add(canvasPanel, BorderLayout.CENTER);

        // Área de salida de texto
        outputArea = new JTextArea(6, 20);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(outputArea);
        add(scroll, BorderLayout.SOUTH);
    }

    private void prepareActions() {
        addStoreButton.addActionListener(e -> addStore());
        addRobotButton.addActionListener(e -> addRobot());
        simulateButton.addActionListener(e -> simulate());
        resetButton.addActionListener(e -> reset());
    }

    // ===============================
    //  Lógica de botones
    // ===============================

    private void addStore() {
        String posStr = JOptionPane.showInputDialog("Ubicación de la tienda (0-9):");
        String moneyStr = JOptionPane.showInputDialog("Tenges iniciales:");

        try {
            int pos = Integer.parseInt(posStr);
            int money = Integer.parseInt(moneyStr);
            silkRoad.placeStore(pos, money);
            outputArea.append("🏪 Tienda agregada en posición " + pos + " con " + money + " tenges.\n");
            repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        }
    }

    private void addRobot() {
        String posStr = JOptionPane.showInputDialog("Ubicación del robot (0-9):");

        try {
            int pos = Integer.parseInt(posStr);
            silkRoad.placeRobot(pos);
            outputArea.append("🤖 Robot agregado en posición " + pos + ".\n");
            repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        }
    }

    private void simulate() {
        outputArea.append("\n🚀 Iniciando simulación...\n");

        for (Robot robot : silkRoad.robots()) {
            int move = (int) (Math.random() * 3) - 1;
            silkRoad.moveRobot(robot.getLocation(), move);
            outputArea.append("🤖 Robot en posición " + robot.getLocation() + " se mueve " + move + " lugares.\n");
        }

        outputArea.append("💰 Ganancia total: " + silkRoad.profit() + "\n");
        repaint();
    }

    private void reset() {
        silkRoad.reboot();
        outputArea.setText("");
        outputArea.append("🔄 Simulación reiniciada.\n");
        repaint();
    }

    // ===============================
    //  Dibujo del mapa
    // ===============================

    private void drawSilkRoad(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int width = canvasPanel.getWidth();
        int height = canvasPanel.getHeight();
        int step = width / 11;
        int baseY = height / 2;

        // Dibujar la ruta principal
        g2.setStroke(new BasicStroke(10));
        g2.setColor(new Color(210, 180, 140)); // color tipo camino de tierra
        g2.drawLine(step, baseY, width - step, baseY);

        // Marcas de posición (0–100)
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        for (int i = 0; i <= 10; i++) {
            int x = step + i * step;
            g2.fillOval(x - 4, baseY - 4, 8, 8);
            g2.drawString(Integer.toString(i * 10), x - 8, baseY + 25);
        }

        // Dibujar tiendas
        for (Store s : silkRoad.stores()) {
            int x = step + s.getLocation() * step;
            g2.setColor(new Color(0, 180, 0));
            g2.fillRect(x - 15, baseY - 60, 30, 40);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.drawString("$", x - 4, baseY - 35);
        }

        // Dibujar robots
        for (Robot r : silkRoad.robots()) {
            int x = step + r.getLocation() * step;
            g2.setColor(new Color(50, 100, 255));
            g2.fillOval(x - 12, baseY + 10, 24, 24);
            g2.setColor(Color.WHITE);
            g2.drawString("R", x - 4, baseY + 27);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SilkRoadGUI gui = new SilkRoadGUI();
            gui.setVisible(true);
        });
    }
}
