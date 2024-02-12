import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameGraphics {
    public static void updateCells() {
        for (int i = 0; i != GameManager.data.width; ++i) {
            for (int j = 0; j != GameManager.data.height; ++j) {
                if (GameManager.data.getCellState(i, j) == State.Unopened)
                    GameManager.cells[i][j].setLabel("");
                else if (GameManager.data.getCellState(i, j) == State.Opened) {
                    GameManager.cells[i][j].setLabel("" + GameManager.data.getValueOnPos(i, j));
                    GameManager.cells[i][j].setBackground(new Color(0, 255, 0));
                }
                else if (GameManager.data.getCellState(i, j) == State.Flag)
                    GameManager.cells[i][j].setLabel("F");
                GameManager.cells[i][j].setFont(new Font("Consolas",Font.PLAIN , 25));
            }
        }
        if (GameManager.data.state == State.Fail) {
            GameManager.frame.setVisible(false);
            Frame tipBox = new Frame();
            tipBox.add(new Label("You failed, close the window and try again"));
            tipBox.setLocation(500, 500);
            tipBox.setSize(100, 100);
            tipBox.setVisible(true);
            tipBox.addWindowListener(new WindowAdapter() {
                                         @Override
                                         public void windowClosing(WindowEvent e) {
                                             System.exit(0);
                                         }
                                     }
            );
        }
        if (GameManager.behavior.ifWin()) {
            GameManager.frame.setVisible(false);
            Frame tipBox = new Frame();
            tipBox.add(new Label("You won, and you can open a new instance"));
            tipBox.setSize(100, 100);
            tipBox.setLocation(500, 500);
            tipBox.setVisible(true);
            tipBox.addWindowListener(new WindowAdapter() {
                                         @Override
                                         public void windowClosing(WindowEvent e) {
                                             System.exit(0);
                                         }
                                     }
            );
        }
    }
}
