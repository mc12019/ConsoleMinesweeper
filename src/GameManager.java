import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameManager {
    public static int width, height, mineCount;
    //GameManager is the place of all the game data
    //These are all in the static to make calling it easy
    public static Frame frame = new Frame("Minesweeper");
    public static GameData data;
    public static Button[][] cells;
    public static GameBehavior behavior;

    public static void main(String[] args) {
        GameProperties gp = new GameProperties();
        gp.getProperties();
        data = new GameData(height, width);
        cells = new Button[height][width];
        behavior = new GameBehavior(data);
        //Frame part
        behavior.setMineCount(mineCount);
        frame.setLocation(500, 500);
        frame.setSize(300, 200);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Layout part
        Panel cellPanel = new Panel();
        cellPanel.setLayout(new GridLayout(data.width, data.height));
        for (int i = 0; i != data.width; ++i) {
            for (int j = 0; j != data.height; ++j) {
                cells[i][j] = new Button("");
                cells[i][j].setName(i + " " + j);
                cells[i][j].addMouseListener(new CellListener());
                cellPanel.add(cells[i][j]);
            }
        }

        //Make it visible
        frame.add(cellPanel);
        frame.setVisible(true);
    }


}