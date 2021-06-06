package com.bulatsa.demo.web.controllers;

import com.bulatsa.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExportController extends BaseController {
    private final FlightService flightService;

    @Autowired
    public ExportController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/export1")
    public ModelAndView exportLandedAirplanes() {
        String exportResult = this.flightService.getAllFlightsAlreadyLanded();
        return super.view("/export/1", "export1", exportResult);
    }

    @GetMapping("/export2")
    public ModelAndView exportFlyingAirplanes() {
        String exportResult = this.flightService.getAllFlightsStillFlying();
        return super.view("/export/2", "export2", exportResult);
    }

    @GetMapping("/export3")
    public ModelAndView exportFutureFlights() {
        String exportResult = this.flightService.getAllFlightsStillNotTakenOff();
        return super.view("/export/3", "export3", exportResult);
    }
}
