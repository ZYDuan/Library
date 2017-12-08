package com.zyd.algorithm;

import com.zyd.entity.Seat;
import com.zyd.service.SeatService;

public class OneAlgorithm {
	private  SeatService seatService;
	
	public SeatService getSeatService() {
		return seatService;
	}

	public void setSeatService(SeatService seatService) {
		this.seatService = seatService;
	}

	public  Seat getBestSeat() {
		Seat seat = seatService.findOneBest();
		return seat;
	}
}
