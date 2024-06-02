package BMS.services;

import BMS.models.ShowSeat;
import BMS.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatService {
    @Autowired
    ShowSeatRepository showSeatRepository;

    public ShowSeat getShowSeat(int showSeatId){
        ShowSeat showSeat = showSeatRepository.findById(showSeatId).get();
        return showSeat;

    }
    public ShowSeat saveShowSeat(ShowSeat seat){
      return showSeatRepository.save(seat); // upsert -> insert and update
    }
}
