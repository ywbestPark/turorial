package com.example.tutorial.component;

import com.example.tutorial.dto.Tour.Root;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestRunnerTest {

    @Autowired
    public WebClient webClient;

    @Test
    public void webClientTest() throws Exception{
        String url = "http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?" +
                "serviceKey=G8dsIOIinqIgxBio8kv6qyrYJ9OsQr%2F5PhyVheRm9NbJfGNNYQSUSKYZH%2BRlgmYs01YxjwihIRUKKyQE1j%2FO%2Fw%3D%3D" +
                "&numOfRows=10" +
                "&pageNo=1" +
                "&CURRENT_DATE=20220108" +
                "&HOUR=24" +
                "&COURSE_ID=9" +
                "&dataType=JSON";

        URI uri = new URI(url);

        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                });

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();

        Mono<Root> tourStnVilageFcstDTOMono =
                client.get()
                      .uri(uri)
                      .retrieve()
                      .bodyToMono(Root.class);
        tourStnVilageFcstDTOMono.doOnNext(value ->
                System.out.println(value.getResponse().getBody().getItems().toString())
        ).block();
    }

    @Test
    public void test3() throws URISyntaxException {

        String url = "http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?" +
                "serviceKey=G8dsIOIinqIgxBio8kv6qyrYJ9OsQr%2F5PhyVheRm9NbJfGNNYQSUSKYZH%2BRlgmYs01YxjwihIRUKKyQE1j%2FO%2Fw%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=200" +
                "&CURRENT_DATE=20220108" +
                "&HOUR=24" +
                "&COURSE_ID=9" +
                "&dataType=JSON";

        URI uri = new URI(url);

        Mono<Root> tourStnVilageFcstDTOMono = webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(Root.class);

        tourStnVilageFcstDTOMono.doOnNext(root ->
                System.out.println(root.getResponse().getBody().getItems().toString())
        ).block();
        System.out.println("$$$$$$$$$$$$$$$$$$$$5");
    }


//    public WeatherWebClient() {
//        this.weatherWebClient = WebClient.create("http://api.openweathermap.org/data/2.5/weather");
//    }
//
//    public Mono<String> getWeatherByCityName(String cityName) {
//        return weatherWebClient
//                .get()
//                .uri(uriBuilder -> uriBuilder
//                        .queryParam("q", cityName)
//                        .queryParam("units", "metric")
//                        .queryParam("appid", API_KEY)
//                        .build())
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class);
//    }

}