package com.example.funretrofit;

import com.google.gson.annotations.SerializedName;

public class ResponseList {

    @SerializedName("timeStamp")
    private String timeStamp;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jenisKelamin")
    private String jenisKelamin;

    @SerializedName("alamat")
    private String alamat;

    public ResponseList(String timeStamp, String nama, String jenisKelamin, String alamat) {
        this.timeStamp = timeStamp;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }
}