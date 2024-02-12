import java.util.Random;

//Game actions
public class GameBehavior {
    public GameBehavior(GameData target) {
        data = target;
        isFirst = true;
    }

    public void firstClick(int x, int y) {
        setGame(x, y);
        clickOnPos(x, y);
    }
    public void setMineCount(int count) {
        targetMine = count;
    }
    private void setGame(int sx, int sy) {
        Random random = new Random();
        nowMine = 0;
        while (nowMine != targetMine) {
            int x = random.nextInt(data.width), y = random.nextInt(data.height);
            if (x == sx && y == sy)
                continue;
            if (data.getValueOnPos(x, y) == -1)
                continue;
            data.getCell(x, y).value = -1;
            ++nowMine;
        }
        for (int x = 0; x != data.width; ++x) {
            for (int y = 0; y != data.height; ++y) {
                if (getNearMineCount(x, y) != 0 && data.getValueOnPos(x, y) == 0) {
                    data.getCell(x, y).value = getNearMineCount(x, y);
                }
            }
        }
    }

    //These methods are easy to read so there's no explanation
    public void clickOnPos(int x, int y) {
        if (data.getValueOnPos(x, y) == 0) {
            data.getCell(x, y).isConnectedWith = data.getCell(x, y);
            checkIfIsConnected(x, y);
            for (int i = 0; i != data.width; ++i) {
                for (int j = 0; j != data.height; ++j) {
                    if (data.getCell(i, j).isConnectedWith == data.getCell(x, y)) {
                        data.setCellState(i, j, State.Opened);
                        data.getCell(i, j).isChecked = false;
                        data.getCell(i, j).isConnectedWith = null;
                    }
                }
            }
        }
        if (ifBelongToNumber(x, y)) {
            data.setCellState(x, y, State.Opened);
        }
        if (data.getValueOnPos(x, y) == -1) {
            data.state = State.Fail;
        }
    }
    public void rightClick(int x, int y) {
        if (data.getCellState(x, y) == State.Unopened)
            data.setCellState(x, y, State.Flag);
        else if (data.getCellState(x, y) == State.Flag)
            data.setCellState(x, y, State.Unopened);
    }
    public boolean ifWin() {
        int nowMarked = 0, rightMarked = 0;
        for (int x = 0; x != data.width; ++x) {
            for (int y = 0; y != data.height; ++y) {
                if (data.getCellState(x,y) == State.Flag) {
                    ++nowMarked;
                    if (data.getCell(x, y).value == -1)
                        ++rightMarked;
                }
            }
        }
        return rightMarked == nowMine && nowMarked == rightMarked;
    }

    //Debug Area
    //To be continued
    public void changeDebugState() {
        data.isDebug = !data.isDebug;
    }

    //Completed on 2024.2.12
    //Open the connected area by recursive calling the nearby cells
    //Every call will try to check their nearby cells
    //By checking the isChecked it avoid StackOverFlow
    //If the nearby cell is empty, that cell's isConnectedWith will be set to current cell's
    //And the beginning cell's is set to itself
    //So finally all the cells' isConnectedWith including the beginning's will be set to the same, the beginning
    private void checkIfIsConnected(int x, int y) {
        for (int XOffset = -1; XOffset <= 1; ++XOffset) {
            for (int YOffset = -1; YOffset <= 1; ++YOffset) {
                if (XOffset == 0 && YOffset == 0)
                    continue;
                if (data.getValueOnPos(x, y) != 0)
                    continue;
                if (ifIsInbound(x + XOffset, y + YOffset)) {
                    if (data.getValueOnPos(x + XOffset, y + YOffset) == 0) {
                        data.setConnectedCell(x + XOffset, y + YOffset, x, y);
                        data.getCell(x, y).isChecked =true;
                        if (!data.getCell(x + XOffset, y + YOffset).isChecked)
                            this.checkIfIsConnected(x + XOffset, y + YOffset);
                    }
                    if (ifBelongToNumber(x + XOffset, y + YOffset) && data.getValueOnPos(x, y) == 0) {
                        data.setConnectedCell(x + XOffset, y + YOffset, x, y);
                        data.getCell(x, y).isChecked =true;
                    }
                }
            }
        }
    }
    private int getNearMineCount(int x, int y) {
        int count = 0;
        for (int XOffset = -1; XOffset <= 1; ++XOffset) {
            for (int YOffset = -1; YOffset <= 1; ++YOffset) {
                if (XOffset == 0 && YOffset == 0)
                    continue;
                if (ifIsInbound(x + XOffset, y + YOffset)) {
                    if (data.getValueOnPos(x + XOffset, y + YOffset) == -1)
                        ++count;
                }
            }
        }
        return count;
    }
    private boolean ifBelongToNumber(int x, int y) {
        switch (data.getValueOnPos(x, y)) {
            case 1, 2, 3, 4, 5, 6, 7, 8-> { return true; }
            default -> { return false; }
        }
    }
    //To avoid the OutOfRange
    private boolean ifIsInbound(int x, int y) {
        return (x >= 0 && x < data.width) && (y >= 0 && y < data.height);
    }
    public boolean isFirst;
    private final GameData data;
    private int nowMine;
    private int targetMine;
}
