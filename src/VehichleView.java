import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehichleView extends JFrame implements ModelUpdated{

    private int X;
    private int Y;
    ModelFacade model;
    VehichleController vehichleController;
    DrawPanel drawPanel;
    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");
    JPanel brakePanel = new JPanel();
    JSpinner brakeSpinner = new JSpinner();
    int brakeAmount = 0;
    JLabel brakeLabel = new JLabel("Amount of brake");
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    JButton removeVehicleButton = new JButton("Remove Vehicle");
    JButton addVehicleButton = new JButton("Add Vehicle");
    JButton unloadWorkshopButton = new JButton("Unload Workshop");


    // Constructor
    public VehichleView(String framename, VehichleController vc, ModelFacade model){
        this.vehichleController = vc;
        this.model = model;
        this.X = model.getWorldSizeX();
        this.Y = model.getWorldSizeY();
        this.drawPanel = new DrawPanel(this.X, this.Y-240);
        initComponents(framename);

    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(drawPanel);

        SpinnerModel gasSpinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        SpinnerModel brakeSpinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(gasSpinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        // Creating the brake spinner
        brakeSpinner = new JSpinner(brakeSpinnerModel);
        brakeSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                brakeAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        brakePanel.add(brakeSpinner, BorderLayout.PAGE_END);
        brakePanel.add(brakeLabel, BorderLayout.PAGE_START);
        this.add(brakePanel);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));
        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addVehicleButton, 6);
        controlPanel.add(removeVehicleButton, 7);
        controlPanel.add(unloadWorkshopButton, 8);
        controlPanel.setPreferredSize(new Dimension((2*X/3)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehichleController.gas(gasAmount);
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehichleController.brake(gasAmount);
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehichleController.turboOn();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehichleController.stopVehicles();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehichleController.startVehicles();
            }
        });
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { vehichleController.addVehicle(); }
        });
        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { vehichleController.removeVehicle(); }
        });
        unloadWorkshopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { vehichleController.unloadWorkshop(); }
        });
        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void modelUpdateNotification() {
        drawPanel.clear();
        for(MotorVehicle vehicle : model.vehicles) {
            drawPanel.moveit((int) vehicle.getCoordinates().x, (int) vehicle.getCoordinates().y, vehicle.getModelName());
        }
        for (Workshop workshop : model.workshops) {
            drawPanel.moveit((int) workshop.getCoordinates().x, (int) workshop.getCoordinates().y, workshop.getWorkshopName());
        }
        drawPanel.repaint();
    }
}