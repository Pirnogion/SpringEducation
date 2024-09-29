package ru.gleb.FirstApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gleb.FirstApp.model.Request;
import ru.gleb.FirstApp.model.Systems;

@Slf4j
@Service
@Qualifier("ModifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSource("undefined");

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        try {
            new RestTemplate().exchange(
                    "http://localhost:8084/feedback",
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<>() {}
            );
        } catch (Exception exception) {
            log.error("Не удалось отправить запрос к сервису 'Сервис 2'");
            log.error(exception.getMessage());
        }
    }
}
