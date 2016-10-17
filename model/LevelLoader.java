package Socoban.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by younotimba on 15.10.2016.
 */
public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (level > 60) level = level % 60;
        Set<Wall> walls = new HashSet<Wall>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        int x0 = Model.FIELD_SELL_SIZE / 2;
        int y0 = Model.FIELD_SELL_SIZE / 2;
        int mazeHeight = 0;
        int mazeWidth = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toAbsolutePath().toString()));) {
            String s = "";
            while (reader.ready()) {
                s = reader.readLine();
                if (s.equals("Maze: " + level)) {
                    reader.readLine();
                    mazeWidth = Integer.parseInt(reader.readLine().replaceAll("Size X: ", ""));
                    mazeHeight = Integer.parseInt(reader.readLine().replaceAll("Size Y: ", ""));

                    for (int i = 0; i < 3; i++) {
                        reader.readLine();
                    }

                    for (int i = 0; i < mazeHeight; i++) {
                        s = reader.readLine();
                        for (int j = 0; j < mazeWidth; j++) {
                            switch (s.charAt(j)) {
                                case ' ':
                                    x0 += Model.FIELD_SELL_SIZE;
                                    break;
                                case 'X':
                                    walls.add(new Wall(x0, y0));
                                    x0 += Model.FIELD_SELL_SIZE;
                                    break;
                                case '*':
                                    boxes.add(new Box(x0, y0));
                                    x0 += Model.FIELD_SELL_SIZE;
                                    break;
                                case '.':
                                    homes.add(new Home(x0, y0));
                                    x0 += Model.FIELD_SELL_SIZE;
                                    break;
                                case '&':
                                    boxes.add(new Box(x0, y0));
                                    homes.add(new Home(x0, y0));
                                    x0 += Model.FIELD_SELL_SIZE;
                                    break;
                                case '@':
                                    player = new Player(x0, y0);
                                    x0 += Model.FIELD_SELL_SIZE;
                                    break;
                            }
                        }
                        x0 = Model.FIELD_SELL_SIZE / 2;
                        y0 += Model.FIELD_SELL_SIZE;
                    }
                    break;
                }
            }
        } catch (Exception e) {

        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
