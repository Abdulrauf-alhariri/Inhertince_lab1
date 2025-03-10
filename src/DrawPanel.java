

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final int worldSizeX;
    private final int worldSizeY;

    // Just a single image, TODO Generalize
    BufferedImage volvoImage;
    // To keep track of a single car's position
    Point volvoPoint = new Point();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,0);

    // Drawing Saab
    BufferedImage saabImage;
    Point saabPoint = new Point();

    BufferedImage scaniaImage;
    Point scaniaPoint = new Point();
    ArrayList<Point> scaniaPoints = new ArrayList<>();
    ArrayList<Point> saabPoints = new ArrayList<>();
    ArrayList<Point> volvoPoints = new ArrayList<>();
    ArrayList<Point> workshopPoints = new ArrayList<>();

    void clear() {
        scaniaPoints.clear();
        volvoPoints.clear();
        saabPoints.clear();
    }


    // TODO: Make this general for all cars
    void moveit(int x, int y, String modelName){

        if (Objects.equals(modelName, "Volvo240")){
            volvoPoints.add(new Point(x, y));
        }
        if (Objects.equals(modelName, "Saab95")){
            saabPoints.add(new Point(x, y));
        }
        if (Objects.equals(modelName, "Scania")) {
            scaniaPoints.add(new Point(x, y));
        }
        if (Objects.equals(modelName, "VolvoWorkshop")) {
            workshopPoints.add(new Point(x, y));
        }
    }
    /*void workshopLocation(double x, double y, Workshop<MotorVehicle> W) {
        if(W instanceof Workshop<Volvo240>) {

        }
    }*/

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        // We save the dimiontions in the variable worldSize
        this.worldSizeX = x;
        this.worldSizeY = y;

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Point point : volvoPoints) {
            g.drawImage(volvoImage, point.x, point.y, null);
        }
        for (Point point : saabPoints) {
            g.drawImage(saabImage, point.x, point.y, null);
        }
        for (Point point : scaniaPoints) {
            g.drawImage(scaniaImage, point.x, point.y, null);
        }
        for (Point point : workshopPoints) {
            g.drawImage(volvoWorkshopImage, point.x, point.y, null);
        }
    }
}
