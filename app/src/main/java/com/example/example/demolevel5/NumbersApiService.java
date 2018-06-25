package com.example.example.demolevel5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface where all the functions are for making http requests.
 */
public interface NumbersApiService {
    // The string in the GET annotation is added to the BASE_URL
    @GET("/{month}/{dayOfMonth}/date?json")
    // The query is added after ?json, for example it could look like '?json&max=999'
    Call<DayQuoteItem> getTodaysQuote(@Path("month") int monthNumber, @Path("dayOfMonth") int dayOfMonth);
}
