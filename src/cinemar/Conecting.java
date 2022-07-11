package cinemar;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;

	public class Conecting {

		public Connection conn;
		public Statement stmt;
		
		public Conecting() {
			 final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";
			 final String DB_URL="jdbc:mysql://localhost:3306/cinemar";
			 final String USER="root";
			 final String PASS="admin";
			 conn=null;
			 stmt =null;
			 
				try {
					Class.forName(JDBC_DRIVER);
					conn= DriverManager.getConnection(DB_URL,USER,PASS);
				}catch(SQLException e){
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}

			public ResultSet devolverConsulta(String query) throws SQLException {
				 stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				 String sql;
				 sql=query;
				 ResultSet rs =stmt.executeQuery(sql);
			    return rs;
			}
			
			public void EjecutarConsulta(String sql) throws SQLException {
				 stmt.executeUpdate(sql);
				 
			}
			
			public void AgregarElementos(String tabla, ArrayList<String> elementos) throws SQLException {
				
				System.out.println(" \n");
				System.out.println(" procesando");
				
				String sql;
				sql="insert into "+ tabla + "\r\n"
					+"values \r\n";
				for( int i=0;i<elementos.size()-1;i++) {
					
					sql.concat("("+"'"+elementos.get(i)+"'"+", \r\n");
				}
				
				sql.concat("'"+elementos.get(elementos.size())+"'"+")\r\n");
				sql.concat(";");
				stmt.executeUpdate(sql);
				System.out.print("valores agregados");
				
			}
			
			public void ModificarElementos(String tabla, String campo, String NueValor, String tiId, int IdTabla) throws SQLException {
				
				String sql;
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				sql="UPDATE "+tabla+" SET "+campo+"='"+NueValor+"' WHERE  "+tiId+"="+IdTabla+";";
				stmt.executeUpdate(sql);
				System.out.print("Valor modificado");
				
			}
			
			public void EliminarReserva(int res, int u) throws SQLException {
				String sql;
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				sql="delete from reserva where idReserva= "+res+" and idUsuario ="+u+";";
				stmt.executeUpdate(sql);
				System.out.print("Modifique los datos");
			}
			public void EliminarCartelera(int car) throws SQLException {
				String sql;
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				sql="delete from cartelera where idCartelera= "+car+";";
				stmt.executeUpdate(sql);
				System.out.print("Registro eliminado");
			}
			
			public void AgregarUsuario(ArrayList<String> elementos) throws SQLException {
				
				System.out.println(" \n");
				System.out.println(" procesando");
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				String sql;
				sql="insert into usuario (apellido,nombre,email,dni,password) "+
				" values (?,?,?,?,?)";
				 PreparedStatement preparedStmt = conn.prepareStatement(sql);
			      preparedStmt.setString (1,elementos.get(0) );
			      preparedStmt.setString (2,elementos.get(1) );
			      preparedStmt.setString (3,elementos.get(2) );
			      preparedStmt.setString (4,elementos.get(3) );
			      preparedStmt.setString (5,elementos.get(4) );
			      preparedStmt.execute(); 
			      System.out.print("Su registro es exitoso");
			}
			
			public void AgregarReserva(ArrayList<Integer>elementos) throws SQLException {
				
				System.out.println(" \n");
				System.out.println(" procesando");
				
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				String sql;
				sql="insert into reserva (idUsuario,idEstado,idTipoPago,idCartelera) "+
				" values (?,?,?,?)";
				
				  PreparedStatement preparedStmt = conn.prepareStatement(sql);
			    
			      preparedStmt.setInt (1,elementos.get(0) );
			      preparedStmt.setInt (2,elementos.get(1) );
			      preparedStmt.setInt(3,elementos.get(2) );
			      preparedStmt.setInt (4,elementos.get(3) );
			      preparedStmt.execute(); 
			      System.out.println("Registro de reserva realizado");
			      
			}
			
			public void AgregarCartelera(ArrayList<Integer>elementos, String hora) throws SQLException {
				
				System.out.println(" \n");
				System.out.println(" procesando");
				
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				String sql;
				sql = "insert into cartelera (hora, idPelicula, idSala, precioTotal,estado)"
						+ "value(?,?,?,?,1);";
				
				  PreparedStatement preparedStmt = conn.prepareStatement(sql);
			    
			      preparedStmt.setString (1,hora );
			      preparedStmt.setInt (2,elementos.get(0) );
			      preparedStmt.setInt(3, elementos.get(1) );
			      preparedStmt.setInt (4, elementos.get(2) );
			      preparedStmt.execute(); 
				 System.out.print("Cartelera ingresada con exito");
				 
			}
            public void AgregarPelicula(ArrayList<Integer>ei, ArrayList<String>es ) throws SQLException {
				
				System.out.println(" \n");
				System.out.println(" procesando");
				
				stmt= conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				String sql;
				sql = "insert into peliculas (nombre,duracion,atp,idGenero,plus)"
						+ "value(?,?,?,?,?);";
				
				  PreparedStatement preparedStmt = conn.prepareStatement(sql);
			    
			      preparedStmt.setString (1,es.get(0) );
			      preparedStmt.setString (2,es.get(1) );
			      preparedStmt.setInt    (3,ei.get(0));
			      preparedStmt.setInt    (4,ei.get(1));
			      preparedStmt.setString (5,es.get(2) );
			      
			      preparedStmt.execute(); 
				
			    System.out.print("Pelicula ingresada con exito");
				 
			}

}