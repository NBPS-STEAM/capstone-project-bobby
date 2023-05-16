import javax.swing.JFrame;

public class Tetris extends JFrame {

    public Tetris() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 800);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Tetris tetris = new Tetris();
            tetris.setVisible(true);
        });
    }
}
