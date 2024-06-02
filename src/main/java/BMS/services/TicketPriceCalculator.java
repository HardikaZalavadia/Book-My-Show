package BMS.services;

import BMS.models.Show;
import BMS.models.ShowSeat;
import BMS.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriceCalculator {
    @Autowired
    private  ShowSeatRepository showSeatRepository;

    public TicketPriceCalculator(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public double calculateTicketPrice(List<ShowSeat> showSeats) {
        Show show = showSeats.get(0).getShow();
        List<ShowSeat> showSeats1 = showSeatRepository.findAllByShowEquals(show);

        double amount = 0;

        for(ShowSeat showSeat : showSeats){
            for(ShowSeat showSeat1 : showSeats1){
                if(showSeat.getSeat().getSeatType().equals(showSeat1.getSeatType())){
                    amount += showSeat1.getPrice();
                }
            }
        }

        return amount;

    }
}
