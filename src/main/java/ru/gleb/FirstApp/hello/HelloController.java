package ru.gleb.FirstApp.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    private List<String> list;
    private Map<Integer, String> map;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/update-array")
    public void updateArrayList(@RequestParam(value = "value") String value) {
        if (list != null) {
            list.add(value);
        } else {
            list = new ArrayList<>();
        }
    }

    @GetMapping("/show-array")
    public String showArrayList() {
        if (list == null) {
            return "undefined";
        }

        return list.toString();
    }

    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam(value = "value") String value) {
        if (map != null) {
            map.put(map.size(), value);
        } else {
            map = new HashMap<>();
        }
    }

    @GetMapping("/show-map")
    public String showHashMap() {
        if (map == null) {
            return "undefined";
        }

        return map.toString();
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        var listSize = (list != null) ? String.valueOf(list.size()) : "undefined";
        var mapSize = (map != null) ? String.valueOf(map.size()) : "undefined";

        return String.format("List: %s;\nMap: %s.", listSize, mapSize);
    }
}
