/**
 * Author : S.Alireza  Moazeni
 * Student Number : 9423110
 * Project 1 : Proxy Server
 * Web Programming winter_spring 1397_1398
 */
package Models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class run a listener on a specific socket
 * and create request handler object for each tcp connection
 */
public class SocketListener implements Runnable {
    public ExecutorService pool;
    public boolean startStop;
    public ServerSocket server;
    public int serverPort;
    public Backend back;

    public SocketListener(Backend back) {
        this.back = back;
        startStop = true;
        pool = Executors.newCachedThreadPool();
        serverPort = 8086;
        configServerPort();
    }

    /**
     * this function try to find free port to start proxy server
     */
    private void configServerPort() {
        boolean needChangePort = true;
        while (needChangePort) {
            try {
                server = new ServerSocket(serverPort);
                needChangePort = false;
            } catch (IOException e) {
                serverPort += 10;
            }
        }
    }

    /**
     * to stop server we must close thread pool and socket
     * @throws IOException
     */
    public void stopServer() throws IOException {
        startStop = false;
        pool.shutdown();
        server.close();
    }

    /**
     * to resume server we init new port and thread pool
     */
    public void resumServer() {
        pool = Executors.newCachedThreadPool();
        startStop = true;
        configServerPort();
        run();
    }

    @Override
    public void run() {
        // we must listen for incoming request to open tcp sockets always
        while (startStop) {
            Socket request = null;
            try {
                request = server.accept();

                //handle each accepted socket separately
                pool.execute(new RequestHandler(request));
            } catch (IOException e) {
                //System.out.println("TCP connection did not established...");
            }
        }
    }
}

