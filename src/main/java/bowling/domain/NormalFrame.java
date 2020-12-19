package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.Scoring.NONE;

public class NormalFrame implements Frame {
    private final List<BallThrow> ballThrows = new ArrayList<>();
    private final int number;

    public NormalFrame() {
        this(1);
    }

    NormalFrame(int number) {
        this.number = number;
    }

    @Override
    public Frame throwBall(int fallingPins) {
        assignBallThrow(fallingPins);
        return getNextFrame();
    }

    @Override
    public Scoring getScoring() {
        if (isIncomplete()) {
            return Scoring.NONE;
        }

        Integer first = getFallingPins(0);
        Integer second = getFallingPins(1);
        return Scoring.valueOf(first, second);
    }

    @Override
    public List<BallThrow> getBallThrows() {
        return ballThrows;
    }

    public int getNumber() {
        return number;
    }

    private void assignBallThrow(int fallingPins) {
        if (ballThrows.isEmpty()) {
            ballThrows.add(new BallThrow(fallingPins));
            return;
        }
        ballThrows.add(ballThrows.get(0).throwSecond(fallingPins));
    }

    private Frame getNextFrame() {
        if (getScoring() == NONE) {
            return this;
        }
        if (number < 9) {
            return new NormalFrame(number + 1);
        }
        return new LastFrame();
    }

    private boolean isIncomplete() {
        return ballThrows.isEmpty() || !ballThrows.get(0).isStrike() && ballThrows.size() < 2;
    }
}
