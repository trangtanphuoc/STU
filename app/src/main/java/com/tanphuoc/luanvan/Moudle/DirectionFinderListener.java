package com.tanphuoc.luanvan.Moudle;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public interface DirectionFinderListener {
    void onDirectionFinderStarttimtram();
    void onDirectionFinderSuccessATM(List<TramATM> listATM,int radius);
    void onDirectionFinderSuccessTX(List<TramXang> TramXang,int radius);
    void SendDataXang(List<TramXang> listTX);
    void SendDataATM (List<TramATM> listATM);
    void AddMarketVitriKhac (LatLng a,String vitri,boolean loai);
}
