package com.zyd.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.zyd.entity.Floor;
import com.zyd.entity.Seat;
import com.zyd.utils.DBUtils;

public class SeatDaoImpl extends HibernateDaoSupport implements SeatDao {

	Random random = new Random();

	@Override
	public Floor findFloor() {
		String sql="from Floor fl where fl.priority = (select max(priority) from Floor f where f.isFull = 0)";
		List<Floor> priorityFloor = (List<Floor>) this.getHibernateTemplate().find(sql);
		int size = priorityFloor.size();
		int index = (random.nextInt(size)%size+1);
		return priorityFloor.get(index-1);
		
		
	}


	
	public Seat findSeat(Integer fid) {
		String sql = "from Seat s where s.priority = (select max(priority) from Seat x where x.isFull = 0 and FSID = "+fid+")";
		List<Seat>prioritySeat = (List<Seat>) this.getHibernateTemplate().find(sql);
		int size = prioritySeat.size();
		int index = (random.nextInt(size)%size+1);
		return prioritySeat.get(index-1);
	}
	
	
	public void add() {
		for(int i=1;i<4;i++) {
			for(int m = 0;m<14;m++) {
				for(int n = 0;n<29;n++) {
					
					if((n+1)%3 != 0 && (m+1)%3 != 0 ) {
						Floor floor =(Floor) this.getHibernateTemplate().get(Floor.class, i);
						Seat seat = new Seat();
						seat.setFloor(floor);
						seat.setIsFull(0);
						seat.setPriority(0);
						seat.setColumn(n);
						seat.setRow(m);
						this.getHibernateTemplate().save(seat);
					}
				}
			}
		}
	}

	@Override
	public void update(Seat seat) {
		this.getHibernateTemplate().update(seat);
	}

	@Override
	public Seat findOne(Integer sid) {
		return this.getHibernateTemplate().get(Seat.class, sid);
	}

	public ArrayList<Seat> findTwoSeat(Integer fid) {
		String sql = "select * from (select * from SEAT  order by PRIORITY desc) c where c.ISFULL = 0 AND c.FSID = 1";
		
		ArrayList<Seat> prioritySeat = new ArrayList<>();
		
		try {
			ResultSet resultSet = DBUtils.query(sql);
			while(resultSet.next()) {
				Seat seat = new Seat();
				seat.setColumn(resultSet.getInt("COL"));
				seat.setRow(resultSet.getInt("ROWS"));
				Floor floor = new Floor();
				floor.setFid((resultSet.getInt("FSID")));
				seat.setFloor(floor);
				prioritySeat.add(seat);
				seat.setSid(resultSet.getInt("SID"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			
		
		return prioritySeat;
	}

	@Override
	public List<Seat> findEmptyOne(int i) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Seat.class);
		criteria.add(Restrictions.eq("Sid", i));
		criteria.add(Restrictions.eq("isFull",  0));
		return (List<Seat>)this.getHibernateTemplate().findByCriteria(criteria);
		
	}

	 
}
