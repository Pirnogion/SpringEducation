package ru.gleb.FirstApp.service;

import org.springframework.stereotype.Service;
import ru.gleb.FirstApp.model.Request;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
