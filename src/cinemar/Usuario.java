package cinemar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Usuario {
        
	private String nombre;
	private String apellido;
	private String email;
	private String dni;
	private String pass;
	private int rol;
	private int frecuente;
	
	public Usuario(String a, String n, String e, String doc, String cont) {
		this.rol=2;
		this.frecuente=0;
		this.apellido=a;
		this.nombre=n;
		this.email=e;
		this.dni=doc;
		this.pass=cont;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public void mostrarUser() {
		
		System.out.println("DATOS INGRESADOS");
		System.out.print("--> APELLIDO: "+this.apellido + "\n");
		System.out.print("--> NOMBRE: "+this.nombre + "\n");
		System.out.print("--> EMAIL: "+this.email+ " / (Sera su nombre de usuario) \n");
		System.out.print("--> D. N. I. : "+this.dni+ "\n");
		//System.out.print(this.rol+ "\n");
		//System.out.print(this.frecuente+ "\n");
		System.out.println("ATENCION");
		//System.out.println("GUARDADO DE DATOS CON EXITO \n");
		
	}
	
	public void insertUser() throws SQLException {
		
		//String[] array= (this.apellido,this.nombre,this.email,this.dni,this.rol,this.frecuente);
		ArrayList <String> lista = new ArrayList();
		lista.add(this.apellido);
		lista.add(this.nombre);
		lista.add(this.email);
		lista.add(this.dni);
		lista.add(this.pass);
		
		Conecting conexion = new Conecting();
		conexion.AgregarUsuario(lista);
		
	}
	
	public void ifoUsuario(int u) throws SQLException {
		String sql="select apellido, nombre from usuario where id_usuario="+u+";";
		Conecting con = new Conecting();
	    ResultSet user = con.devolverConsulta(sql);
	    while(user.next()) {
	       	System.out.println("BIENVENIDO: "+user.getString("apellido")+" "+user.getString("nombre"));
	    	
	    }
	    
	}
	
	public void getUsuario() throws SQLException {
		String sql="select * from usuario ;";
		Conecting con = new Conecting();
	    ResultSet user = con.devolverConsulta(sql);
	    System.out.println("USUARIOS");
		System.out.printf("%-3s|%-20s|%-30s|%-12s|\n","ID","APELLIDO","NOMBRE","FRECUENTE");
	    while(user.next()) {
	       	String frec="";
	    	if(user.getInt("esFrecuente") == 1) {
	       		 frec = "Si";
	       	}else {
	       		 frec = "No";
	       	}
	    	System.out.printf("%-3s|%-20s|%-30s|%-12s|\n",
	    			          user.getInt("id_usuario"),
	    			          user.getString("apellido"),
	    			          user.getString("nombre"),
	    			          frec
	    			          );
                               	    	
	    }
	    
	}

}