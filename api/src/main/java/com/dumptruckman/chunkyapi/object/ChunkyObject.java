package com.dumptruckman.chunkyapi.object;

import com.dumptruckman.chunkyapi.persistence.Persistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chunky_object")
public interface ChunkyObject extends ChunkyFlagged, Persistable {

}
