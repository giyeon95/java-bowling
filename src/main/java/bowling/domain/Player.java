package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class Player {

    private final String name;
    private final Frames frames;

    public Player(String name) {
        valid(name);

        this.name = name;
        this.frames = Frames.create();
    }

    private void valid(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("플레이어의 이름은 3글자를 입력해주세요.");
        }
    }

    public Frame play(int index, Pin pin) {
        return play(Round.of(index), pin);
    }

    public Frame play(Round round, Pin pin) {
        return frames.bowl(round, pin);
    }

    public String getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }
}
