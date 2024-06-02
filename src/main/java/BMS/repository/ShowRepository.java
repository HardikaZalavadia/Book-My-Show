package BMS.repository;

import BMS.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    Show findByIdEquals(Integer Id);  // select * from shows where id equals = {}
    Show findShowById(int showId);
}
