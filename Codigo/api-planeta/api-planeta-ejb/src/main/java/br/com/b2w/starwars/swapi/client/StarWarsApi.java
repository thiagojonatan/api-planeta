package br.com.b2w.starwars.swapi.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class StarWarsApi {
	
	 public static final String BASE_URL = "https://swapi.co/api/";
	 public static final String USER_AGENT_NAME = "B2W-Java-Client/1.0";
	
	 private static final Logger log = Logger.getLogger(StarWarsApi.class);

	    private Swapi swService;
	    private static StarWarsApi instance;

	    private StarWarsApi() {
	        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
	                .connectTimeout(5, TimeUnit.SECONDS)
	                .readTimeout(30, TimeUnit.SECONDS)
	                .addInterceptor(new UserAgentInterceptor(USER_AGENT_NAME))
	                .addInterceptor(new RequestLoggingInterceptor());

	        Retrofit retrofit = new Retrofit.Builder()
	                .baseUrl(BASE_URL)
	                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper().registerModule(new JavaTimeModule())))
	                .client(httpClientBuilder.build())
	                .build();

	        swService = retrofit.create(Swapi.class);
	    }

	    public static Swapi getApi() {
	        if (instance == null) {
	            instance = new StarWarsApi();
	        }
	        return instance.swService;
	    }

	    private static class RequestLoggingInterceptor implements Interceptor
	    {

	        @Override
	        public Response intercept(Chain chain) throws IOException {
	            Request request = chain.request();
	            log.debug("HTTP Request : " + request);
	            log.debug("HTTP Request headers : " + request.headers());

	            return chain.proceed(request);
	        }
	    }

	    private static class UserAgentInterceptor implements Interceptor
	    {
	        private final String userAgent;

	        public UserAgentInterceptor(String userAgent) {
	            this.userAgent = userAgent;
	        }

	        @Override
	        public Response intercept(Chain chain) throws IOException {
	            Request request = chain.request();
	            Request requestWithUserAgent = request.newBuilder()
	                    .header("User-Agent", userAgent)
	                    .build();

	            return chain.proceed(requestWithUserAgent);
	        }
	    }

}
