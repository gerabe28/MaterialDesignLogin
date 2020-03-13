package com.example.materialdesign

import android.telecom.Call
import retrofit2.http.*

interface LastFmService {

    @GET("/2.0/?method=artist.search")
    fun searchArtistInfo(@Query("mbid") id: String): Call

    @GET("/2.0/?method=artist.getinfo")
    fun requestArtistInfo(@Query("mbid") id: String,
                          @Query("lang") language: String): Call

    @GET("/2.0/?method=artist.gettopalbums")
    fun requestAlbums(@Query("mbid") id: String,
                      @Query("artist") artist: String) : Call

    @GET("/2.0/?method=artist.getsimilar")
    fun requestSimilar(@Query("mbid") id: String): Call

    @GET("/2.0/?method=album.getInfo")
    fun requestAlbum(@Query("mbid") id: String): Call
}