import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Garden {
    private List<Coordinate> availableSpaces;
    private char[][] layout;

    public Garden(File gardenFile) {
        availableSpaces = new LinkedList<>();
        constructGardenLayout(gardenFile);
    }

    public void plantFlowers(File flowersFile) {
        PriorityQueue<Flower> flowerQueue = new PriorityQueue<>(Collections.reverseOrder());
        processFlowersFile(flowersFile, flowerQueue);

        while(!flowerQueue.isEmpty()) {
            plantFlower(flowerQueue.poll());
        }
    }

    private void constructGardenLayout(File gardenFile) {
        try {
            int gardenRows = (int) Files.lines(Path.of(gardenFile.getPath())).count();
            layout = new char[gardenRows][];
            Scanner scanner = new Scanner(gardenFile);

            for (int currentRow=0; currentRow<gardenRows && scanner.hasNextLine(); currentRow++) {
                String line = scanner.nextLine();
                layout[currentRow] = new char[line.length()];
                for(int currentColumn=0; currentColumn<line.length(); currentColumn++) {
                    char currentPosition = line.charAt(currentColumn);
                    layout[currentRow][currentColumn] = currentPosition;
                    if(currentPosition == ' ') {
                        availableSpaces.add(new Coordinate(currentColumn, currentRow));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processFlowersFile(File flowersFile, PriorityQueue<Flower> flowerQueue) {
        try {
            Scanner scanner = new Scanner(flowersFile);
            while (scanner.hasNextLine()) {
                String[]line = scanner.nextLine().split(",");
                char flowerType = line[0].charAt(0);
                int flowerCount = Integer.parseInt(line[1]);
                int flowerRestriction = Integer.parseInt(line[2]);
                for(int i=0; i<flowerCount; i++) {
                    flowerQueue.offer(new Flower(flowerType, flowerRestriction));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This implementation would log an error if we are unable to find space for our flower
    private void plantFlower(Flower flower) {
        Iterator<Coordinate> iterator = availableSpaces.listIterator();
        while(iterator.hasNext()) {
            Coordinate coordinate = iterator.next();
            if(validSpace(coordinate, flower)) {
                //plant the flower
                layout[coordinate.Y()][coordinate.X()] = flower.getType();
                iterator.remove();
                return;
            }
        }
        System.err.println("Couldn't find space for flower of type: "+flower.getType());
    }

    private boolean validSpace(Coordinate coordinate, Flower flower) {
        if(layout[coordinate.Y()][coordinate.X()]!=' ')
            return false;

        int restriction = flower.getSpaceRestriction();
        char flowerType = flower.getType();

        //check South East
        int x = coordinate.X()+1;
        int y = coordinate.Y();
        int xLimit = Math.min(x+restriction-1, layout[0].length);
        int yLimit = Math.min(y+restriction-1, layout.length);
        int offset = 0;
        while (y<yLimit) {
            while (x<xLimit-offset) {
                if (layout[y][x] == flowerType) {
                    return false;
                }
                x++;
            }
            x = coordinate.X()+1;
            offset++;
            y++;
        }

        //check South West
        x = coordinate.X();
        y = coordinate.Y()+1;
        xLimit = Math.max(x-restriction+1, 0);
        yLimit = Math.min(y+restriction-1, layout.length);
        offset = 0;
        while (y<yLimit) {
            while (x>xLimit+offset) {
                if (layout[y][x] == flowerType) {
                    return false;
                }
                x--;
            }
            x = coordinate.X();
            offset++;
            y++;
        }

        //check North West
        x = coordinate.X()-1;
        y = coordinate.Y();
        xLimit = Math.max(x-restriction+1, 0);
        yLimit = Math.max(y-restriction+1, 0);
        offset = 0;
        while (y>yLimit) {
            while (x>xLimit+offset) {
                if (layout[y][x] == flowerType) {
                    return false;
                }
                x--;
            }
            x = coordinate.X()-1;
            offset++;
            y--;
        }

        //check North East
        x = coordinate.X();
        y = coordinate.Y()-1;
        xLimit = Math.min(x+restriction-1, layout[0].length);
        yLimit = Math.max(y-restriction+1, 0);
        offset = 0;
        while (y>yLimit) {
            while (x<xLimit-offset) {
                if (layout[y][x] == flowerType) {
                    return false;
                }
                x++;
            }
            x = coordinate.X();
            offset++;
            y--;
        }

        return true;
    }

    public String getLayout() {
        StringBuilder builder = new StringBuilder();
        for (char[] chars : layout) {
            for (int j = 0; j < layout[0].length; j++) {
                builder.append(chars[j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
