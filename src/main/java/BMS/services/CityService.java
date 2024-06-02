package BMS.services;

import BMS.models.City;
import BMS.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    public City saveCity(String cityName){
        City city = new City();
        city.setName(cityName);
        return cityRepository.save(city);
    }

    public City saveCity(City city){
        return cityRepository.save(city);
    }
    public City getCityByName(String cityName){
        City city = cityRepository.findCityByName(cityName);
        return city;
    }
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public boolean deleteCityById(int id){
        cityRepository.deleteById(id);
        return true;
    }
    public City getCityById(int cityId){
        return cityRepository.findById(cityId).get();
    }
}
