package bskapp.service;

import bskapp.model.Player;
import bskapp.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoreService {
    Logger logger = LoggerFactory.getLogger(ScoreService.class);

    private static Player actor;
    private static Team belong;
    private Team red = Team.RED;
    private Team white = Team.WHITE;

    public void shotCount(int point) {
        this.belong.shotCount(point);
        this.actor.shotCount(point);
        logger.info("shot in count of {}pt : {}", point, actor);
    }

    public Integer getTeamScoreOfRed() {
        return red.getTotal();
    }
    public Integer getTeamScoreOfWhite() {
        return white.getTotal();
    }

    public void foul() {
        this.actor.foul();
        this.belong.foul();
        logger.info("foul : {}", actor);
    }

    public Integer getTeamFoulOfRed() {
        return red.getFoul();
    }
    public Integer getTeamFoulOfWhite() {
        return white.getFoul();
    }

    public Integer getPersonalFoul(Player player) {
        return player.getFoul();
    }

    public Integer getPersonalScore(Player player) {
        return player.getTotal();
    }

    public void select(Player actor, Team team) {
        this.actor = actor;
        this.belong = team;
    }
}
