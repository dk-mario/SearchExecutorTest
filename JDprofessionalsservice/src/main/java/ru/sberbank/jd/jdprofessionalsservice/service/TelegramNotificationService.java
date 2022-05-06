package ru.sberbank.jd.jdprofessionalsservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

@Service
public class TelegramNotificationService {

    @Value("${application.notify.telegram.channel_id}")
    private String channelId;

    @Value("${application.notify.telegram.bot_id}")
    private String botId;


    // шаблон запроса
    private final String urlTemplate = "https://api.telegram.org/bot%TELEGRAM_BOT_TOKEN%/sendMessage";

    public void sendMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        String request = urlTemplate
                .replaceAll("%TELEGRAM_BOT_TOKEN%", botId);

        UriComponentsBuilder telegramRequestBuilder = UriComponentsBuilder.fromHttpUrl(request)
                .queryParam("chat_id", channelId)
                .queryParam("text", message);

        ResponseEntity<String> response = null;
        restTemplate.exchange(telegramRequestBuilder.buildAndExpand("").toUri() , HttpMethod.POST,
                response, String.class);
    }

}
