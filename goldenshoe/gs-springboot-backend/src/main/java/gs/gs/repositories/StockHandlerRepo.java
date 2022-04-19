package gs.gs.repositories;

import gs.gs.model.StockHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockHandlerRepo extends JpaRepository<StockHandler, Long> {


}
