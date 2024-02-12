public class DataCell {
    public DataCell() {
        value = 0;
        isChecked = false;
        isConnectedWith = null;
        state = State.Unopened;
    }
    public boolean isChecked;
    public int value;
    public DataCell isConnectedWith;
    public int state;
}
