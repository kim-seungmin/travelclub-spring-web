package io.namoosori.travelclub.web.store.jpastore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import io.namoosori.travelclub.web.aggregate.club.TravelClub;
import io.namoosori.travelclub.web.store.ClubStore;
import io.namoosori.travelclub.web.store.jpastore.jpo.TravelClubJpo;
import io.namoosori.travelclub.web.store.jpastore.repository.ClubRepository;
import io.namoosori.travelclub.web.util.exception.NoSuchClubException;

@Repository
public class ClubJpaStore implements ClubStore{

	private ClubRepository clubRepository;
	
	public ClubJpaStore(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}
	
	@Override
	public String create(TravelClub club) {
		clubRepository.save(new TravelClubJpo(club));
		return club.getId();
	}

	@Override
	public TravelClub retrieve(String clubId) {
		// TODO Auto-generated method stub
		Optional<TravelClubJpo> clubJpo = clubRepository.findById(clubId);
		if(!clubJpo.isPresent()) {
			throw new NoSuchClubException(String.format("TravelClub(%s) is not found", clubId));
		}
		return clubJpo.get().toDomain();
	}

	@Override
	public List<TravelClub> retrieveByName(String name) {
		List<TravelClubJpo> clubJpos = clubRepository.findAllByName(name);
		return clubJpos.stream().map(TravelClubJpo::toDomain).collect(Collectors.toList());
	}

	@Override
	public List<TravelClub> retrieveAll() {
		// TODO Auto-generated method stub
		List<TravelClubJpo> clubJpos = clubRepository.findAll();
		
		//return clubJpos.stream().map(clubJpo -> clubJpo.toDomain()).collect(Collectors.toList());
		return clubJpos.stream().map(TravelClubJpo::toDomain).collect(Collectors.toList());
	}

	@Override
	public void update(TravelClub club) {
		clubRepository.save(new TravelClubJpo(club));		
	}

	@Override
	public void delete(String clubId) {
		// TODO Auto-generated method stub
		clubRepository.deleteById(clubId);
	}

	@Override
	public boolean exists(String clubId) {
		// TODO Auto-generated method stub
		return clubRepository.existsById(clubId);
	}
	
	

}
