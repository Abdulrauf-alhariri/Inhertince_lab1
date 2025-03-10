import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    static VehichleController vc;
    static VehichleView view;

    public static void main(String[] args) {
        // Skapa VehicleController och VehicleView i Model.
        ModelFacade model = new ModelFacade();
        vc = new VehichleController(model);

        // Skapa en factory för att skapa bilarna. En metod för varje model.
        model.vehicles.add(new Volvo240());
        
        model.vehicles.add(new Saab95());
        MotorVehicle saab = model.vehicles.get(model.vehicles.size() - 1);
        saab.setCoordinates(0.0, 200.0);

        model.vehicles.add(new Scania());
        MotorVehicle scania = model.vehicles.get(model.vehicles.size() - 1);
        scania.setCoordinates(0.0, 100.0);
        
        model.workshops.add(new Workshop<MotorVehicle>(2,"VolvoWorkshop", Volvo240.class));

        // Start a new view and send a reference of self
        view = new VehichleView("CarSim 1.0", vc, model);
        model.addObserver(view);

        vc.startTimer();
    }
}
