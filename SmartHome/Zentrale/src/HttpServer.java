

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class HttpServer extends Thread {
    private ServerSocket tcpServerSocket;
    private Socket connectionSocket = null;
    private boolean running = true;



    /**
     *
     * @param
     * @throws IOException
     */
    HttpServer(int port)throws IOException {
        this.tcpServerSocket = new ServerSocket(port);


    }

    public void run(){
        while(running){
            //connectionSocket = null;
            try {
                connectionSocket = tcpServerSocket.accept();
                Runnable httpInnerThread = new Runnable() {
                    @Override
                    public void run() {
                        try {

                            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                            PrintWriter headerOutput = new PrintWriter(connectionSocket.getOutputStream());
                            String header = inFromClient.readLine();

                            StringTokenizer headerToken = new StringTokenizer(header);
                            String method = headerToken.nextToken();
                            String rest = headerToken.nextToken();
                            System.out.println(header);
                            printClientMessage(inFromClient);

                            if (method.equals("GET")) {
                                //outToClient.writeBytes("Sie haben einen HTTP-Request erfolgreich versendet.");
                                //Accept HTTP-GET mit /history
                                if (rest.equals("/")) {
                                    ArrayList<DataStruct> currentData = DataSingleton.getInstance().getCurrentData();
                                    String outputTest = "";

                                    for (int i = 0; i < currentData.size(); i++) {
                                        outputTest = outputTest + "Port: " + currentData.get(i).getPort() + "; " + "Typ: " + currentData.get(i).getType() + "; " + "Wert: " + currentData.get(i).getValue() + ";\n";
                                        //System.out.println(outputTest);
                                    }
                                    headerOutput.println("HTTP/1.1 200 OK");
                                    headerOutput.println("Server: Java Server");
                                    headerOutput.println("Date: " + new Date());
                                    headerOutput.println("Content-type: " + "text");
                                    headerOutput.println("Content-length: " + outputTest.getBytes().length);
                                    headerOutput.println();
                                    headerOutput.flush();
                                    outToClient.write(outputTest.getBytes());

                                } else if (rest.equals("/history")) {
                        /*
                        File requestedFile = new File("../Zentrale/WebFiles/history.txt");
                        int fileLength = (int)requestedFile.length();
                        headerOutput.println("HTTP/1.1 200 OK");
                        headerOutput.println("Server: Java Server");
                        headerOutput.println("Date: " + new Date());
                        headerOutput.println("Content-type: " +  "text");
                        headerOutput.println("Content-length: " + fileLength);
                        headerOutput.println();
                        headerOutput.flush();
                        outToClient.write(readFileData(requestedFile, fileLength));
                        */
                                    ArrayList<DataStruct> currentData = DataSingleton.getInstance().getData();
                                    String outputTest = "";

                                    for (int i = 0; i < currentData.size(); i++) {
                                        outputTest = outputTest + "Port: " + currentData.get(i).getPort() + "; " + "Typ: " + currentData.get(i).getType() + "; " + "Wert: " + currentData.get(i).getValue() + ";\n";
                                        //System.out.println(outputTest);
                                    }
                                    headerOutput.println("HTTP/1.1 200 OK");
                                    headerOutput.println("Server: Java Server");
                                    headerOutput.println("Date: " + new Date());
                                    headerOutput.println("Content-type: " + "text");
                                    headerOutput.println("Content-length: " + outputTest.getBytes().length);
                                    headerOutput.println();
                                    headerOutput.flush();
                                    outToClient.write(outputTest.getBytes());

                                } else if (rest.equals("/favicon.ico")) {
                                    File requestedFile = new File("../Zentrale/WebFiles/favicon.ico");
                                    int fileLength = (int) requestedFile.length();
                                    outToClient.write(readFileData(requestedFile, fileLength));
                                }
                            } else {
                                outToClient.writeBytes("Falsche HTTP Methode.");
                            }
                        } catch (IOException e) {
                            System.out.println("Fehler beim Verarbeiten und Response: " + e);
                        }

                    }




                    // String hello = "hello";
                    // outToClient.write(response.getBytes());
                };
                httpInnerThread.run();

            }catch (IOException e){
                System.out.println("Fehler beim Empfangen: " + e);
            }finally{
                if(connectionSocket != null){
                    try {
                        connectionSocket.close();
                    } catch (IOException e) {
                        //e.printStackTrace();
                    }
                }
            }

        }
    }



    private void printClientMessage(BufferedReader bf) throws IOException {
        String streamLine = bf.readLine();

        while(!streamLine.isEmpty()) {

            System.out.println("Info: " + streamLine);
            streamLine = bf.readLine();
        }

    }

    private byte[] readFileData(File file, int filelength) throws IOException{
        FileInputStream fileIn = null;
        byte[] fileData = new byte[filelength];

        fileIn = new FileInputStream(file);
        fileIn.read(fileData);
        if(fileIn != null){
            fileIn.close();
        }

        return fileData;
    }

}
