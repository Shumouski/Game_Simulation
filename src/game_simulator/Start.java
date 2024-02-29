package game_simulator;
import game_simulator.select.SelectSimulation;

public class Start {

    public static void main(String[] args) throws InterruptedException {
        //Метод main - запуск программы
        SelectSimulation simulation = new SelectSimulation();
        simulation.toSelect();
    }
}
