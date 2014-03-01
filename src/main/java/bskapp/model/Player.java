package bskapp.model;

import bskapp.properties.PlayerProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Player extends PlayerProperty {
    private static Logger logger = LoggerFactory.getLogger(Player.class);

    private final String name;
    private final Integer number;
    private Integer total;
    private Integer foul;

    public Player(Integer number, String name) {
        this.number = number;
        this.name = name;
        this.total = 0;
        this.foul = 0;
        logger.debug("CREATE " + this.toString());
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }


    public void shotCount(Integer point) {
        total += point;
        bindTotalProperty();
    }

    public void foul() {
        ++foul;
        bindFoulProperty();
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public Integer getFoul() {
        return foul;
    }

    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this)
                .append("no", number)
                .append("score", getTotal())
                .append("foul", getFoul())
                .append("name", name)
                .toString();
    }
}
