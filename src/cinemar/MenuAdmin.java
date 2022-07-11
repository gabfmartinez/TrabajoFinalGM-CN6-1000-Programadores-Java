package cinemar;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdmin {
	private int opcion;
	private int usuario;
	private int descuentos;
	Reserva reserva = new Reserva();
	Cartelera cartelera = new Cartelera();
	Peliculas peli = new Peliculas();
	Conecting conn = new Conecting();
	Scanner t = new Scanner(System.in);
	String cont="";

	public MenuAdmin() {
		
	}
	
	public MenuAdmin(int u) {
		this.usuario=u;
	}
	
    public void mostrarMenu() throws SQLException, ParseException {
				
		System.out.println("Entrando \n");
		Usuario user= new Usuario();
		user.ifoUsuario(this.usuario);
		pausa(2000);
		System.out.println("MENU ADMINISTRADOR");
		//do {
		System.out.print(" \n");
		System.out.print("RESERVAS:   (presione 1) \n");
		System.out.print("CARTELERAS: (presione 2) \n");
		System.out.print("DESCUENTOS: (presione 3) \n");
		System.out.print("PELICULAS : (presione 4) \n");
		
		System.out.print(" \n");
		System.out.print(" \n");
     	System.out.print("CERRAR SESION:     (precione 5) \n");
     	System.out.print("SALIR DEL SISTEMA: (precione 0) \n");
     	System.out.print(" \n");
     	System.out.print("Opcion: ");
     	this.opcion = t.nextInt();
     	
     	switch(this.opcion) {
     	
     	case 1:
     	    subMenuReserva();
     		break;
     	
     	case 2:
     		subMenuCartelera();
     		break;
     	case 3:
     		subMenuDesc();
     		break;
     	case 4:
     		peli.getPeliculas();
     		System.out.print("Desea elegir un estreno S/N");
     		String est = t.next();
     		if(est.equals("s") || est.equals("S")) {
     			addPeliculas();
     		}else{
     		  volver();
     		}
     		
     		break;
        case 5:
        	Principal p = new Principal();
     		break;
        case 0:
		     System.out.print("SALIO DEL SISTEMA. REGRESE PRONTO.");
     		
		break;
     		
     	}
     	
        }
    
        @SuppressWarnings("unused")
		private void subMenuReserva() throws SQLException, ParseException {
        	System.out.println("Sub - Menu Reservas");
    		System.out.print(" \n");
    		System.out.print("Ver reservas:          (presione 1) \n");
    		System.out.print("Reservas por clientes: (presione 2) \n");
    		System.out.print("Volver:                (presione 3) \n");
    		
    		
    		System.out.print(" \n");
         	System.out.print("->CERRAR SESION:     (precione 4) \n");
         	System.out.print("->SALIR DEL SISTEMA: (precione 0) \n");
         	System.out.print(" \n");
         	System.out.print("Opcion: ");
         	
         	this.opcion = t.nextInt();
         	
         	switch(this.opcion) {
         	case 0:
         		System.out.println("Usted esta saliendo del sistema");
         		pausa(2000);
         		System.out.print("GRACIAS");
         		break;
         	
         	case 1:
         		reserva.verReservas();
         		System.out.print("Volver S/N...");
         		
         		if (cont.equals("s")) {
         			volver();	
         		}else {
         			volver();
         		}
         		
         		break;
         	case 2:
         		Usuario u = new Usuario();
         		u.getUsuario();
         		Reserva r=new Reserva(this.usuario);
         		System.out.print("Seleccionar usuario: ");
         		this.usuario = t.nextInt();
         		r.getReserva(this.usuario);
         		System.out.print("Volver S/N...");
         		if (cont.equals("s")) {
         			volver();	
         		}else {
         			volver();
         		}
         		break;	
         		
         	case 3:
         		volver();
         		break;
         	case 4:
         		Principal p = new Principal();
         		break;
         		
         	}
        }
        
        @SuppressWarnings("unused")
		private void subMenuCartelera() throws SQLException, ParseException {
        	System.out.println(" Submenu Carteleras");
    		System.out.print(" \n");
    		
    		System.out.print("Crear cartelera:     (presione 1) \n");
    		System.out.print("Modificar cartelera: (presione 2) \n");
    		System.out.print("Eliminar cartelera:  (presione 3) \n");
    		System.out.print("Listar cartelera:    (presione 4) \n");
    		System.out.print("Volver:              (presione 5) \n");
    		
    		
    		System.out.print(" \n");
         	System.out.print("CERRAR SESION:     (precione 6) \n");
         	System.out.print("SALIR DEL SISTEMA: (precione 0) \n");
         	System.out.print(" \n");
         	System.out.print("Opcion: ");
         	
         	this.opcion = t.nextInt();
         	switch(this.opcion) {
         	case 0:
         		System.out.println("Usted esta saliendo del sistema");
         		pausa(2000);
         		System.out.print("GRACIAS");
         	    break;
         	case 1:
         	   cartelera.formCartelera(); 
         	   System.out.print("Volver S/N...");
        		if (cont.equals("s")) {
        			volver();	
        		}else {
        			volver();
        		}
         	    
         	case 2:
         		cartelera.mostrarCarteleras();
         		System.out.print("\n");
         		System.out.print("Selecciona cartelera");
         		int c = t.nextInt();
         		cartelera.mostrarUnaCartelera(c);
         		
         		System.out.println("                                 ");
         		System.out.print("Modificar Horario:  (presione 1) \n");
        		System.out.print("Modificar Pelicula: (presione 2) \n");
        		System.out.print("Modificar Precio:   (presione 3) \n");
        		System.out.print("Opcion: ");
        		
        		int op=t.nextInt();
        		if (op==1) {
        			System.out.print("Ingrese nuevo horario: ");
        			String nHorario = t.next();
        			conn.ModificarElementos("cartelera","hora",nHorario,"idCartelera",c);
        		}
        		if (op==2) {
        			System.out.print("Seleccione Pelicula: ");
        			peli.getPeliculas();
        			System.out.print("Ingrese Id de la pelicula: ");
        			String nPeli = t.next();
        			conn.ModificarElementos("cartelera","idPelicula",nPeli,"idCartelera",c);
        		}
        		if (op==3) {
        			System.out.print("Ingrese nuevo precio: $");
        			String nPrecio = t.next();
        			conn.ModificarElementos("cartelera","precioTotal",nPrecio,"idCartelera",c);
        			
        		}
        		System.out.println("Aguarde");
         		pausa(10000);
         		System.out.println("Volviendo al menu administrador");
         		pausa(5000);
         		volver();
        		
         		break;
         	case 3:
         		
         		cartelera.mostrarCarteleras();
         		System.out.print("\n");
         		System.out.print("Selecciona cartelera");
         		System.out.print("Introdusca el ID de la cartelera: ");
         		int cartDel = t.nextInt();
         		cartelera.mostrarUnaCartelera(cartDel);
         		
         		System.out.println("__________________________________");
         		
         		System.out.println("Que tipo de borrado desea hacer...");
         		System.out.println("Deshabilitacion         Digite 1: ");
         		System.out.println("Borrado definitivo      Digite 2: ");
         		System.out.print("Ingrese aqui su eleccion: ");
         		int del = t.nextInt();
         		if(del==1) {
         			conn.ModificarElementos("cartelera","estado","0","idCartelera",cartDel);
         			pausa(1000);
         			volver();
         		}else if (del==2){
         			System.out.println("ATENCION");
         			System.out.println("El borrado es definitivo desea continuar S/N");
         			System.out.print("--->");
         			String out = t.next();
         			     if(out.equals("s") || out.equals("S")) {
         			        	conn.EliminarCartelera(cartDel);
         			            pausa(1000);
         			            volver();
         			        }else {
         			        	pausa(1000);
         			        	subMenuCartelera();
         			        }
         			
         		        }else{
         		        	System.out.println("Retornando");
             	        	pausa(1000);
             	        	subMenuCartelera();
         		        }
         		
         		break;
         	case 4:
         		cartelera.mostrarCarteleras();
         		System.out.println("Espere");
         		pausa(10000);
         		System.out.println("Volviendo al menu administrador");
         		pausa(5000);
         		volver();
         		break;
         	case 5:
         		volver();
         		break;
         	case 6:
         		Principal p =new Principal();
         		break;
         	default:
         		System.out.print("Opcion incorrecta");
         		volver();
         		
         	}
         	
        }
                
        private void subMenuDesc() throws SQLException, ParseException {
        	
        	descuentos();
        	
        	
        	System.out.println("Submenu Descuentos");
    		System.out.print("----------------------------------- \n");
    		System.out.print("Nuevo descuento:       (presione 1) \n");
    		System.out.print("Modificar descuento:   (presione 2) \n");
    		System.out.print("Desabilitar descuento: (presione 3) \n");
    		System.out.print("Volver:                (presione 4) \n");
    		
    		System.out.print("----------------------------------- \n");
         	System.out.print("->CERRAR SESION:       (precione 5) \n");
         	System.out.print("->SALIR DEL SISTEMA:   (precione 0) \n");
         	System.out.print("----------------------------------- \n");
         	System.out.print("Opcion: ");
         	
         	this.opcion = t.nextInt();
         	switch(this.opcion) {
         	
         	case 0:
         		System.out.print("Usted esta saliendo del sistema");
         		pausa(2000);
         		System.out.print("GRACIAS");
         		break;
         	case 1:
         		System.out.print("Total descuento en %: ");
         		this.descuentos = t.nextInt();
         		System.out.print("Dia de descuento (lunes, martes, etc): ");
         		String dia = t.next();
         		System.out.print("Confirmar: S/N");
         		String co = t.next();
         		if(co.equals("s")) {
         			nuevoDescuento(this.descuentos, dia);
         		}
         		volver();
         		break;
         		
         	case 2:
         		System.out.println("Muy pronto");
         		pausa(2000);
         		volver();
         		break;
         	case 3:
         		System.out.println("Muy pronto");
         		pausa(2000);
         		volver();
         		
         		break;
         	case 4:
         		volver();
         		break;
         	case 5:
         		Principal p= new Principal();
         		break;
         	default:
         		System.out.print("Opcion incorrecta");
         		pausa(2000);
         		volver();
         		break;
         	}
    		
        }
        
        private void addPeliculas() throws SQLException, ParseException {
        	
        	System.out.print("Ingrese nombre de la nueva pelicula: ");
        	String np = t.next(); 
        	System.out.print("Ingrese duracion aprox de la nueva pelicula: ");
        	String dur = t.next();
        	
        	System.out.println("CALIFICACION");
      	    System.out.println("Atp             (1) ");
      	    System.out.println("P/mayores 12    (2) ");
      	  System.out.println("--------------------- ");
      	    
        	System.out.print("Ingrese calificacion de la nueva pelicula: ");
        	int cal = t.nextInt();
        	
        	System.out.println("GENERO");
      	    System.out.println("Comedia         (1) ");
      	    System.out.println("Accion          (2) ");
      	    System.out.println("Infantil        (3) ");
      	    System.out.println("Terror          (4) ");
      	    System.out.println("Drama           (5) ");
      	    System.out.println("Suspenso        (6) ");
      	    System.out.println("------------------------- ");
        	System.out.print("Ingrese genero de la nueva pelicula: ");
        	
        	int genero = t.nextInt();
        	
        	ArrayList<String> LisPel = new ArrayList<String>();
        	LisPel.add(np);
        	LisPel.add(dur);
        	LisPel.add("estreno");
        	ArrayList<Integer> LisPel1 = new ArrayList<Integer>();
        	LisPel1.add(cal);
        	LisPel1.add(genero);
        	
        	conn.AgregarPelicula(LisPel1, LisPel);
        	pausa(3000);
        	mostrarMenu();
        	
        }
        
        private void volver() throws SQLException, ParseException {
        	mostrarMenu();
        }
        
        private void descuentos() {
        	
        	int entrada=600;
        	int desc1=(entrada*20)/100;
        	int desc3=(entrada*20)/100;
        	int desc2=(entrada*20)/100;
        	System.out.println("------------------------------- \n");
        	System.out.println("Los descuentos disponibles son: \n");
        	System.out.println("Lunes y Miercoles: %20 Precio Final:$ "+ (entrada -desc1));
        	System.out.println("Martes y Jueves: %15  Precio Final:$ " +(entrada-desc2) );
        	System.out.println("Viernes, Sabado y Domingo: %10 Precio Final:$ " +(entrada- desc3) );
        	System.out.println("------------------------------- \n");
        	
        }
        
        private void nuevoDescuento(int desc, String dia) {
        	descuentos();
        	System.out.println("Descuento nuevo : "+dia+" %"+desc);
        	
        }
        
        private void pausa(int seg) {
	            try {
	        		   Thread.sleep(seg);
	               	} catch (InterruptedException e) {
	        	    	e.printStackTrace();
	        	    	
        	       	}
	            
        }

}