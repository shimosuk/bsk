package bskapp.service;

import bskapp.model.Player;
import bskapp.model.Team;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class ScoreServiceTest {
    public static class 得点のカウントについて {
        private ScoreService service;
        @Test public void
        シュート2点決めた合計得点が2点となること() {
            // arrange
            service = new ScoreService();
            Team red = Team.RED;
            Player gori = new Player(4, "ゴリ");
            red.entry(gori);
            // act
            service.select(gori, Team.RED);
            service.shotCount(2);
            // assert
            assertThat(service.getTeamScoreOfRed(), is(2));
        }

        @Test public void
        シュート2点後3点決めた合計得点が5点となること() {
            // arrange
            service = new ScoreService();
            Team red = Team.RED;
            Player gori = new Player(4, "ゴリ");
            Player mitthy = new Player(14, "ミッチー");
            red.entry(gori);
            red.entry(mitthy);
            // act
            service.select(gori, red);
            service.shotCount(2);
            service.select(mitthy, red);
            service.shotCount(3);
            // assert
            assertThat("ゴリの合計点が2点であること", service.getPersonalScore(gori), is(2));
            assertThat("ミッチーの合計点が3点であること", service.getPersonalScore(mitthy), is(3));
            assertThat("チームの合計点が2点であること", service.getTeamScoreOfRed(), is(5));
        }

        @Test public void
        異なるチームの得点が混同しないこと() {
            // arrange
            service = new ScoreService();
            Team red = Team.RED;
            Team white = Team.WHITE;
            Player gori = new Player(4, "ゴリ");
            Player mitthy = new Player(14, "ミッチー");
            red.entry(gori);
            white.entry(mitthy);
            // act
            service.select(gori, red);
            service.shotCount(2);
            service.select(mitthy, white);
            service.shotCount(3);
            // assert
            assertThat("ゴリの合計点が2点であること", service.getPersonalScore(gori), is(2));
            assertThat("ミッチーの合計点が3点であること", service.getPersonalScore(mitthy), is(3));
            assertThat("赤チームの合計点が2点であること", service.getTeamScoreOfRed(), is(2));
            assertThat("白チームの合計点が3点であること", service.getTeamScoreOfWhite(), is(3));
        }

        @Test public void
        個人の得点の合計がわかること() {
            // arrange
            service = new ScoreService();
            Team red = Team.RED;
            Team white = Team.WHITE;
            Player akagi = new Player(4, "ゴリ");
            Player mitsui = new Player(14, "ミッチー");
            red.entry(akagi);
            white.entry(mitsui);
            // act
            service.select(akagi, red);
            service.shotCount(2);
            service.select(mitsui, white);
            service.shotCount(3);
            service.select(akagi, red);
            service.shotCount(2);
            // assert
            assertThat(service.getPersonalScore(akagi), is(4));
            assertThat(service.getPersonalScore(mitsui), is(3));
        }
        @After
        public void teardown() {
            Team.RED.clear();
            Team.WHITE.clear();
        }
    }

    public static class ファールのカウントについて {
        private ScoreService service;
        @Test public void
        チームファールをカウントできること() {
            // arrange
            service = new ScoreService();
            Team red = Team.RED;
            Team white = Team.WHITE;
            Player gori = new Player(4, "ゴリ");
            Player sakuragi = new Player(10, "天才");
            Player mitthy = new Player(14, "ミッチー");
            red.entry(sakuragi);
            white.entry(mitthy);
            white.entry(gori);
            // act
            service.select(sakuragi, red);
            service.foul();
            service.foul();
            service.select(mitthy, white);
            service.foul();
            service.select(gori, white);
            service.foul();
            // arrange
            assertThat(service.getTeamFoulOfRed(), is(2));
            assertThat(service.getTeamFoulOfWhite(), is(2));
        }
        @Test public void
        個人ファールをカウントできること() {
            // arrange
            service = new ScoreService();
            Team red = Team.RED;
            Team white = Team.WHITE;
            Player gori = new Player(4, "ゴリ");
            Player sakuragi = new Player(10, "天才");
            Player mitthy = new Player(14, "ミッチー");
            red.entry(sakuragi);
            white.entry(mitthy);
            white.entry(gori);
            // act
            service.select(sakuragi, red);
            service.foul();
            service.foul();
            service.select(mitthy, white);
            service.foul();
            service.select(gori, white);
            service.foul();
            // arrange
            assertThat(service.getPersonalFoul(sakuragi), is(2));
            assertThat(service.getPersonalFoul(mitthy), is(1));
            assertThat(service.getPersonalFoul(gori), is(1));
        }
        @After
        public void teardown() {
            Team.RED.clear();
            Team.WHITE.clear();
        }
    }
}
