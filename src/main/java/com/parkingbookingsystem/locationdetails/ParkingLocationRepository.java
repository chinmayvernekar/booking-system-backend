package com.parkingbookingsystem.locationdetails;

import com.parkingbookingsystem.booking.SlotBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLocationRepository extends JpaRepository<ParkingLocations, Long> {

    @Query("select count(area) from ParkingLocations")
    public Integer checkDataExist();

    @Query("select pl from ParkingLocations pl where pl.area = ?1")
    ParkingLocations findAreaByIdForBooking(String area);

    @Query("select pl.id from ParkingLocations pl where pl.id = ?1")
    public Integer decrementSlotAvalibality(Integer location);

    @Query("select pl.totalSlot from ParkingLocations pl where pl.id = ?1")
    public Integer getTotalSlot(Integer location);

    @Query("select pl.slotAvaliable from ParkingLocations pl where pl.id = ?1")
    public Integer getSlotAvaliable(Integer location);



    @Query("SELECT CASE WHEN sb.slotAvaliable = 0 THEN true ELSE false END FROM ParkingLocations sb WHERE sb.id = ?1")
    public boolean checkIfSlotAvaliableisNull(Integer location);

    @Modifying
    @Query("update ParkingLocations set slotAvaliable = ?1 where id = ?2")
    public Integer updateTotalSlot(Integer slotsAvaliable,Integer location);

    @Query("select pl from ParkingLocations pl")
    Page<ParkingLocations> getAllByPageSize(Pageable page);
}
