package cinemar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Butacas {
	private Conecting conexion = new Conecting(); 
	private Scanner t = new Scanner(System.in);
	
	  public Butacas() {
    	  
      }
	  public int cantidadButacas() {
          int cantidad = 0;
          System.out.print("INGRESAR CANTIDAD DE BUTACAS A RESERVAR");
          cantidad = t.nextInt();
          return cantidad;
	  }
	  public int[] getListaButacas(int k) {
		  int[] listaButacas = new int[k];
		  for(int i=0; i<k;i++) {
        	  System.out.print("SELECCIONAR BUTACAS DISPONIBLES");
       	      listaButacas[i] = t.nextInt();  
       	     
          }
		  return listaButacas;
	  }
	  public boolean compButacas(int [] lista) {
		  int ban=0;
		  int aux;
		  for(int i=1; i<lista.length;i++) {
        	  aux=lista[0]; 
			  if (aux==lista[i]) {
				  ban=-1;
			  }
       	  }
		  if (ban == -1) {
    	    	 return false;
    	     }else {
    	    	 return true; 
    	     }
	  }
	
	  public int compButLib(int sala) throws SQLException {
		  String sql="select status from butacas where idButacas="+sala+";";
				ResultSet r=conexion.devolverConsulta(sql);
				while(r.next()) {
				if(r.getInt("status")==1) {
				   return 1;
				     }
			  }
			return 0;	
	  }
	  
      public void getButacas(int sala) throws SQLException {
    	  String but=""; 
    	  String sql="select idButacas, nombre, status from butacas where idSala="+sala+";";
			ResultSet r=conexion.devolverConsulta(sql);
			System.out.println("LISTADO DE BUTACAS DISPONIBLES");
			//System.out.println("id|\t Butaca|");
			while(r.next()) {
				if(r.getInt("status")==1) {
				     but ="Ocupada";
				}else{
					 but="Libre";
				}
				System.out.printf("%12s|%12s|%12s \n",r.getInt("idButacas"),r.getString("nombre"),but);
			}
           
      }
      public void getButacasSelec(int butaca) throws SQLException {
          String but="";
    	  String sql="select idButacas, nombre,status from butacas where idButacas="+butaca+";";
			ResultSet r=conexion.devolverConsulta(sql);
			
			while(r.next()) {
				if(r.getInt("status")==1) {
					 but ="Ocupada";
				}else{
					 but="Libre";
				}
				
				System.out.printf("%12s|%12s|%12s \n",r.getInt("idButacas"),r.getString("nombre"),but);
				
			}
           
      }
      
}