package bskapp.model;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class PlayerTest {
    public static class シュートのカウントについて {
        @Test public void
        異なる人物が得点した場合() {
            // arrange
            Player rukawa = new Player(11, "キツネ");
            Player mitsui = new Player(14, "ミッチー");
            // act
            rukawa.shotCount(1);
            mitsui.shotCount(3);
            // assert
            assertThat("個人の合計得点がカウントされていること", rukawa.getTotal(), is(1));
            assertThat("個人の合計得点がカウントされていること", mitsui.getTotal(), is(3));
            assertThat("ファールカウントが増えないこと", rukawa.getFoul(), is(0));
            assertThat("ファールカウントが増えないこと", mitsui.getFoul(), is(0));
        }
        @Test public void
        単独で連続得点した場合() {
            // arrange
            Player rukawa = new Player(10, "キツネ");
            // act
            rukawa.shotCount(2);
            rukawa.shotCount(3);
            // assert
            assertThat("個人の合計得点がカウントされていること", rukawa.getTotal(), is(5));
            assertThat("ファールカウントが増えないこと", rukawa.getFoul(), is(0));
        }
    }
    public static class ファールについて {
        @Test public void
        ファールした場合() {
            // arrange
            Player player = new Player(10, "天才");
            // act
            player.foul();
            // assert
            assertThat("1個づつファールがカウントされること", player.getFoul(), is(1));
            assertThat("シュートカウントが増えないこと", player.getTotal(), is(0));
        }
        @Test public void
        連続でファールした場合() {
            // arrange
            Player player = new Player(10, "天才");
            // act
            player.foul();
            player.foul();
            // assert
            assertThat("1個づつファールがカウントされること", player.getFoul(), is(2));
            assertThat("シュートカウントが増えないこと", player.getTotal(), is(0));
        }
    }
}
