public class lifeView {
    public static void main(String[] args) {
        lifeModel model = new lifeModel();
        model.addTile(0,0);
        model.addTile(1, 1);
        model.addTile(1, 0);
        model.addTile(0, 1);
        model.printBoard();
        model.runSimulation1Step();
        model.printBoard();
    }
}