package org.example.proxy.test;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/4 22:57
 */
public class TargetImpl implements Target{

    @Override
    public int test(int i) {
        return i +1;
    }
}
