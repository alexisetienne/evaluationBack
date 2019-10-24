package co.simplon.evaluation.dao;

import co.simplon.evaluation.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
@RepositoryRestResource
public interface RoomRepository extends JpaRepository<Room, Long> {
    @RestResource(path = "/selectedRooms")
    public List<Room> findBySelectedIsTrue();
    @RestResource(path = "/RoomsByKeyword")
    public List<Room> findByNameContains(@Param("mc") String mc);
    @RestResource(path = "/dispoRooms")
    public List<Room> findByAvailableIsTrue();
}
