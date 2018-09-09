/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estudiante
 */
import becker.robots.*;
import java.awt.Color;
public class Parqueadero{
    private City ciudad;
    private Robot robot1;
    private Robot robot2;
    private int[] ocupados;
    private int secciones;
    private int puestos;
    

    public Parqueadero(int p, int pt, int s) {
       this.secciones=s;
       this.puestos=p;
       this.ocupados= new int[s];
       
       for(int i=0;i<s;i++) this.ocupados[i]=0;
       
       this.ciudad = new City("Parqueadero.txt");
        for (int i = 1; i <= s+1; i++) {
            Wall pared = new Wall(ciudad, 1, i, Direction.NORTH); 
            pared = new Wall(ciudad, p+1, i, Direction.SOUTH);
            for(int j=1; j<=p;j++){
                pared = new Wall(ciudad, j, i, Direction.WEST);
            }            
        }
        for(int i=1;i<=pt;i++){
            Wall pared = new Wall(ciudad,i,s+1,Direction.EAST);
        }
        Wall pared = new Wall(ciudad,p+1,s+1,Direction.EAST);
        
        
        //Wall pared= new Wall(ciudad,);
    /*
        Thing thing = new Thing(ciudad, 2, 2);
        thing.getIcon().setColor(Color.LIGHT_GRAY);
        thing.getIcon().setLabel("Placa");
        */
        this.robot1 = new Robot(ciudad, p, s+2, Direction.WEST);       
        this.robot1.setLabel("Robot");
        this.robot1.setColor(Color.ORANGE);
    }
    
    public void ingresarVehiculo(String placa){
        
        Thing carro= new Thing(ciudad,this.puestos,this.secciones+2); 
        carro.getIcon().setColor(Color.LIGHT_GRAY);
        carro.getIcon().setLabel(placa);
        
        this.robot1.pickThing();
        
        this.robot1.move();
        this.robot1.turnLeft();
        this.robot1.move();
        
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        
        for(int i=1;i<=this.seccionMasVacia();i++){
            this.robot1.move();
        }
        
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        
        for(int i=this.ocupados[this.seccionMasVacia()];i<this.puestos;i++){
            this.robot1.move();
        }
        
        this.robot1.putThing();
        
        
    }
    
    public int seccionMasVacia(){
        int sec=0;
        for(int i=1;i<this.secciones;i++){
            if(this.ocupados[sec]>this.ocupados[i]){
                sec=i;
            }
        }
        return sec+1;
    }
            
    
    
    
}
