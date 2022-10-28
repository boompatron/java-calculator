package com.programmers.engine.validate;

import com.programmers.engine.stack.Bracket;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class BracketValidator implements Validator{
    private final AtomicInteger openBracket = new AtomicInteger();
    private final AtomicInteger closeBracket = new AtomicInteger();
    @Override
    public LinkedList<String> validate(LinkedList<String> linkedList) {
        LinkedList<String> newList = new LinkedList<>();
        openBracket.set(0); closeBracket.set(0);
        linkedList.forEach(
                (l) -> {
                    if (l.equals("(")) {
                        openBracket.getAndIncrement();
                        newList.add(Bracket.OPEN.toString());
                    }
                    else if (l.equals(")")) {
                        closeBracket.getAndIncrement();
                        newList.add(Bracket.CLOSE.toString());
                    }
                    else{
                        newList.add(l);
                    }
                }
        );
        if (openBracket.get() != closeBracket.get()) return new LinkedList<>();
        return newList;
    }
}
