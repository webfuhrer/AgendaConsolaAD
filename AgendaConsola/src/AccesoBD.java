import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;





public class AccesoBD {
	public static void crearTabla()
	{
		//-----
		
		String sql="CREATE TABLE IF NOT EXISTS tabla_pruebas(id INTEGER AUTO_INCREMENT, nombre VARCHAR(10), apellido VARCHAR(50), PRIMARY KEY (id))";
	}
	public static void insertarContacto(Contacto c)  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
			/*Statement stmt=conexion.createStatement();
			String sql="INSERT INTO t_contactos VALUES('"+c.getNombre()+"', '"+c.getTelefono()+"', "
					+ "'"+c.getEmail()+"')";
			stmt.executeUpdate(sql);
			stmt.close();*/
			String sql="INSERT INTO t_contactos(nombre, telefono, email) VALUES(?, ?, ?)";
			//¿tengo que poner el nombre de cada columna?
			//No si metes el mismo numero de valores que columnas tiene la tabla
			
			PreparedStatement pstmt=conexion.prepareStatement(sql);
			pstmt.setString(1, c.getNombre());
			pstmt.setString(2, c.getTelefono());
			pstmt.setString(3, c.getEmail());
			pstmt.executeUpdate();		
			conexion.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static ArrayList<Contacto> devolverContactos()
	{
		ArrayList<Contacto> lista_contactos=new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
		String sql="SELECT * FROM t_contactos";
		Statement stmt=conexion.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			String nombre=rs.getString("nombre");
			String telefono=rs.getString("telefono");
			String email=rs.getString("email");
			Contacto c=new Contacto(nombre, email, telefono);
			lista_contactos.add(c);
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista_contactos;
				
	}

}
