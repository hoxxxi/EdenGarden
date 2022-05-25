import java.io.File;

public class Main {
    public static void main(String[] args) {
        File gardenFile = new File(args[0]);
        File flowersFile = new File(args[1]);

        Garden garden = new Garden(gardenFile);
        garden.plantFlowers(flowersFile);
        System.out.println(garden.getLayout());
    }
}
