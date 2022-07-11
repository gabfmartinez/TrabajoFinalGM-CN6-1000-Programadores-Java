package cinemar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cartelera {
	private Conecting conexion = new Conecting(); 
	private Peliculas pelicula = new Peliculas();
	private Sala sa = new Sala();
	Scanner t = new Scanner(System.in);
	private int hora;
	private int peli;
	private int sala;
	private double precio;
	
	
	public Cartelera(int h, int p, int s, double prc) {
		this.hora=h;
		this.peli=p;
		this.sala=s;
		this.precio=prc;
		
	}
	
	public void formCartelera() throws SQLException, ParseException {
		System.out.println("CREACION DE CARTELERA");
		mostrarCarteleras();
		pausa(5000);
		System.out.println("\n");
		System.out.println("CREAR DE CARTELERA");
		System.out.println("_ _ _ _ _ _ _ _ _");
		
		pausa(2000);
		pelicula.getPeliculas();
		
		System.out.println("SELECCIONE PELICULA: ");
		pausa(3000);
		System.out.println("Digite 0 para CANCELAR: ");
		System.out.print("Digite la opcion: ");
		int peli= t.nextInt();
		

		if(peli!=0) {
		     
		     System.out.println("_ _ _ _ _ _ _");
		     sa.getSala();

		     System.out.print("SELECCIONE SALA:");
		     int sala= t.nextInt();
		     System.out.println("--------------");
		     
		     System.out.print("INGRESE HORARIO: hh:mm:ss");
		     String hora= t.next();
		     System.out.println("-----------------------");
		     
		     
		     System.out.print("INGRESE PRECIO: --> $");
		     int precio= t.nextInt();
		   
		     System.out.println("-------------------");
		     
		     System.out.println("DATOS INGRESADOS");
		     
		     String p=pelicula.getNombrePeliculas(peli);
		     String s=sa.getNombreSala(sala);
		     System.out.println("Pelicula"+p);
		     System.out.println("Sala"+s);
		     System.out.println("Horario"+hora);
		     System.out.println("Precio s/descuento"+precio);
		     
		     System.out.println("CONFIRMAR digite (1)");
		     System.out.println("CANCELAR  digite (2)");
		     int insert = t.nextInt();
		     
		     if (insert == 1) {
		         ArrayList<Integer> Lista = new ArrayList<Integer>();
		         Lista.add(peli);
		         Lista.add(sala);
		         Lista.add(precio);
		         Conecting conn=new Conecting();
  	             conn.AgregarCartelera(Lista, hora);
		     }else if(insert == 2){
		    	 MenuAdmin ma= new MenuAdmin();
		    	 ma.mostrarMenu();
		     }
		     
		}
	
	}
	
	public void mostrarCarteleras() throws SQLException{
		
   	  String sql="select cartelera.*, peliculas.nombre as peli, sala.idSala, sala.nombre AS sala from cartelera inner JOIN peliculas, sala WHERE\r\n"
   	  		+ "   cartelera.idSala=sala.idSala and\r\n"
   	  		+ "	  cartelera.idPelicula=peliculas.idPeliculas and"
   	  		+ "   cartelera.estado=1 order by sala.idSala, cartelera.hora ;";
			ResultSet r=conexion.devolverConsulta(sql);
			System.out.println("FUNCIONES DE : ");
			System.out.println("LISTADO DE FUNCIONES: " +peli);
			System.out.printf("%-12s|%-20s|%-12s|%-50s|%-12s| \n","Id","Sala","Hora","Pelicula","Precio");
			System.out.println("-----------------------------------------------------------------------");
			
			while(r.next()) {
				System.out.printf("%-12s|%-20s|%-12s|%-50s|%-12s| \n",
						          r.getInt("idCartelera"),
						          r.getString("sala"),
						          r.getString("Hora"),
						          r.getString("peli"),
						          r.getInt("precioTotal"));
			}
			
	}
	
	public void mostrarUnaCartelera(int c) throws SQLException{
		
	   	  String sql="select cartelera.*, peliculas.nombre as peli, sala.idSala, sala.nombre AS sala from cartelera inner JOIN peliculas, sala WHERE\r\n"
	   	  		+ "   cartelera.idSala=sala.idSala and\r\n"
	   	  		+ "	  cartelera.idPelicula=peliculas.idPeliculas and"
	   	  		+ "   cartelera.idCartelera = "+c+" ;";
				ResultSet r=conexion.devolverConsulta(sql);
				System.out.println("\n MODIFICANDO FUNCION Nro: "+c);
				
				System.out.printf("%-12s|%-20s|%-12s|%-50s| \n","Id","Sala","Hora","Pelicula");
				System.out.println("--------------------------------------------------------");
				while(r.next()) {
					System.out.printf("%-12s|%-20s|%-12s|%-50s| \n",
							          r.getInt("idCartelera"),
							          r.getString("sala"),
							          r.getString("Hora"),
							          r.getString("peli"));
				}
				
				System.out.print("\n ");
				
		}
	
	public void getCartelera(int pel) throws SQLException {
           
    	  String peli = this.pelicula.getNombrePeliculas(pel);
    	  
    	  String sql="select cartelera.*, peliculas.nombre as peli, sala.idSala, sala.nombre AS sala from cartelera inner JOIN peliculas, sala WHERE\r\n"
    	  		+ "   cartelera.idSala=sala.idSala and\r\n"
    	  		+ "	  cartelera.idPelicula=peliculas.idPeliculas and\r\n"
    	  		+ "	  cartelera.idPelicula="+pel+";";
			ResultSet r=conexion.devolverConsulta(sql);
			System.out.println("FUNCIONES DE : ");
			System.out.println("LISTADO DE FUNCIONES: " +peli);
			System.out.printf("%-12s|%-20s|%-12s|%-12s| \n","Id","Sala","Hora","Precio");
			System.out.println("------------------------------------------------------");
			while(r.next()) {
				System.out.printf("%-12s|%-20s|%-12s|%-12s| \n",
						          r.getInt("idCartelera"),
						          r.getString("sala"),
						          r.getString("Hora"),
						          r.getDouble("precioTotal"));
			}
           
      }
	
      public String getHorario(int idCartelera) throws SQLException{
    	  String sql="select hora from cartelera where idCartelera= "+ idCartelera + ";";
			ResultSet r=conexion.devolverConsulta(sql);
			
			while(r.next()) {
  			 return r.getString("hora");
  			 
			}
    	  
    	  return null;
      }
      
      public double getPrecio(int idCartelera) throws SQLException{
    	  String sql="select precioTotal from cartelera where idCartelera= "+ idCartelera + ";";
			ResultSet r=conexion.devolverConsulta(sql);
			
			while(r.next()) {
  			 return r.getDouble("precioTotal");
  			 
			}
    	  
    	  return 0;
      }
      
      @SuppressWarnings("unused")
	private void pausa(int seg) {
          try {
      		   Thread.sleep(seg);
             	} catch (InterruptedException e) {
      	    	e.printStackTrace();
  	 	}
          
    }

}