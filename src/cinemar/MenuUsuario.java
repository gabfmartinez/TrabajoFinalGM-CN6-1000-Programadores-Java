package cinemar;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MenuUsuario {
	private int opcion;
	private int usuario;
	
	Scanner t = new Scanner(System.in);
	
	public MenuUsuario() {
		
	}
	
	public MenuUsuario(int u) {
		this.usuario=u;
		
	}
    
	public void mostrarMenu() throws SQLException, ParseException {
		System.out.println("Espere...\n");
		Usuario user= new Usuario();
		user.ifoUsuario(this.usuario);
		pausa(2000);
		
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        String formattedDate = dtf.format(dateObj);
        System.out.println(formattedDate);

        //String dia = getDiaSemana("2022/07/09");
		//System.out.println(dia +"Espere...\n");
		
		System.out.println("MENU DE USUARIO");
		System.out.print("--------------------------------- \n");
		System.out.print("CREAR RESERVA :      (presione 1) \n");
		System.out.print("MIS RESERVAS :       (presione 2) \n");
		System.out.print("--------------------------------- \n");
		System.out.print("--------------------------------- \n");
     	System.out.print("->CERRAR SESION:     (precione 3) \n");
     	System.out.print("->SALIR DEL SISTEMA: (precione 0) \n");
     	System.out.print("--------------------------------- \n");
     	System.out.print("Opcion: ");
     	this.opcion = t.nextInt();
    	
     	switch(this.opcion) {
     	
     	case 0:{
     		System.out.println("Saliendo...");
     		pausa(1000);
     		System.out.println("GRACIAS POR SU VISITA");
    		break;
        	}
     	case 1:{
     		System.out.println("CREANDO RESERVA \n");
     		Reserva res = new Reserva(this.usuario);
     		res.creaReserva();     		     		
     		break;
        	}
     	case 2:{
     		System.out.println("MIS RESERVA \n");
     		Reserva res = new Reserva(this.usuario);
     		res.getReserva(this.usuario); 
     		System.out.println("---------------------------------");
     		System.out.println("Modificar reserva      (Digite 1)");
     		System.out.println("Volver                 (Digite 2)");
     		System.out.println("---------------------------------");
     		System.out.println("CERRAR SESION/CANCELAR (Digite 3)");
     		System.out.println("SALIR DEL SISTEMA      (Digite 4)");
     		System.out.print("Opcion: ");
         	this.opcion = t.nextInt();
         	submenu(this.opcion);
     		break;
        	}
     	case 3:{
     		Salir();
    		break;
        	}
      	default: {
     	    System.out.print("MAL INGRESO \n");
     	    pausa(2000);
     	    
   		   	}
      	
     	}
     	
    }
	private void submenu(int opcion) throws SQLException, ParseException {
		switch(opcion){
		case 1:
			modifReserva();
			break;
		case 2:
			System.out.println("Volviendo al menu");
			pausa(2000);
			mostrarMenu();			
            break;
		case 3:
			Principal pi= new Principal();
			break;
		case 4:
			System.out.println("Saliendo");
			pausa(2000);
			System.out.println("GRACIAS POR SU VISITA");
			break;
		default: 	
			System.out.println("NO SE RECONOCE OPCION");
			mostrarMenu();
			
		}
				
	}
	
	private void modifReserva() throws SQLException, ParseException {
		Reserva res = new Reserva(this.usuario);
		res.getReserva(this.usuario); 
 		System.out.println("________________");
 		System.out.println("                ");
 		System.out.print("\n");
 		System.out.print("Selecciona reserva");
 		int reserva = t.nextInt();
 		Conecting conn = new Conecting();
 		conn.EliminarReserva(reserva, this.usuario);
		System.out.println("Espere");
 		pausa(1000);
 		res.creaReserva();  
 		System.out.println("Modifique su reserva");
 		pausa(1000);
 				
	}
	
	private void Salir() throws SQLException, ParseException {
		System.out.println("VOLVIENDO A MENU PRINCIPAL \n");
 		Principal pi= new Principal();
	}
	private void pausa(int seg) {
		  try {
  			   Thread.sleep(seg);
  	       	} catch (InterruptedException e) {
  		    	e.printStackTrace();
  	       	}
		  
	}
		
}