package no.hials.sched;

import java.util.Iterator;
import java.util.List;

/**
 * SSTF Disk scheduling algorithm (Shortest Seek Time First)
 * 
 * Fill in your code in this class!
 * 
 * 2016-04-04 
 */
public class SSTFScheduling extends DiskSchedulingAlgo {
    @Override
    public int process(List<Integer> refList) {
        if (refList == null) {
            // No references means "empty sequence" == no movement
            return 0;
        }
        
        int movement = 0; // Total head movement

        //MY SOLUTION:

        //Loop through entire cylinders
        //Find closest to head position
        //move head to closest
        //Remove closest from cylinders list

        Iterator<Integer> it = refList.iterator();
        while(it.hasNext()){
            int closest = findClosest(getHeadPosition(), refList);
            int m = moveHead(closest);
            if (m < 0) {
                // Error
                return -1;
            }
            movement += m;
        }

        return movement;
    }

    private int findClosest(int of, List<Integer> in) {
        int min = Integer.MAX_VALUE;
        int closest = of;
        int indexOfElementToRemove = -1;

        for(int i = 0; i < in.size(); i++){
            int v = in.get(i);
            final int diff = Math.abs(v - of);

            if (diff < min) {
                min = diff;
                closest = v;
                indexOfElementToRemove = i;
            }
        }

        in.remove(indexOfElementToRemove);
        return closest;
    }
}