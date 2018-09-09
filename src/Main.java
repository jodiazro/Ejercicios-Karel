/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estudiante
 */
public class Main {
    public static void main(String[] args) {
        
        Parqueadero karel = new Parqueadero(5,3);
        karel.ingresarVehiculo("CBC101");
        karel.ingresarVehiculo("ASC121");
        karel.ingresarVehiculo("KLC121");
        karel.ingresarVehiculo("ABY001");
        karel.ingresarVehiculo("ABC121");
        karel.ingresarVehiculo("KLC121");
        karel.ingresarVehiculo("ABM091");
        karel.ingresarVehiculo("ABC121");
        karel.ingresarVehiculo("KLC131");
        karel.sacarVehiculo("CBC101");
        karel.sacarVehiculo("ABY001");
        
        
    }
}
