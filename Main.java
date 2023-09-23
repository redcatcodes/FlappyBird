import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlappyBird extends JPanel implements KeyListener {

    private Bird bird;
    private Pipe[] pipes;
    private int score;

    public FlappyBird() {
        bird = new Bird();
        pipes = new Pipe[2];
        pipes[0] = new Pipe(300, 0, 100);
        pipes[1] = new Pipe(500, 200, 100);
        score = 0;
    }

    public void update() {
        bird.update();

        for (int i = 0; i < pipes.length; i++) {
            pipes[i].update();

            if (bird.collidesWith(pipes[i])) {
                gameOver();
            }
        }

        if (bird.getY() > getHeight()) {
            gameOver();
        }

        score++;
    }

    public void draw(Graphics g) {
        bird.draw(g);

        for (int i = 0; i < pipes.length; i++) {
            pipes[i].draw(g);
        }

        g.drawString("Score: " + score, 10, 20);
    }

    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.flap();
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void gameOver() {
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.addKeyListener(game);
        frame.setSize(640, 480);
        frame.setVisible(true);

        while (true) {
            game.update();
            game.repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
