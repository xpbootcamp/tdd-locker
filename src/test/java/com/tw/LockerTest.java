package com.tw;


import com.tw.exception.InvalidTicketException;
import com.tw.exception.LockerIsFullException;
import com.tw.model.Bag;
import com.tw.model.Ticket;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LockerTest {
    @Test
    public void should_return_ticket_when_locker_save_bag_given_locker_has_available_capacity() {

        Locker locker = new Locker(10);

        Ticket ticket = locker.save(new Bag());

        assertNotNull(ticket);
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_locker_save_bag_given_locker_has_no_available_capacity() {

        Locker locker = new Locker(1);
        locker.save(new Bag());

        locker.save(new Bag());
    }

    @Test
    public void should_get_bag_when_locker_pick_up_bag_given_a_valid_ticket() {

        Locker locker = new Locker(1);
        Bag savingBag = new Bag();
        Ticket ticket = locker.save(savingBag);

        Bag takingBag = locker.pickUp(ticket);

        Assert.assertSame(savingBag, takingBag);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_locker_pick_up_bag_given_an_invalid_ticket() {

        Locker locker = new Locker(1);

        locker.pickUp(null);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_InvalidTicketException_when_locker_pick_up_bag_given_a_reused_ticket() {

        Locker locker = new Locker(1);
        Ticket ticket = locker.save(new Bag());
        locker.pickUp(ticket);

        locker.pickUp(ticket);
    }
}
