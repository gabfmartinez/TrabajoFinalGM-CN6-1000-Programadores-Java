package cinemar;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sala {
	private Conecting conexion = new Conecting(); 
	//private String query;
	
	  public Sala() {
    	  
      }
	  
      public void getSala() throws SQLException {
           
    	  String sql="select * from sala;";
			ResultSet r=conexion.devolverConsulta(sql);
			System.out.println("\n");
			System.out.println("LISTADO DE SALAS \n");
			System.out.printf("|%-12s|%-12s|%-12s| \n", "ID","CAPACIDAD","NOMBRE","TIPO");

			while(r.next()) {

				String tipoSala=null;
				if(r.getString("idTiposala")=="1") {
					tipoSala="2D";
				}else{
					tipoSala="3D";
				}
				
				System.out.printf("|%-12s|%-12s|%-12s| \n",
						r.getInt("idSala"),
						r.getString("capacidad"),
						r.getString("nombre"),
						tipoSala
						);
			}
			
			System.out.println("______________");
           
      }
      
      public int getIdSala(int cartelera) throws SQLException {
          
    	  String sql="SELECT idSala FROM cartelera WHERE idCartelera = "+cartelera+";";
			ResultSet r=conexion.devolverConsulta(sql);
			
			while(r.next()) {
				return r.getInt("idSala");
			}
			return -1;
           
      }
      
      public String getNombreSala(int cartelera) throws SQLException {
          
    	  String sql="SELECT sala.nombre as sala FROM sala inner join cartelera using(idSala )WHERE idCartelera = "+cartelera+";";
			ResultSet r=conexion.devolverConsulta(sql);
			
			while(r.next()) {
				return r.getString("sala");
			}
			return null;
           
      }
      
}