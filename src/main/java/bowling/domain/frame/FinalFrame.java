package bowling.domain.frame;

import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.result.ResultState;
import bowling.domain.result.Results;
import bowling.domain.result.status.NextAbleState;
import bowling.domain.result.status.PinResultState;
import bowling.domain.result.status.Strike;
import java.util.List;

public class FinalFrame extends Frame {

    private static final int MAX_OF_GENERAL_HIT_PIN_COUNT = 10;

    public FinalFrame() {

    }

    public FinalFrame(Progress progress, List<PinResultState> results) {
        this(progress, new Results(results));
    }


    public FinalFrame(Progress progress, Results results) {
        super(progress, results);

        if (isGeneralRound()) {
            valid();
        }
    }

    private void valid() {
        if (sumHitPinsCount(result -> !result.isInstanceOf(Strike.class))
            > MAX_OF_GENERAL_HIT_PIN_COUNT) {
            throw new IllegalArgumentException("일반 게임의 HitPin 갯수는 10개를 넘길 수 없어요.");
        }
    }

    @Override
    protected Progress nextProgress(ResultState resultState) {
        if (isGeneralRound() && nextBowlAble(resultState)) {
            return ProgressFactory.progress(resultState);
        }

        return ProgressFactory.closed();
    }

    private boolean nextBowlAble(ResultState resultState) {
        return isNextAbleState(resultState) || containBonusAbleState();
    }

    @Override
    protected boolean isNextAbleState(ResultState resultState) {
        return isGeneralRoundBefore() && resultState.isInstanceOf(NextAbleState.class);
    }

    private boolean isGeneralRoundBefore() {
        return this.results.sizeLessThan(GENERAL_ROUND_NUMBER);
    }

    private boolean isGeneralRound() {
        return this.results.sizeLessOrEqualThan(GENERAL_ROUND_NUMBER);
    }

    private boolean containBonusAbleState() {
        return this.results.containBonusAbleState();
    }
}
