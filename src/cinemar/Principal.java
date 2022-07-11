package cinemar;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class Principal {
	
	int opcion;
	Scanner t = new Scanner(System.in);
	
	public Principal() throws SQLException, ParseException {
		//System.out.println("BIENVENIDO A CINEMAR \n");
		System.out.println("Aguarde");
		pausa(1500);
		System.out.println("Entrando al sistema \n");
		pausa(1500);
		System.out.println("MENU PRINCIPAL");
		System.out.println("INICIAR SESION (Digite 1)");
		System.out.println("REGISTRARSE    (Digite 2)");
		System.out.println("SALIR          (Digite 0) \n");
		System.out.println(" \n");
		System.out.print("OPCION: ");
	  	this.opcion = t.nextInt();
	  	
	  	switch(this.opcion) {
	  	case 2:
	  		Registrar r=new Registrar();
	  		break;
	  	case 1:	
  			Login log = new Login();
  			log.mostrarFormulario();
  			log.autDatos();
  			
  			break;
	  	case 0:
	  		System.out.println("Saliendo del sistema");
	  		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	  		System.out.println("GRACIAS");
	  		
	  		break;
	  	default:
	  			System.out.print("ERROR DE INGRESO. VUELVA A INTENTAR");
		  		try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		  		
		  		Principal p = new Principal();	
		  		
		  		break;
	  	}
	  	
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