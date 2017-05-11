package ec.epn.redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Servidor {
	
	private static int PORT =9091;

	public static void main(String[] args) throws IOException {
		int a,b, suma;
		
//		Crea un servidor socket para el servidor y n que socket está
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Servidor escuchando al puerto "+ PORT);
		
		try{
			while(true){    //servidor dedicado (siempre encendido)
				Socket socket =serverSocket.accept();
				try{
					
//					Obtiene el IP ingresado por el cliente
					InputStreamReader inputStream =new InputStreamReader (socket.getInputStream()); 
					
//					lee el mensaje enviado desde el cliente y lo almacena en na variable
					BufferedReader input =new BufferedReader(inputStream); 
					String answer =input.readLine();
					
//					Atrapa el error, en caso de que el mensaje contenga errores
					try{
//						tokeniza el mensaje
						StringTokenizer tokens= new StringTokenizer(answer, ",");
						a= Integer.parseInt(tokens.nextToken());
						b= Integer.parseInt(tokens.nextToken());
						suma= a+b;
						
//						Almacena la respuesta y la envia al cliente
						String respuesta= "La suma de ["+ a+ " +"+ b+ "] es: "+ suma;
						PrintWriter out =new PrintWriter (socket.getOutputStream(), true); // enviar algo
						out.println(respuesta);	
									
			    	}catch(NumberFormatException ex){
//			    		Envía un mensaje al usuario indicando que hay un error
			    		String error= "Error en los datos";
			    		PrintWriter out =new PrintWriter (socket.getOutputStream(), true); // enviar algo
						out.println(error);			        	
			    	}		
					
				} finally{
					socket.close();
				}
			}
		}
		finally {
			serverSocket.close();
		}
		
	}

}
