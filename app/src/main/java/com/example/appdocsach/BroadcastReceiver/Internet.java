package com.example.appdocsach.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Internet extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Lấy đối tượng ConnectivityManager để kiểm soát kết nối mạng
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        // Đăng ký NetworkCallback để lắng nghe thay đổi trạng thái kết nối mạng
        connectivityManager.registerNetworkCallback(builder.build(), new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                // Khi có kết nối mạng
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLost(Network network) {
                // Khi mất kết nối mạng
                Toast.makeText(context, "Internet Disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isNetworkAvailable(@NonNull Context context) {
        // Lấy đối tượng ConnectivityManager để kiểm soát kết nối mạng
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null) {
            return false; // Trả về false nếu không có ConnectivityManager
        }
        Network network = connectivityManager.getActiveNetwork();
        if(network == null) {
            return false; // Trả về false nếu không có kết nối mạng
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
        // Trả về true nếu có kết nối mạng Wi-Fi, ngược lại trả về false
        return networkCapabilities != null && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
    }
}
