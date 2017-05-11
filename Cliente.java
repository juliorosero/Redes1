package ec.epn.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Cliente {

	private static int SERVER_PORT =9091;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
//		Creamos un lazo para solicitar al cliente si desea volver a ingresar datos 
		int opcion;
		do{
//			Permite al usuario ingresar la IP del servido
			String serverAddress =JOptionPane.showInputDialog("Ingrese la direccion IP del servidor: ", "172.29.22.190");

//			Crea un socket para el cliente
			Socket clientSocket =new Socket(serverAddress, SERVER_PORT);
			
//			Atrapa el error en caso de que la direccion IP sea distinta a la del servidor
			try{
//				Envia los datos ingresados por el cliente al servidor mediante un socket
				PrintWriter out =new PrintWriter (clientSocket.getOutputStream(), true); 
				String datos= JOptionPane.showInputDialog("Ingrese los numeros a sumar separados por coma");
				out.println(datos);
			


//				Obtiene el mensaje enviado por el servidor a travez de socket
				InputStreamReader inputStream =new InputStreamReader (clientSocket.getInputStream());
			
			
//				lee los datos del mensaje y los guarda un una variable
				BufferedReader input =new BufferedReader(inputStream);
				String respuesta =input.readLine();
			
			
//			imprime el mensaje enviado por el servidor
			JOptionPane.showMessageDialog(null, respuesta);
			
//			
			} finally{
			clientSocket.close();
		}
//		Pregunta al cliente si desea seguir ingrsenado datos
		opcion=JOptionPane.showConfirmDialog(null, "Desea seguir sumando con el mismo servidor", "Continuar",JOptionPane.YES_NO_OPTION);
		}while(opcion==JOptionPane.YES_NO_OPTION);
		
//		finaliza el programa
		System.exit(0);
		}
}
