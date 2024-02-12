import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CellListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            String[] position = e.getComponent().getName().split(" ");
            if (GameManager.behavior.isFirst) {
                GameManager.behavior.firstClick(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
                GameManager.behavior.isFirst = false;
            }
            else
                GameManager.behavior.clickOnPos(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
        } else if (e.getButton() == 3) {
            if (GameManager.behavior.isFirst) {
                Frame tipBox = new Frame();
                tipBox.add(new Label("Please click left button for the first time"));
                tipBox.setLocation(500, 500);
                tipBox.setSize(100,100);
                tipBox.setVisible(true);
                tipBox.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        tipBox.setVisible(false);
                    }
                });
            }
            String[] position = e.getComponent().getName().split(" ");
            GameManager.behavior.rightClick(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
        }
        GameGraphics.updateCells();
    }

    //To be continued
    //2024.2.12
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
