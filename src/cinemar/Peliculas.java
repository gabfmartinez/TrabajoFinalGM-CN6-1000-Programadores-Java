package cinemar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Peliculas {
      
	private Conecting conexion = new Conecting(); 
	private String query;
	
	  public Peliculas() {
    	  
      }
      public void getPeliculas() throws SQLException {
           
    	  String sql="select peliculas.*, genero.descripcion as genero from peliculas inner join genero ON peliculas.idGenero=genero.idGenero;";
			ResultSet r=conexion.devolverConsulta(sql);
			System.out.println("LISTADO DE PELICULAS DISPONIBLES");
			System.out.println(" \n");
			System.out.printf("%-3s|%-30s|%-12s|%-12s|%-12s|%-13s|\n","ID","NOMBRE","DURACION","CALIFICACION","CATEGORIA","GENERO");
			System.out.println(" \n");
			while(r.next()) {

				String atp=null;
				String plus=null;
				if(r.getString("atp")=="0") {
					atp="ATP";
					
				}else {
					atp="P/13";
				}
				
				if(r.getString("plus")==null) {
					plus="no estreno";
				}else{
					plus="estreno";
				}
				
				System.out.printf("%-3s|%-30s|%-12s|%-12s|%-12s|%-13s| \n",
						r.getInt("idPeliculas"),
						r.getString("nombre"),
						r.getString("duracion"),
						atp,
						plus,
						r.getString("genero"));
			}
			
			System.out.println(" \n");
           
      }
      
      public String getNombrePeliculas(int idPeli) throws SQLException {
          
    	  String sql="select nombre from peliculas where idPeliculas= "+ idPeli + ";";
			ResultSet r=conexion.devolverConsulta(sql);
			
			while(r.next()) {
    			 return r.getString("nombre");
			}
			
            return null;
      }
      
}