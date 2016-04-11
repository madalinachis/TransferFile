package licenta.tranferfile;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Madalina.Chis on 4/11/2016.
 */
public class WiFiClientIPTransferService extends IntentService {

    public WiFiClientIPTransferService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getAction().equals(FileTransferService.ACTION_SEND_FILE)) {
            String host = intent.getExtras().getString(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS);
            String InetAddress = intent.getExtras().getString(FileTransferService.inetaddress);
            Socket socket = new Socket();
            int port = intent.getExtras().getInt(FileTransferService.EXTRAS_GROUP_OWNER_PORT);

            try {
                Log.d(WiFiDirectActivity.TAG, "Opening client socket for First tiime- ");
                socket.bind(null);
                if (host != null) {
                    socket.connect((new InetSocketAddress(host, port)), FileTransferService.SOCKET_TIMEOUT);
                }
                Log.d(WiFiDirectActivity.TAG, "Client socket - " + socket.isConnected());
                OutputStream stream = socket.getOutputStream();

               /*
                * Object that is used to send file name with extension and recieved on other side.
                */
                ObjectOutputStream oos = new ObjectOutputStream(stream);
                WiFiTransferModal transObj = new WiFiTransferModal(InetAddress);

                oos.writeObject(transObj);
                System.out.println("Sending request to Socket Server");
                oos.close();    //close the ObjectOutputStream after sending data.
            } catch (IOException e) {
                Log.e(WiFiDirectActivity.TAG, e.getMessage());
                e.printStackTrace();
            } finally {
                if (socket.isConnected()) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
