package com.mark.pt.state;

/**
 * Created by lulei on 2016/5/27.
 */
public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void method() {
        if (state.getValue().equals("state1")) {
            state.method1();
        } else if (state.getValue().equals("state2")) {
            state.method2();
        }
    }

    public static void main(String[] args) {
        State state = new State();
        Context context = new Context(state);

        state.setValue("state1");
        context.method();

        state.setValue("state2");
        context.method();
    }
}
