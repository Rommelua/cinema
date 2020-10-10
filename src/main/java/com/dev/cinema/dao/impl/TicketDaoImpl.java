package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.TicketDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Ticket;

@Dao
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        return super.add(ticket, Ticket.class);
    }
}
