package com.footballmanager.repository;

import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("from Team t join t.players tp where tp = ?1")
    Team getTeamByPlayer(Player player);
}
