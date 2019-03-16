package Models;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        serverPort = 7653;
        configServerPort();
    }

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

    public void stopServer() {
        pool.shutdown();
    }

    public void resumServer() {
        pool = Executors.newCachedThreadPool();
        startStop = true;
        configServerPort();
        run();
    }

    @Override
    public void run() {
        while (startStop) {
            Socket request = null;
            try {
                request = server.accept();
                pool.execute(new RequestHandler(request));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

