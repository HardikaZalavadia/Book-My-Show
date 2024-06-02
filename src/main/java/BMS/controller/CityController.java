package BMS.controller;

import BMS.dto.CityRequestDTO;
import BMS.models.City;
import BMS.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping("/city")
    public ResponseEntity createCity(@RequestBody CityRequestDTO cityRequestDTO){
        try{
            String cityName = cityRequestDTO.getName();
            if(cityName == null || cityName.isBlank() || cityName.isEmpty()){
                throw new Exception("City Name is Invalid");
            }
            City saveCity = cityService.saveCity(cityName);
            return ResponseEntity.ok(saveCity);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/city/{name}")
    public ResponseEntity getCityByName(@PathVariable("name") String cityName){
        City city = cityService.getCityByName(cityName);
        return ResponseEntity.ok(city);
    }

    @GetMapping("/city")
        public ResponseEntity getAllCities(){
            List<City> cities = cityService.getAllCities();
            return ResponseEntity.ok(cities);

    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity deleteCityById(@PathVariable("id") int id) {
        boolean isDeleted = cityService.deleteCityById(id);
        return ResponseEntity.ok(isDeleted);
    }

}
