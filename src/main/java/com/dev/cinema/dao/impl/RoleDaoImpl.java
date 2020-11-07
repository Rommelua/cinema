package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.RoleDao;
import com.dev.cinema.model.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Role add(Role role) {
        return super.add(role, Role.class);
    }

    @Override
    public Role getByName(Role.RoleName roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Role r where r.roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .uniqueResult();
        }
    }
}
