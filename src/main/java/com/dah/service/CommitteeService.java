package com.dah.service;

import com.dah.domain.Area;
import com.dah.domain.ReviewerProfile;
import com.dah.domain.User;
import com.dah.repository.AreaRepository;
import com.dah.repository.ReviewerRepository;
import com.dah.repository.UserRepository;
import java.util.List;

public class CommitteeService {

    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final ReviewerRepository reviewerRepository;

    public CommitteeService(UserRepository userRepository, AreaRepository areaRepository,
            ReviewerRepository reviewerRepository) {
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.reviewerRepository = reviewerRepository;
    }

    public Area registerArea(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da área não pode ser nulo ou vazio");
        }

        if (areaRepository.findAll().stream().anyMatch(a -> a.getName().equalsIgnoreCase(name))) {
            throw new IllegalArgumentException("Área com o mesmo nome já existe");
        }

        Area area = new Area(null, name);

        return areaRepository.save(area);
    }

    public List<Area> listAreas() {
        return areaRepository.findAll();
    }

    public List<Area> findAreasByIds(List<Integer> ids) {
        return areaRepository.findByIds(ids);
    }

    public ReviewerProfile inviteResearcher(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() 
            -> new IllegalArgumentException("Pesquisador não encontrado: " + userId));

        ReviewerProfile reviewer = new ReviewerProfile(null, user, null);
        return reviewerRepository.save(reviewer);
    }

    public ReviewerProfile defineExpertiseAreas(User user, List<Integer> areaIds) {
        ReviewerProfile reviewer = findReviewerByUser(user);
        List<Area> areas = areaRepository.findByIds(areaIds);

        reviewer.setExpertiseAreas(areas);
        return reviewerRepository.save(reviewer);
    }

    public boolean needsExpertiseAreas(User user) {
        return reviewerRepository.findByUser(user).map(reviewer 
            -> !reviewer.hasDefinedExpertiseAreas()).orElse(false);
    }

    public ReviewerProfile findReviewerByUser(User user) {
        return reviewerRepository.findByUser(user).orElseThrow(()
            -> new IllegalStateException("Usuário não é revisor: " + user.getEmail()));
    }

    public List<ReviewerProfile> listReviewers() {
        return reviewerRepository.findAll();
    }

}
