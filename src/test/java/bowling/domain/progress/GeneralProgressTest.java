package bowling.domain.progress;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;
import bowling.domain.result.status.Spare;
import bowling.domain.result.status.Strike;
import bowling.domain.result.status.Gutter;
import bowling.domain.result.status.HitNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralProgressTest {

    private static final Pin MISS = Pin.of(0);
    private static final Pin FIRST_SPARE = Pin.of(3);
    private static final Pin SPARE = Pin.of(7);
    private static final Pin NORMAL = Pin.of(5);
    private static final Pin STRIKE = Pin.of(10);

    private GeneralProgress generalProgress;


    @BeforeEach
    void init() {
        FirstProgress firstProgress = new FirstProgress();
        ResultState resultState = firstProgress.pitch(FIRST_SPARE);

        generalProgress = new GeneralProgress(resultState);
    }


    @Test
    @DisplayName("투구가 0건이면, Gutter이 반환된다.")
    void pitchGutterTest() {
        assertThat(generalProgress.pitch(MISS)).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("Spare 가 반환된다.")
    void pitchSpareTest() {
        assertThat(generalProgress.pitch(SPARE)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("HitNumber 이 반환된다.")
    void pitchHitNumberTest() {
        assertThat(generalProgress.pitch(NORMAL)).isInstanceOf(HitNumber.class);
    }

    @Test
    @DisplayName("첫번째 투구가 Miss이고, 두번째 투구가 Strike이면, Spare이 반환된다.")
    void pitchSpareTest2() {
        ResultState resultState = new FirstProgress().pitch(MISS);
        generalProgress = new GeneralProgress(resultState);

        assertThat(generalProgress.pitch(STRIKE)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("첫번째 투구가 Strike이고, 두번째 투구가 Strike이면, Strike이 반환된다.")
    void pitchSpareTest3() {
        ResultState resultState = new FirstProgress().pitch(STRIKE);
        generalProgress = new GeneralProgress(resultState);

        assertThat(generalProgress.pitch(STRIKE)).isInstanceOf(Strike.class);
    }


}