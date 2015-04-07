package com.example.cameraopencv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.app.Activity;
import android.net.nsd.NsdManager;
//import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ClientActivity extends Activity {
    
    // NSD Manager, discovery listener code here
    private int SocketServerPort = 8005;
    private static final String REQUEST_CONNECT_CLIENT = "request-connect-client";
    
    NsdManager.ResolveListener mResolveListener = new NsdManager.ResolveListener() {
    
        @Override
        public void onServiceResolved(NsdServiceInfo serviceInfo) {
         //   Log.d(TAG, "Resolve Succeeded. " + serviceInfo);
            
//            if (serviceInfo.getServiceName().equals(SERVICE_NAME)) {
//              //  Log.d(TAG, "Same IP.");
//                return;
//            }
        
            // Obtain port and IP
            int hostPort = 8005;
            hostAddress = "192.168.137.11";
            
            /* Once the client device resolves the service and obtains
             * server's ip address, connect to the server and send data
             */
             
            connectToHost();
        }

		@Override
		public void onResolveFailed(NsdServiceInfo serviceInfo, int errorCode)
		{
			// TODO Auto-generated method stub
			
		}
    };
    String hostAddress = "192.168.137.11"; 
    private void connectToHost() {
    
        if (hostAddress == null) {
         //   Log.e(TAG, "Host Address is null");
            return;
        }
    
        String ipAddress = getLocalIpAddress();
        JSONObject jsonData = new JSONObject();
    
        try {
            jsonData.put("request", REQUEST_CONNECT_CLIENT);
            jsonData.put("ipAddress", ipAddress);
        } catch (JSONException e) {
            e.printStackTrace();
         //   Log.e(TAG, "can't put request");
            return;
        }
        new SocketServerTask().execute(jsonData);
    }
//    
    private String getLocalIpAddress() {
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }
	
    private class SocketServerTask extends AsyncTask<JSONObject, Void, Void> {
        private JSONObject jsonData;
        private boolean success;
	    
        @Override
        protected Void doInBackground(JSONObject... params) {
            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            jsonData = params[0];
			
            try {
                // Create a new Socket instance and connect to host
                socket = new Socket(hostAddress, 8005);
                
                dataOutputStream = new DataOutputStream(
                		                socket.getOutputStream());
                
                // transfer JSONObject as String to the server
                dataOutputStream.writeUTF(jsonData.toString());
              //  Log.i(TAG, "waiting for response from host");
            } catch (IOException e) {
                e.printStackTrace();
                success = false;
            } finally {
            
                // close socket
                if (socket != null) {
                    try {
                     //   Log.i(TAG, "closing the socket");
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                               
                // close output stream
                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return null;
        }
		
        
    }
	
}