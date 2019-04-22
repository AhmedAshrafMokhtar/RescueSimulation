package Chating;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket welcomeSocket;
	private InputStream input;
	private OutputStream output;
	public OutputStream getOutput() {
		return output;
	}

	public ServerSocket getWelcomeSocket() {
		return welcomeSocket;
	}

	public void setWelcomeSocket(ServerSocket welcomeSocket) {
		this.welcomeSocket = welcomeSocket;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public Server(InputStream input,OutputStream output) throws IOException {
		this.input=input;
		this.output=output;
		PrintStream printStream = new PrintStream(output);
		System.setOut(printStream);
		System.setErr(printStream);
		welcomeSocket=new ServerSocket(5000);
		Socket connectionSocket=welcomeSocket.accept();
		BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		String clientSentence=inFromClient.readLine();
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		String returnSentence;
		boolean available=false;
		BufferedReader inServer=new BufferedReader(new InputStreamReader(input));
		if(clientSentence.equals("CONNECT")) {
			available=true;
			outToClient.writeBytes("connected"+"\n");
		}
		while(true) {
			if(available) {
				if(inFromClient.ready()) {
					clientSentence=inFromClient.readLine();
					if(clientSentence!=null) {
						System.out.println("From Client : "+clientSentence);
					}
				}
				if(inServer.ready()) {
					returnSentence=inServer.readLine();
					outToClient.writeBytes(returnSentence+"\n");
				}
			}
			else {
				if(inFromClient.ready()) {
					clientSentence=inFromClient.readLine();
					if(clientSentence.equals("CONNECT")) {
						outToClient.writeBytes("connected"+"\n");
						available=true;
					}
				}	
			}
		}
	}
	
	public void close() throws IOException {
		welcomeSocket.close();
	}

}
