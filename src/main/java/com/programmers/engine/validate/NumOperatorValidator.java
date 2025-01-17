package com.programmers.engine.validate;

import com.programmers.engine.stack.Bracket;
import com.programmers.engine.stack.Operator;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NumOperatorValidator implements Validator{
    private final AtomicInteger numCnt = new AtomicInteger();
    private final AtomicInteger operatorCnt =  new AtomicInteger();

    @Override
    public LinkedList<String> validate(LinkedList<String> linkedList) {
        LinkedList<String> newList = new LinkedList<>();
        numCnt.set(0); operatorCnt.set(0);

        linkedList.forEach((element) -> {
                    if(Operator.find(element).isPresent()){
                        newList.add(Operator.find(element).get().toString());
                        operatorCnt.getAndIncrement();
                    }
                    else  {
                        newList.add(element);
                        if (!element.equals(Bracket.OPEN.toString()) && !element.equals(Bracket.CLOSE.toString()))
                            numCnt.getAndIncrement();
                    }
                }
        );

        if (numCnt.get() - 1 != operatorCnt.get())
            return new LinkedList<>();
        return
                newList;
    }
}
