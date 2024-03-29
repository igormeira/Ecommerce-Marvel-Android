package com.igormeira.comics.retrofit.service;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface para as requisições feitas à Api
 */
public interface ComicService {

    /**
     * Recupera objeto com as HQs.
     *
     * @return Object
     */
    @GET("comics?ts=1&apikey=bdfa59afe57cf329d31b564a402f592d&hash=30984123de3d753fe4ffa9af51d0eff2")
    Call<Object> searchAll();

}
