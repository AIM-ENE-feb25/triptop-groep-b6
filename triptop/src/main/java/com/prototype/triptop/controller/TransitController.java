package com.prototype.triptop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TransitController {

        @GetMapping("api/getAllDepartures")
        public String getAllDepartures(@RequestParam String fromLatitude, @RequestParam String fromLongitude,
                        @RequestParam String departure)
                        throws IOException {
                OkHttpClient client = new OkHttpClient();

                String BASE_URL = "https://wikiroutes-api.p.rapidapi.com/nextDepartures?location=" + fromLatitude + ","
                                + fromLongitude + "&radius=10&results=25&requestTime=" + departure;

                Request request = new Request.Builder()
                                .url(BASE_URL)
                                .get()
                                .addHeader("x-rapidapi-key", "a074e1a2e5msh00771683f45ee6bp1aa067jsncc90ae852fdb")
                                .addHeader("x-rapidapi-host", "wikiroutes-api.p.rapidapi.com")
                                .build();

                Response response = client.newCall(request).execute();
                return response.body().string();
        }

        @GetMapping("api/getRoutes")
        public String getRoutes(@RequestParam String fromLatitude, @RequestParam String fromLongitude,
                        @RequestParam String toLatitude, @RequestParam String toLongitude)
                        throws IOException {
                OkHttpClient client = new OkHttpClient();

                String BASE_URL = "https://wikiroutes-api.p.rapidapi.com/routes?origin=" + fromLatitude + ","
                                + fromLongitude + "&destination=" + toLatitude + "," + toLongitude + "&transfers=1";

                Request request = new Request.Builder()
                                .url(BASE_URL)
                                .get()
                                .addHeader("x-rapidapi-key", "a074e1a2e5msh00771683f45ee6bp1aa067jsncc90ae852fdb")
                                .addHeader("x-rapidapi-host", "wikiroutes-api.p.rapidapi.com")
                                .build();

                Response response = client.newCall(request).execute();
                return response.body().string();
        }

}
