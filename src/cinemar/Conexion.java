package cinemar;
import java.sql.*;

public class Conexion {
    
	 static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	 static final String DB_URL="jdbc:mysql://localhost:3306/cinemar";
	 static final String USER="root";
	 static final String PASS="admin";
	 Connection conn=null;
	 Statement stmt =null;
	 
	 public Conexion(){
		 try
	        {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn=DriverManager.getConnection(DB_URL,USER,PASS);

	        }
		 
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
		 
	 }
	 
	 public int[] compLogin(String u,String p) throws SQLException {			
	        
		       try {
					this.stmt=this.conn.createStatement();
					String sql;
					
					sql="select * from usuario where email='"+u+"' and password='"+p+"';";
					ResultSet respuesta = stmt.executeQuery(sql);
					while (respuesta.next()) {
						
						int idUsuario=respuesta.getInt("id_usuario");
						int rol=respuesta.getInt("idRol");
						return  new int[] {idUsuario,rol};
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		      // return new int[]{1, 3, 6, 8, 10};
		 
		       return new int[] {-1,-1};
	 }
	 
	public int getRolUsuario(int u) {
		try {
			this.stmt=this.conn.createStatement();
			String sql;
			
			sql="select rol from usuario where id_usuario = " +u+ ";";
			ResultSet respuesta = stmt.executeQuery(sql);
			while (respuesta.next()) {
				
				int rol=respuesta.getInt("rol");
				
				return rol;
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
 
		return 0;
	}
	
	 public void mostrarTablas() {
	 		
		 try {
			this.stmt=this.conn.createStatement();
			String sql, sql2;
			sql="select * from usuario";
			sql2="select * from peliculas"; 
			int i=1;
			ResultSet respuesta = stmt.executeQuery(sql);
			System.out.print("USUARIOS: \n");
			  while (respuesta.next()) {
				String nombre = respuesta.getString("nombre");
				String apellido = respuesta.getString("apellido");
				String email = respuesta.getString("email");
				String dni = respuesta.getString("dni");
				
				System.out.print(i+") "+apellido+" ");
				System.out.print(" "+nombre+" ");
				System.out.print(" "+email+" ");
				System.out.print(" "+dni+"\n");
				i++;
				
			}
			  
			ResultSet respuesta2 = stmt.executeQuery(sql2);
			int j=1;
			System.out.print("PELICULAS: \n");
			   while (respuesta2.next()) {
				String nombre = respuesta2.getString("nombre");
				String duracion = respuesta2.getString("duracion");

				System.out.print(j+") "+nombre+" ");
				System.out.print(" "+duracion+" \n");
				j++;
				
			}
			
			respuesta.close();
			respuesta2.close();
			stmt.close();
			conn.close();
			
			
     		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
	    	} 
		 
	 }
     
}