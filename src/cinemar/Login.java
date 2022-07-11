package cinemar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Login {

	private String usua;
	private String password;
	private Scanner t = new Scanner(System.in);
	
	
	public Login() {
		
	}
	
	public void mostrarFormulario() {
		System.out.println("|INICIAR SESION|");
    	System.out.print("INGRESE USUARIO: ");
		this.usua = t.next();
		System.out.print("INGRESE CLAVE: ");
		this.password = t.next();
		
	}
	
	public void autDatos() throws SQLException, ParseException {
		
		Conexion con=new Conexion();
		int verdadero[] = con.compLogin(this.usua,this.password);
		if(verdadero[0]!=-1) {
			
			int idUsuario=verdadero[0];
			int rol=verdadero[1];
			if (rol == 2) {
				System.out.print("Ingresando");
				pausa(1500);
				MenuUsuario ui= new MenuUsuario(idUsuario);
			    ui.mostrarMenu();
			}
			
			if (rol == 1) {
				System.out.print("Ingresando");
				pausa(1500);
				MenuAdmin ua= new MenuAdmin(idUsuario);
				ua.mostrarMenu();
	            
			}
			
			
		}else{
			System.out.println("RECHAZADO");
			pausa(1500);
			System.out.println("VOLVIENDO AL INICIO");
			pausa(1500);
			Principal vuelve=new Principal();
		}
		
	}
	
	private void pausa (int seg){
		try {
			Thread.sleep(seg);
		}catch (InterruptedException e){
			e.printStackTrace();
			
		}
		
	}
	
}