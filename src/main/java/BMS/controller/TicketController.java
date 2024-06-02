package BMS.controller;

import BMS.dto.*;
import BMS.exception.ShowSeatNotAvailableException;
import BMS.models.ShowSeat;
import BMS.models.Ticket;
import BMS.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<BookTicketResponseDTO> bookTicket(@RequestBody BookTicketRequestDTO bookTicketRequestDTO)  {
        List<Integer> showSeatId = bookTicketRequestDTO.getShowSeatId();
        int showId = bookTicketRequestDTO.getShowId();
        int userId = bookTicketRequestDTO.getUserId();
        try{
            Ticket ticket = ticketService.bookTicket(showSeatId, showId, userId);
            BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
            responseDTO.setStatus("SUCCESS");
            //responseDTO.setTicket(ticket);
            return ResponseEntity.ok(responseDTO);
        }
        catch (ShowSeatNotAvailableException exception){
            BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
            responseDTO.setStatus("FAIL");
            return  ResponseEntity.ok(responseDTO);
        }
    }
    @GetMapping("/genticket/{Id}")
    public ResponseEntity generateTicket(@PathVariable("Id") int userId){
        //List<Integer> ticketIds = ticketRequestDTO.getTicketId();
        //int userId = ticketRequestDTO.getUserId();

        List<Ticket> tickets = ticketService.generateTicket(userId);
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        List<TicketForUser> userTicket = ticketResponseDTO.getTicketForUsers();
        if(userTicket == null){
            userTicket = new ArrayList<>();
        }
        for(Ticket ticket: tickets){
            TicketForUser ticketForUser = new TicketForUser();
            ticketForUser.setTicketId(ticket.getId());
            ticketForUser.setShowId(ticket.getShows().getId());
            ticketForUser.setMovieName(ticket.getShows().getMovie().getName());
            ticketForUser.setBookingTime(LocalDateTime.now());
            ticketForUser.setStartTime(ticket.getShows().getStartTime());
            ticketForUser.setEndTime(ticket.getShows().getEndTime());
            ticketForUser.setTotalAmount(ticket.getTotalAmount());

            List<String> seatNumber = new ArrayList<>();
            List<ShowSeat> showSeats = ticket.getShowSeats();
            for(ShowSeat showSeat : showSeats){
                String seatNo = showSeat.getSeat().getSeatNumber();
                seatNumber.add(seatNo);
            }
            ticketForUser.setSeatNumbers(seatNumber);

            userTicket.add(ticketForUser);
        }

        ticketResponseDTO.setUserId(userId);
        ticketResponseDTO.setTicketForUsers(userTicket);
        //List<TicketForUser> ticketResponseDTO.getTicketForUsers();

        return ResponseEntity.ok(ticketResponseDTO);
    }

}
