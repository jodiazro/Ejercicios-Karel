/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Johan Andrés Díaz Roa
 *         Ahmet Felipe Marquez Gelvis
 *         Santiago Hernández Lamprea
 *         Cristian Camilo Vargas Morales
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
    private String[][] usuarios;
    
// Parqueadero(Numero de puestos de cada seccion, Numero de seccciones)
    public Parqueadero(int p, int s) {
       this.secciones=s;
       this.puestos=p;
       this.ocupados= new int[s];
       this.usuarios= new String[s][p];
       
       for(int i=0;i<s;i++) this.ocupados[i]=0;
       
       this.ciudad = new City("Parqueadero.txt");
        for (int i = 1; i <= s+1; i++) {
            Wall pared = new Wall(ciudad, 1, i, Direction.NORTH); 
            pared = new Wall(ciudad, p+1, i, Direction.SOUTH);
            for(int j=1; j<=p;j++){
                pared = new Wall(ciudad, j, i, Direction.WEST);
            }            
        }
        for(int i=1;i<=p-1;i++){
            Wall pared = new Wall(ciudad,i,s+1,Direction.EAST);
        }
        Wall pared = new Wall(ciudad,p+1,s+1,Direction.EAST);
  
        this.robot1 = new Robot(ciudad, p, s+2, Direction.WEST);       
        //this.robot1.setLabel("Robot");
        this.robot1.setColor(Color.ORANGE);
    }
    
    public void ingresarVehiculo(String placa){
        
        Thing carro= new Thing(ciudad,this.puestos,this.secciones+2); 
        carro.getIcon().setColor(Color.LIGHT_GRAY);
        carro.getIcon().setLabel(placa);
        
        
        int seccion=this.seccionMasVacia();
        
        this.robot1.pickThing();
        
        this.robot1.move();
        this.robot1.turnLeft();
        this.robot1.move();
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        
        for(int i=1;i<=seccion+1;i++){
            this.robot1.move();
        }
        
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        
        for(int i=this.ocupados[seccion];i<this.puestos;i++){
            this.robot1.move();
        }
        
        this.robot1.putThing();
        
        for(int i=0;i<2;i++)this.robot1.turnLeft();
        
        for(int i=this.ocupados[seccion];i<this.puestos;i++){
            this.robot1.move();
        }
        
        this.robot1.turnLeft();
        
        for(int i=1;i<=seccion+1;i++)this.robot1.move();        
        this.robot1.turnLeft();
        this.robot1.move();
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        this.robot1.move();
        for(int i=0;i<2;i++)this.robot1.turnLeft();
        
        this.usuarios[seccion][this.ocupados[seccion]]=placa;
        System.out.println(this.usuarios[seccion][this.ocupados[seccion]]);
        
        this.ocupados[seccion]++;
        
        
        
    }
    
    public void sacarVehiculo(String placa){
        int s = 0,p = 0;
        
        for(int i=0;i<this.secciones;i++){
            for(int j=0;j<this.ocupados[i];j++){
                if(this.usuarios[i][j].equals(placa)){
                    s=i;
                    p=j;
                }
            }
        }
        
        this.robot1.move();
        this.robot1.turnLeft();
        this.robot1.move();
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        
        for(int i=1;i<=s+1;i++)this.robot1.move();
        
        for(int i=0;i<3;i++)this.robot1.turnLeft();
        
        for(int i=0;i<this.ocupados[s]-p;i++){
            for(int j=0;j<=this.puestos-this.ocupados[s]+i;j++)this.robot1.move();
            this.robot1.pickThing();
            for(int j=0;j<2;j++)this.robot1.turnLeft();            
            for(int j=0;j<=this.puestos-this.ocupados[s]+i;j++)this.robot1.move();
            
            if(i+1==this.ocupados[s]-p){
                for(int j=0;j<3;j++)this.robot1.turnLeft();
                for(int j=1;j<=this.secciones-s;j++)this.robot1.move();
                this.robot1.putThing();
            }else{
                this.robot1.turnLeft();
                for(int j=1;j<=s+1;j++)this.robot1.move();
                this.robot1.turnLeft();


                for(int j=1;j<=this.puestos-i;j++)this.robot1.move();
                this.robot1.putThing();

                for(int j=0;j<2;j++)this.robot1.turnLeft();
                for(int j=1;j<=this.puestos-i;j++)this.robot1.move();

                for(int j=0;j<3;j++)this.robot1.turnLeft();

                for(int j=1;j<=s+1;j++)this.robot1.move();
                for(int j=0;j<3;j++)this.robot1.turnLeft();
            }                        
        }
        
        for(int j=0;j<2;j++)this.robot1.turnLeft();
        for(int j=0;j<=this.secciones;j++)this.robot1.move();
        this.robot1.turnLeft();
        
        for(int i=0;i<this.ocupados[s]-p-1;i++){
            for(int j=0;j<=this.puestos-this.ocupados[s]+p+1+i;j++)this.robot1.move();
            this.robot1.pickThing();
            for(int j=0;j<2;j++)this.robot1.turnLeft();
            for(int j=0;j<=this.puestos-this.ocupados[s]+p+i+1;j++)this.robot1.move();
            for(int j=0;j<3;j++)this.robot1.turnLeft();
            for(int j=1;j<=s+1;j++)this.robot1.move();
            for(int j=0;j<3;j++)this.robot1.turnLeft();
            for(int j=0;j<this.puestos-p-i;j++)this.robot1.move();
            this.robot1.putThing();
            for(int j=0;j<2;j++)this.robot1.turnLeft();
            for(int j=0;j<this.puestos-p-i;j++)this.robot1.move();
            this.robot1.turnLeft();
            for(int j=1;j<=s+1;j++)this.robot1.move();
            this.robot1.turnLeft();
            
        }
        
        this.robot1.move();
        for(int j=0;j<3;j++)this.robot1.turnLeft();
        this.robot1.move();
        for(int j=0;j<2;j++)this.robot1.turnLeft();
        
        for(int i=p;i<this.ocupados[s]-1;i++){
            this.usuarios[s][i]=this.usuarios[s][i+1];
        }
        
        this.ocupados[s]--;
    }
    
    public int seccionMasVacia(){
        int sec=0;
        for(int i=1;i<this.secciones;i++){
            if(this.ocupados[sec]>this.ocupados[i]){
                sec=i;
            }
        }
        System.out.println(sec);
        return sec;
        
    }

    public String mostrarSeccion(){
        
    }
    
    public double gananciasGeneradas(){
        
    }
}
