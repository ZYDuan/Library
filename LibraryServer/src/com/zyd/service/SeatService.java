package com.zyd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.zyd.dao.SeatDao;
import com.zyd.entity.Floor;
import com.zyd.entity.Seat;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

@Transactional
public class SeatService {
	private SeatDao seatDao;

	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}

	public Seat findOneBest() {
		Floor bestFloor = seatDao.findFloor();
		Seat bestSeat = seatDao.findSeat(bestFloor.getFid());
		return bestSeat;
	}

	@SuppressWarnings("null")
	public ArrayList<HashMap<String, String>> findTwoBest() {
		Floor bestFloor = seatDao.findFloor();
		ArrayList<Seat> seats = seatDao.findTwoSeat(bestFloor.getFid());
		ArrayList<HashMap<String, String>> twoSeat = new ArrayList<>(); 
		Seat []s = new Seat[2];
		
		for(Seat seat : seats) {
			int column = seat.getColumn();
			int row = seat.getRow();
			if(row % 3 == 0) {
				if(column % 3 == 0) {
					if(seatDao.findEmptyOne(seat.getSid()+1).size() != 0) {
						Seat s1 = seatDao.findEmptyOne(seat.getSid()+1).get(0);
					 
						s[0] = s1;
						s[1] = seat;
						break;
					}else {
						if(seatDao.findEmptyOne(seat.getSid()+20).size() != 0) {
						Seat s2  = seatDao.findEmptyOne(seat.getSid()+20).get(0);
						
							s[0] = s2;
							s[1] = seat;
							break;
						}else {
							if(seatDao.findEmptyOne(seat.getSid()+21).size() != 0) {
								Seat s3  = seatDao.findEmptyOne(seat.getSid()+21).get(0);
							
								s[0] = s3;
								s[1] = seat;
								break;
							}
						}
					}
				}else if((column - 1) % 3 == 0) {
					if(seatDao.findEmptyOne(seat.getSid()-1).size() != 0) {
					Seat s1 = seatDao.findEmptyOne(seat.getSid()-1).get(0);
					
						s[0] = s1;
						s[1] = seat;
						break;
					}else {
						if(seatDao.findEmptyOne(seat.getSid()+20).size() != 0) {
						Seat s2  = seatDao.findEmptyOne(seat.getSid()+20).get(0);
						
							s[0] = s2;
							s[1] = seat;
							break;
						}else {
							if(seatDao.findEmptyOne(seat.getSid()+19).size() != 0) {
							Seat s3  = seatDao.findEmptyOne(seat.getSid()+19).get(0);
							
								s[0] = s3;
								s[1] = seat;
								break;
							}
						}
					}
					
				}
			}else if((row - 1) % 3 ==0) {
				if(column % 3 == 0) {
					if(seatDao.findEmptyOne(seat.getSid()+1).size() != 0) {
					Seat s1 = seatDao.findEmptyOne(seat.getSid()+1).get(0);
					
						s[0] = s1;
						s[1] = seat;
						break;
					}else {
						if(seatDao.findEmptyOne(seat.getSid()-20).size() != 0) {
						Seat s2  = seatDao.findEmptyOne(seat.getSid()-20).get(0);
						
							s[0] = s2;
							s[1] = seat;
							break;
						}else {
							if(seatDao.findEmptyOne(seat.getSid()-19).size() != 0) {
							Seat s3  = seatDao.findEmptyOne(seat.getSid()-19).get(0);
							
								s[0] = s3;
								s[1] = seat;
								break;
							}
						}
					}
				}else if((column - 1) % 3 == 0) {
					if(seatDao.findEmptyOne(seat.getSid()-1).size() != 0) {
					Seat s1 = seatDao.findEmptyOne(seat.getSid()-1).get(0);
					
						s[0] = s1;
						s[1] = seat;
						break;
					}else {
						if(seatDao.findEmptyOne(seat.getSid()-20).size() != 0) {
						Seat s2  = seatDao.findEmptyOne(seat.getSid()-20).get(0);
						
							s[0] = s2;
							s[1] = seat;;
							break;
						}else {
							if(seatDao.findEmptyOne(seat.getSid()-21).size() != 0) {
							Seat s3  = seatDao.findEmptyOne(seat.getSid()-21).get(0);
							
								s[0] = s3;
								s[1] = seat;
								break;
							}
						}
					}
					
				}
			}
		}
		
		for(int i = 0; i<2; i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("row", String.valueOf(s[i].getRow()));
			map.put("column", String.valueOf(s[i].getColumn()));
			map.put("floor", String.valueOf(s[i].getFloor().getFid()));
			twoSeat.add(map);
		}
		return twoSeat; 
	}

	

	public void add() {
		seatDao.add();
		
	}

	public void update(Seat seat) {
		seatDao.update(seat);
		
	}

	public Seat findOne(Integer sid) {
		// TODO Auto-generated method stub
		return seatDao.findOne(sid);
	}

	

	
}
