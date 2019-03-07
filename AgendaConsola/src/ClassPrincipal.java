import java.util.ArrayList;

public class ClassPrincipal {

	public static void main(String[] args) {
	Contacto c=new Contacto("Teresa", "tere@mail.com", "556445787");
	AccesoBD.insertarContacto(c);
	
	ArrayList<Contacto> lista=AccesoBD.devolverContactos();
	System.out.println(lista);
	
	}

}
