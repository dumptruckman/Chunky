package com.dumptruckman.chunkyapi.object;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chunky_object")
public interface ChunkyObject extends ChunkyIdentity, ChunkyFlagged {

}
