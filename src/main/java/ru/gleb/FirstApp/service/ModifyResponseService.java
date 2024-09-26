package ru.gleb.FirstApp.service;

import org.springframework.stereotype.Service;
import ru.gleb.FirstApp.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
