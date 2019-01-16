package com.bladejs.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "model.all", query = "Select m from Model m"),
})
public class Model {

    private Long id;
}
