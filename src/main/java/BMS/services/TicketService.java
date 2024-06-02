package BMS.services;

import BMS.exception.ShowSeatNotAvailableException;
import BMS.exception.UserNotFoundException;
import BMS.models.Show;
import BMS.models.ShowSeat;
import BMS.models.Ticket;
import BMS.models.User;
import BMS.models.constant.ShowSeatStatus;
import BMS.models.constant.TicketStatus;
import BMS.repository.ShowRepository;
import BMS.repository.ShowSeatRepository;
import BMS.repository.TicketRepository;
import BMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    private TicketPriceCalculator ticketPriceCalculator;

    @Autowired
    ShowSeatService showSeatService;

//    @Autowired
//    UserService userService;

    @Autowired
    public TicketService(ShowRepository showRepository, ShowSeatRepository showSeatRepository,
                         TicketPriceCalculator ticketPriceCalculator, TicketRepository ticketRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketPriceCalculator = ticketPriceCalculator;
        this.ticketRepository = ticketRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Integer> showSeatIds, int showId, int userId) throws ShowSeatNotAvailableException {
        checkAndLockSeat(showSeatIds);

        Ticket ticket = startBooking(showSeatIds,showId);
        User user = userRepository.findUserById(userId);
        user.getTickets().add(ticket);
        //user.setTickets(tickets);
        userRepository.save(user);
        return ticket;
    }
    private Ticket startBooking(List<Integer> showSeatIds, int showId) {
        Show show = showRepository.findShowById(showId);
        //List<ShowSeat> showSeats = showSeatRepository.findAllByIdIn(showSeatIds);
        List<ShowSeat> showSeats = new ArrayList<>();
        for(int showSeatId : showSeatIds){
            ShowSeat seat = showSeatRepository.findShowSeatById(showSeatId);
            showSeats.add(seat);
        }
        Ticket ticket = new Ticket();
        ticket.setTicketStatus(TicketStatus.INPROGRESS);
        ticket.setShows(show);
        ticket.setShowSeats(showSeats);
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setTotalAmount(ticketPriceCalculator.calculateTicketPrice(showSeats));
        ticketRepository.save(ticket);
        return ticket;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkAndLockSeat(List<Integer> showSeatIds) throws ShowSeatNotAvailableException {
//     check availability of seat
        //List<ShowSeat> showSeats = showSeatRepository.findAllByIdIn(showSeatIds);
        for(int showSeatId : showSeatIds){
            //ShowSeat seat = showSeatService.getShowSeat(showSeatId);
            ShowSeat seat = showSeatRepository.findShowSeatById(showSeatId);
            if(!seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotAvailableException("Seat is not available");
            }
        }
//      updating seat status
        for(int showSeatId : showSeatIds){
            //ShowSeat seat = showSeatService.getShowSeat(showSeatId);
            ShowSeat seat = showSeatRepository.findShowSeatById(showSeatId);
            seat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatRepository.save(seat);
            //showSeatService.saveShowSeat(seat);
        }

    }
    public List<Ticket> generateTicket(int userId){
        User savedUser = userRepository.findUserById(userId);
        if(savedUser==null){
            throw new UserNotFoundException("User is not Found");
        }
//        List<Ticket> tickets = new ArrayList<>();
//        for(int ticketId : ticketIds){
//            Ticket ticket = ticketRepository.findTicketById(ticketId);
//            tickets.add(ticket);
//        }
        //savedUser.setTickets(tickets);
        //userRepository.save(savedUser);
        return savedUser.getTickets();
    }
}
