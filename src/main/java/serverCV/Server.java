package serverCV;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * Server Class
 * @author Bernucci Elena 740283 VA
 * @author Clementi Luca 740350 VA
 */

public class Server {
    /**
     * First Runnable Program
     * @param args
     */

    public static void main(String[] args) {
        try{
            Semaphore sem= new Semaphore(100);
            ServerSocket server = new ServerSocket(InformazioniServer.getPORT());

            Scanner scan = new Scanner(System.in);

            System.out.println("DB Username: ");
                InformazioniServer.setPGUSERNAME(scan.nextLine());
            System.out.println("DB Password: ");
                InformazioniServer.setPGPASSWORD(scan.nextLine());

            while(!tryConnection(InformazioniServer.getPGUSERNAME(),
                    InformazioniServer.getPGPASSWORD(),
                    InformazioniServer.getDBNAME(),
                    InformazioniServer.getIPSERVER(),
                    InformazioniServer.getDBPORT())){
                System.out.println("Errore nel inserimento di credenziali, riprova!");

                System.out.println("DB Username: ");
                    InformazioniServer.setPGUSERNAME(scan.nextLine());
                System.out.println("DB Password: ");
                    InformazioniServer.setPGPASSWORD(scan.nextLine());
            }

            System.out.println("Connesso!");

            try {
                System.out.println("Server: " + server);

                while(true) {
                    Socket socket = server.accept();

                    new ConessioneServer(socket, sem, InformazioniServer.getPGUSERNAME(), InformazioniServer.getPGPASSWORD());
                }
            } catch(Exception e)  {
                e.printStackTrace();
            }

        }catch (Exception e){
            System.out.println("Server err: " + e.getMessage());
        }
    }

    public static Boolean tryConnection(String username, String password, String database, String ipAddress, int port) throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        try{
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://" + ipAddress + ":" +
                            port + "/" +
                            database +
                            "?&useUnicode=true&characterEncoding=utf8",
                            username,
                            password);
        }catch (SQLException e){
            return false;
        }
        return true;
    }
}
