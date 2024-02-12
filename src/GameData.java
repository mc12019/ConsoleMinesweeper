public class GameData {
    public GameData(int width, int height) {
        this.width = width;
        this.height = height;
        game = new DataCell[width][height];
        for (int i = 0; i != width; ++i) {
            for (int j = 0; j != height; ++j)
                game[i][j] = new DataCell();
        }
    }
    public void setConnectedCell(int x, int y, int tx, int ty) {
        game[x][y].isConnectedWith = game[tx][ty].isConnectedWith;
    }
    public void setCellState(int x, int y, int state) {
        game[x][y].state = state;
    }
    public int width;
    public int height;
    public int state;
    public DataCell getCell(int x, int y) {
        return game[x][y];
    }
    public int getCellState(int x, int y) {
        return game[x][y].state;
    }
    public int getValueOnPos(int x, int y) {
        return game[x][y].value;
    }
    public boolean isDebug;
    private final DataCell[][] game;
}
