package bowling.domain.result.status;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

/**
 * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
 *
 * @apiNote FinalFrame에서는 한번 더 시도할 수 있음.
 */
public class Strike extends PinResultState implements BonusAbleState {

    private static final String MARKER = "X";

    public Strike(Pin pin) {
        super(pin);

        if (!pin.isStrike()) {
            throw new IllegalArgumentException("Strike가 아니에요.");
        }
    }

    @Override
    public String getPrintMark() {
        return MARKER;
    }

    @Override
    public boolean isInstanceOf(Class<? extends ResultState> clazz) {
        return clazz.isInstance(this);
    }
}
