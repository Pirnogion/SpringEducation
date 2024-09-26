package ru.gleb.FirstApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.gleb.FirstApp.model.Response;
import ru.gleb.FirstApp.util.DateTimeUtils;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtils.getCustomFormat().format(new Date()));

        log.info("modified response: {}", response);

        return response;
    }
}
