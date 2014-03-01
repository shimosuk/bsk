package bskapp.model;

import org.junit.After;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(Enclosed.class)
public class TeamTest {
    public static class チーム登録について {
        @After
        public void tearDown() {
            Team.RED.clear();
            Team.WHITE.clear();
        }

        @Test public void
        登録前のチーム名を取得する() {
            // arrange
            // assert
            assertThat(Team.RED.getName(), is("Team A"));
            assertThat(Team.WHITE.getName(), is("Team B"));
        }
        @Test public void
        チーム名を登録する() {
            // arrange, act
            Team.RED.setName("Team RED");
            Team.WHITE.setName("Team WHITE");
            // assert
            assertThat(Team.RED.getName(), is("Team RED"));
            assertThat(Team.WHITE.getName(), is("Team WHITE"));
        }
    }
    public static class 合計点について {
        @After
        public void tearDown() {
            Team.RED.clear();
            Team.WHITE.clear();
        }
        @Test public void
        得点の初期値について() {
            // arrange, act, assert
            assertThat(Team.RED.getTotal(), is(0));
            assertThat(Team.WHITE.getTotal(), is(0));
        }
        @Test public void
        得点のカウントについて() {
            // arrange, act
            Team.RED.shotCount(1);
            Team.RED.shotCount(3);
            Team.WHITE.shotCount(3);
            // assert
            assertThat(Team.RED.getTotal(), is(4));
            assertThat(Team.WHITE.getTotal(), is(3));
        }
    }
    public static class ファール合計について {
        @After
        public void tearDown() {
            Team.RED.clear();
            Team.WHITE.clear();
        }
        @Test public void
        得点の初期値について() {
            // arrange, act, assert
            assertThat(Team.RED.getFoul(), is(0));
            assertThat(Team.WHITE.getFoul(), is(0));
        }
        @Test public void
        得点のカウントについて() {
            // arrange, act
            Team.RED.foul();
            Team.RED.foul();
            Team.WHITE.foul();
            // assert
            assertThat(Team.RED.getFoul(), is(2));
            assertThat(Team.WHITE.getFoul(), is(1));
        }
    }
}
