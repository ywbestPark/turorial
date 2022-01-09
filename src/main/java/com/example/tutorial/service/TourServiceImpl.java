package com.example.tutorial.service;

import com.example.tutorial.dto.Tour.Item;
import com.example.tutorial.dto.Tour.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TourServiceImpl implements TourService {

    private final WebClient webClient;

    @Override
    public List<Item> getTourList(int courseId) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        int numberOfRows = 200;

        String url = "http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst?" +
                "serviceKey=G8dsIOIinqIgxBio8kv6qyrYJ9OsQr%2F5PhyVheRm9NbJfGNNYQSUSKYZH%2BRlgmYs01YxjwihIRUKKyQE1j%2FO%2Fw%3D%3D" +
                "&numOfRows="+numberOfRows +
                "&pageNo=1" +
                "&CURRENT_DATE="+strToday +
                "&HOUR=24" +
                "&COURSE_ID="+courseId +
                "&dataType=JSON";

        URI uri = new URI(url);

        Mono<Root> tourStnVilageFcstDTOMono = webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Root.class);

        Root rootNew = tourStnVilageFcstDTOMono.doOnNext(root -> new Root()
        ).block();


        return rootNew.getResponse().getBody().getItems().getItem();
    }
}
