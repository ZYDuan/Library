package com.zyd.dao;


import java.util.ArrayList;
import java.util.List;

import com.zyd.entity.Floor;
import com.zyd.entity.Seat;

public interface SeatDao {

	Floor findFloor();

	Seat findSeat(Integer aid);

	void add();

	void update(Seat seat);

	Seat findOne(Integer sid);

	ArrayList<Seat> findTwoSeat(Integer fid);

	List<Seat> findEmptyOne(int i);


}
