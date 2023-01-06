package io.namoosori.travelclub.web.store.jpastore.jpo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import io.namoosori.travelclub.web.aggregate.club.TravelClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="TRAVEL_CLUB")
public class TravelClubJpo {

	@Id
	private String id;
	private String name;
	private String intro;
	private long foundationTime;
	
	public TravelClubJpo(TravelClub travelClub) {		
		BeanUtils.copyProperties(travelClub, this);
	}
	
	public TravelClub toDomain() {
//		TravelClub travelClub = new TravelClub(this.name, this.intro);
//		travelClub.setId(this.id);
//		travelClub.setFoundationTime(this.foundationTime);
//		return travelClub;
		TravelClub travelClub = new TravelClub();
		BeanUtils.copyProperties(this, travelClub);
		return travelClub;
	}
}
