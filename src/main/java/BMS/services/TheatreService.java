package BMS.services;


import BMS.models.City;
import BMS.models.Theatre;
import BMS.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private CityService cityService;


    public Theatre saveTheatre(String name, String address, int cityId) {
        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setAddress(address);
        Theatre saveTheatre = theatreRepository.save(theatre);

        City city = cityService.getCityById(cityId);
        List<Theatre> theatreList = city.getTheatre();
        theatreList.add(saveTheatre);
        city.setTheatre(theatreList);
        cityService.saveCity(city);

        return saveTheatre;

    }
}
