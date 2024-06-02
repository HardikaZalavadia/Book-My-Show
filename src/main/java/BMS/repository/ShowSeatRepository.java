package BMS.repository;

import BMS.models.Show;
import BMS.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    ShowSeat save(ShowSeat showSeat);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ShowSeat> findAllByIdIn(List<Integer> showSeatIds);

    List<ShowSeat> findAllByShowEquals(Show show);
    ShowSeat findShowSeatById(int showSeatId);

}
