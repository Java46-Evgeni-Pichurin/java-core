package telran.view;

import java.util.function.Consumer;

public interface Item {
    String displayName();

    void perform(InputOutput io);

    boolean isExit();

    static Item of(String name, Consumer<InputOutput> action, boolean isExit) {
        return new Item() {
            @Override
            public void perform(InputOutput io) {
                action.accept(io);
            }

            @Override
            public boolean isExit() {
                return isExit;
            }

            @Override
            public String displayName() {
                return name;
            }
        };
    }

    static Item of(String name, Consumer<InputOutput> action) {
        return of(name, action, false);
    }

    static Item exit(String name) {
        return of(name, io -> {
        }, true);
    }
}
