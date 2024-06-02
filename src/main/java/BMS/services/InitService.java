package BMS.services;

import BMS.models.*;
import BMS.models.constant.AuditoriumFeature;
import BMS.models.constant.SeatStatus;
import BMS.models.constant.SeatType;
import BMS.models.constant.ShowSeatStatus;
import BMS.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    AuditoriumRepository auditoriumRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserRepository userRepository;
    public boolean initialiseData() {
        City pune = new City("Pune");
        City agra = new City("Agra");
        City shreenagar = new City("ShreeNagar");

        cityRepository.save(pune);
        cityRepository.save(agra);
        cityRepository.save(shreenagar);

        City savedPune = cityRepository.findCityByName("Pune");
        Theatre iMax = new Theatre("IMax", "Airport Road, Pune");
        Theatre pvr = new Theatre("PVR", "PM Road, Pune");

        theatreRepository.save(iMax);
        theatreRepository.save(pvr);

        Theatre savedImax = theatreRepository.findTheatreByName("IMax");
        Theatre savedPvr = theatreRepository.findTheatreByName("PVR");

        List<Theatre> puneCityTheatre = new ArrayList<>();
        puneCityTheatre.add(savedImax);
        puneCityTheatre.add(savedPvr);

        savedPune.setTheatre(puneCityTheatre);
        cityRepository.save(savedPune);

        for(int i=1; i<=10; i++){
            Seat seat = new Seat(i,i, i+" "+i,SeatStatus.AVAILABLE, SeatType.GOLD);
            seatRepository.save(seat);
        }
        List<Seat> savedSeat = seatRepository.findAll();

        Auditorium auditorium = new Auditorium();
        auditorium.setName("Audi01");
        auditorium.setCapacity(5);
        auditorium.setSeats(savedSeat);
        auditorium.setAuditoriumFeature(List.of(AuditoriumFeature.DOLBY, AuditoriumFeature.IMAX));
        auditoriumRepository.save(auditorium);

        Movie movie = new Movie("MI" , "HollyWood Movei");
        movieRepository.save(movie);

        Show show = new Show();
        show.setAuditorium(auditoriumRepository.findAuditoriumByName("Audi01"));
        show.setMovie(movieRepository.findMovieByName("MI"));
        show.setStartTime(LocalDateTime.now());
        show.setEndTime(LocalDateTime.now().plusMinutes(180));
        showRepository.save(show);

        for(int i=1; i<=10; i++){
            ShowSeat seat = new ShowSeat(251,showRepository.findById(1).get(),seatRepository.findSeatBySeatNumber(i+" "+i), ShowSeatStatus.AVAILABLE, SeatType.GOLD);
            showSeatRepository.save(seat);
        }

        return true;

    }
}
