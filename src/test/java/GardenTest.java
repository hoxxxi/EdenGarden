import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenTest {

    @Test
    public void testScenario1() {
        File gardenFile = new File(getClass().getResource("garden1.txt").getPath());
        File flowersFile = new File(getClass().getResource("flowers1.txt").getPath());

        StringBuilder builder = new StringBuilder();
        builder.append("XXXXXX\n");
        builder.append("Xaia X\n");
        builder.append("XXXXXX\n");

        Garden garden = new Garden(gardenFile);
        garden.plantFlowers(flowersFile);
        assertEquals(builder.toString(), garden.getLayout());
        System.out.println(garden.getLayout());
    }

    @Test
    public void testScenario2() {
        File gardenFile = new File(getClass().getResource("garden2.txt").getPath());
        File flowersFile = new File(getClass().getResource("flowers2.txt").getPath());

        StringBuilder builder = new StringBuilder();
        builder.append("XXXXXXXXXX\n");
        builder.append("XiaiaiaaaX\n");
        builder.append("XXXXXX   X\n");
        builder.append("XXXXXXXXXX\n");

        Garden garden = new Garden(gardenFile);
        garden.plantFlowers(flowersFile);
        assertEquals(builder.toString(), garden.getLayout());
        System.out.println(garden.getLayout());
    }

    @Test
    public void testScenario3() {
        File gardenFile = new File(getClass().getResource("garden3.txt").getPath());
        File flowersFile = new File(getClass().getResource("flowers3.txt").getPath());

        StringBuilder builder = new StringBuilder();
        builder.append("XXXXXXXXXXXXXXXXXXXXXXXXXX\n");
        builder.append("XdicidicidicidiXXXXXXXXXXX\n");
        builder.append("XXXXXXciciciciciXXXXXXXXXX\n");
        builder.append("XXXXXXcdcccdcccdcXXXXXXXXX\n");
        builder.append("XXXXXXcccccccccwwwdcccdccX\n");
        builder.append("XXXXXXccccccccwwccwcwwwwcX\n");
        builder.append("XXXXXXcwcw  XXXXX        X\n");
        builder.append("XXXXXXXXXXXXXXXXXXXXXXXXXX\n");


        Garden garden = new Garden(gardenFile);
        garden.plantFlowers(flowersFile);
        assertEquals(builder.toString(), garden.getLayout());
        System.out.println(garden.getLayout());
    }

    @Test
    public void testScenario4() {
        File gardenFile = new File(getClass().getResource("garden4.txt").getPath());
        File flowersFile = new File(getClass().getResource("flowers4.txt").getPath());

        StringBuilder builder = new StringBuilder();
        builder.append("XXXXXXXXXX\n");
        builder.append("XdbcbcdXXX\n");
        builder.append("XaXaa XXXX\n");
        builder.append("X     XXXX\n");
        builder.append("X     XX X\n");
        builder.append("XXXXXXXXXX\n");

        Garden garden = new Garden(gardenFile);
        garden.plantFlowers(flowersFile);
        assertEquals(builder.toString(), garden.getLayout());
        System.out.println(garden.getLayout());
    }
}
