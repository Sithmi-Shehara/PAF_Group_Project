package com.MyMongoSpring.MyMongoSpring.Service;

import com.MyMongoSpring.MyMongoSpring.Model.Badge;
import com.MyMongoSpring.MyMongoSpring.Repositary.BadgeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepo badgeRepo;

    public Badge addBadge(Badge badge) {
        return badgeRepo.save(badge);
    }

    public List<Badge> getAllBadges() {
        return badgeRepo.findAll();
    }

    public Optional<Badge> getBadgeById(String id) {
        return badgeRepo.findById(id);
    }

    public boolean claimBadge(String id) {
        Optional<Badge> badgeOpt = badgeRepo.findById(id);
        if (badgeOpt.isPresent()) {
            Badge badge = badgeOpt.get();
            if (!badge.isClaimed()) {
                badge.setClaimed(true);
                badgeRepo.save(badge);
                return true;
            }
        }
        return false;
    }
}
