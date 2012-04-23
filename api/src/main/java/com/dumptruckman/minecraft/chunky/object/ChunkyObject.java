package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.persistence.Persistable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chunky_object")
public interface ChunkyObject extends ChunkyFlagged, Persistable {

}
