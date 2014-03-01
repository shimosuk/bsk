package bskapp.model;

import bskapp.properties.TeamProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Team extends TeamProperty {
    private static Logger logger = LoggerFactory.getLogger(Team.class);
    public final static Team RED = new Team(Color.RED);
    public final static Team WHITE = new Team(Color.WHITE);

    private String name;
    private final Color color;
    private List<Player> member;
    private Integer total;
    private Integer foul;

    protected Team(Color color) {
        this.color = color;
        this.total = 0;
        this.foul = 0;
        this.member = new ArrayList<Player>();
        setDefaultName(color);
    }

    public void clear() {
        this.member.clear();
        this.total = 0;
        this.foul = 0;
        setDefaultName(color);
        logger.info("初期化");
    }

    public void entry(Player player) {
        this.member.add(player);
    }

    public boolean isMember(Player player) {
        return this.member.contains(player);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Player> getMenber() {
        return member;
    }

    public void shotCount(Integer point) {
        total += point;
        setTotalProperty();
    }

    public void foul() {
        ++foul;
        setFoulProperty();
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public Integer getFoul() {
        return foul;
    }

    public static enum Color {
        RED("RED"), WHITE("WHITE");

        private String color;
        Color(String color) {
            this.color = color;
        }
        @Override
        public String toString() {
            return color;
        }

    };

    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this)
                .append("name", name)
                .append("total", total)
                .append("foul", foul)
                .toString();
    }

    private void setDefaultName(Color color) {
        switch (color) {
            case RED:
                this.name = "Team A";
                break;
            case WHITE:
                this.name = "Team B";
                break;
            default:
                throw new RuntimeException(String.format("%sは想定外の入力です。", color));
        }
    }

}
