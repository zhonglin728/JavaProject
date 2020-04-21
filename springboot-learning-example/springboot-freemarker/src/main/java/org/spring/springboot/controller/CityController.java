package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city/{id}" , method = RequestMethod.GET)
    public String findOneCity(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city" , cityService.findCityById(id));
        return "city";
    }

    @RequestMapping(value = "/api/city" , method = RequestMethod.GET)
    public String findAllCity(Model model) {
        //List<City> cityList = cityService.findAllCity();
        List<City> list = new ArrayList<>();
        City city = new City();
        city.setId(12L);
        city.setCityName("湖北");
        city.setDescription("这个地方很好玩！");
        list.add(city);
        model.addAttribute("cityList" , list);
        return "cityList";
    }
}
