package com.zyd.algorithm;

import java.util.ArrayList;
import java.util.HashMap;

import com.zyd.entity.Seat;
import com.zyd.service.SeatService;

public class TwoAlgorithm {
private  SeatService seatService;

	public void setSeatService(SeatService seatService) {
		this.seatService = seatService;
	}

	public ArrayList<HashMap<String, String>> getBestSeat() {
		ArrayList<HashMap<String, String>> seat = seatService.findTwoBest();
		return seat;
	}
}
